package com.example.reignchallenge.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.reignchallenge.R
import com.example.reignchallenge.model.NoticesSaved

class DetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var webSettings: WebSettings
    var data:NoticesSaved = NoticesSaved()
    var url: String? = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        data = intent.getParcelableExtra("notice")!!

        data.story_url.let {
            url = it
        }

        webView = findViewById(R.id.webview)
        webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(url!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                this.finish()
            }
        }
        return true
    }
}