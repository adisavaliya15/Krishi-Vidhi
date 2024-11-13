package com.example.finale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class pak_kathol_webview extends MyBaseActivity {

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
            setTitle("તુવેર");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tuver/tuver1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tuver/tuver2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tuver/tuver3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tuver/tuver4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tuver/tuver5.html");
            } else if (childPosition == 5) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tuver/tuver6.html");
            } else if (childPosition == 6) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Tuver/tuver7.html");
            }
        }

        if (groupPosition == 1) {
            setTitle("અડદ");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Adad/adad1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Adad/adad2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Adad/adad3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Adad/adad4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Adad/adad5.html");
            }

        }
        if (groupPosition == 2) {
            setTitle("ચણા");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Chana/chana1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Chana/chana2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Chana/chana3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Chana/chana4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Chana/chana5.html");
            } else if (childPosition == 5) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Chana/chana6.html");
            }

        }
        if (groupPosition == 3) {
            setTitle("મગ");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Mag/mag1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Mag/mag2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Mag/mag3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Mag/mag4.html");
            }

        }
    }
}