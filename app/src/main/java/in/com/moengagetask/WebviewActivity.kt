package `in`.com.moengagetask

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        var loadUrl = intent.extras?.getString("url").toString()


        var webView: WebView = findViewById<View>(R.id.webView) as WebView

        webView!!.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        webView.loadUrl(loadUrl)


    }
}