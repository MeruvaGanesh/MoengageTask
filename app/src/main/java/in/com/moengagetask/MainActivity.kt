package `in`.com.moengagetask

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var listViewDetails: ListView

    var arrayListDetails:ArrayList<Model> = ArrayList();
    private val client = OkHttpClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listViewDetails = findViewById<ListView>(R.id.listView) as ListView
        run("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")
    }

    private fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()


        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.v("ERROR", "Failed to execute URL")
            }

            override fun onResponse(call: Call, response: Response) {
                var strResponse = response.body()!!.string()
                //creating json object

                val jsonContact = JSONObject(strResponse)
                //creating json array

                var jsonArrayInfo: JSONArray = jsonContact.getJSONArray("articles")
                var size: Int = jsonArrayInfo.length()

                arrayListDetails = ArrayList();
                for (i in 0 until size) {
                    var jsonObjectdetail: JSONObject = jsonArrayInfo.getJSONObject(i)
                    var model = Model();
                    model.title = jsonObjectdetail.getString("title")
                    model.urlToImage = Uri.parse(jsonObjectdetail.getString("urlToImage"))
                    model.url = Uri.parse(jsonObjectdetail.getString("url"))
                    model.date = Uri.parse(jsonObjectdetail.getString("publishedAt")).toString()
                    arrayListDetails.add(model)

                    Log.v("Date", model.date)

                }

                var sortedArrayListDetails = arrayListDetails.sortedWith(compareBy { it.date }).reversed()

                runOnUiThread {
                    val objAdapter = CustomAdapter(applicationContext, sortedArrayListDetails)
                    var urlOfContent = ""
                    listViewDetails.adapter = objAdapter
                    listViewDetails.setOnItemClickListener { parent, view, position, id ->

                        for (i in 0 until position) {
                            var jsonObjectdetails: JSONObject = jsonArrayInfo.getJSONObject(i)
                            var model = Model();
                            model.url = Uri.parse(jsonObjectdetails.getString("url"))
                            urlOfContent = model.url.toString()
                        }

                        intent = Intent(applicationContext, WebviewActivity::class.java)
                        intent.putExtra("url", urlOfContent)
                        Log.v("URLInMain", urlOfContent)
                        startActivity(intent)
                    }
                }
            }

        })

    }


}
