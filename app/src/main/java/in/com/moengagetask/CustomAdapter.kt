package `in`.com.moengagetask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class CustomAdapter(context: Context, arrayListDetails: List<Model>) : BaseAdapter(){

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val arrayListDetails: List<Model> = arrayListDetails

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder

        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.adapter_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.title.text = arrayListDetails.get(position).title
        val imageUrl = arrayListDetails.get(position).urlToImage

        listRowHolder.urlToImage.setImageURI(imageUrl)
        return view
    }
}

private class ListRowHolder(row: View?) {

    val title: TextView = row?.findViewById<TextView>(R.id.title) as TextView
    val urlToImage: ImageView = row?.findViewById<ImageView>(R.id.imageView) as ImageView

}