package com.example.finale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class pak_telibiya_webview extends MyBaseActivity {


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
            setTitle("દિવેલાં");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Aernda/diwela1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Aernda/diwela2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Aernda/diwela3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Aernda/diwela4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Aernda/diwela5.html");
            } else if (childPosition == 5) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Aernda/diwela6.html");
            } else if (childPosition == 6) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Aernda/diwela7.html");
            }
        }

        if (groupPosition == 1) {
            setTitle("મગફળી");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Magafali/magfali1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Magafali/magfali2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Magafali/magfali3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Magafali/magfali4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Magafali/magfali5.html");
            }

        }
        if (groupPosition == 2) {
            setTitle("તલ");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tal/tal1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tal/tal2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tal/tal3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tal/tal4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tal/tal5.html");
            }

        }
        if (groupPosition == 3) {
            setTitle("સોયાબીન");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Soyabin/soyabean1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Soyabin/soyabean2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Soyabin/soyabean3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Soyabin/soyabean4.html");
            }

        }
    }
}
