package com.demo.indiamoneydemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {
    WebView web;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        /*getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FFFF0000")));*/
        mProgressBar = (ProgressBar) findViewById(R.id.pb);
        web = findViewById(R.id.webview);
        renderWebPage("https://developer.android.com/guide/index.html");
    }

    protected void renderWebPage(String urlToRender) {
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Do something on page loading started
                // Visible the progressbar
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Do something when page loading finished

            }

        });
       web.setWebChromeClient(new WebChromeClient(){

    public void onProgressChanged(WebView view, int newProgress){
        // Update the progress bar with page loading progress
        mProgressBar.setProgress(newProgress);
        if(newProgress == 100){
            // Hide the progressbar
            mProgressBar.setVisibility(View.GONE);
        }
    }
});


        web.getSettings().setJavaScriptEnabled(true);
        // Render the web page
        web.loadUrl(urlToRender);
        }
}

