package com.example.qarapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class webview1 : AppCompatActivity() {
    lateinit var webView1: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview1)

        val link = intent.getStringExtra("link")
        webView1 = findViewById(R.id.Webview1)
        webView1.settings.javaScriptEnabled = true // enable javascript
        webView1.webViewClient = WebViewClient() //important to open url in your app
        webView1.loadUrl(link.toString())

    }
}