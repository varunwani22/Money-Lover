package com.example.moneylover.views

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.moneylover.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView :WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val one = intent.getStringExtra("one")
        webView = findViewById(R.id.webView11)
        webView.loadUrl(one!!)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
    }
}