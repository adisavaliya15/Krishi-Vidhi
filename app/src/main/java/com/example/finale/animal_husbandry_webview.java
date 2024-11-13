package com.example.finale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class animal_husbandry_webview extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.expandable_listview_webview, null, false);
        drawer.addView(v, 0);

        WebView webView1;
        webView1 = findViewById(R.id.webview);
        int groupPosition = getIntent().getIntExtra("key", 0);
        int childPosition = getIntent().getIntExtra("child", 1);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView1.getSettings().setAllowFileAccess(true);
        webView1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        webView1.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);

                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        if (groupPosition == 0) {
            setTitle("ગાય");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Gay/gay1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Gay/gay2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Gay/gay3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Gay/gay4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Gay/gay5.html");
            } else if (childPosition == 5) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Gay/gay6.html");
            }
        } if (groupPosition == 1) {
            setTitle("ભેંસ");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Bhesh/bhesh1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Bhesh/bhesh2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Bhesh/bhesh3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Bhesh/bhesh4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Bhesh/bhesh5.html");
            } else if (childPosition == 5) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Bhesh/bhesh6.html");
            }
        }
    }
}