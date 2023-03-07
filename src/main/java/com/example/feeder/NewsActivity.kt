package com.example.feeder

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class NewsActivity : AppCompatActivity() {
    lateinit var webView: WebView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val actionBar = supportActionBar
        actionBar!!.title = "News"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        webView = findViewById(R.id.wv_news)
        webView.webViewClient = WebViewClient()
        intent.getStringExtra("URL")?.let { webView.loadUrl(it) }
        //webView.loadUrl("https://www.geeksforgeeks.org/")
        webView.settings.javaScriptEnabled = true
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}