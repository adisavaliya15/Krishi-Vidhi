package com.example.finale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class pak_anaj_webview extends MyBaseActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.expandable_listview_webview, null, false);
        drawer.addView(v, 0);


        swipeRefreshLayout = findViewById(R.id.refreshLayout);



    WebView webView1;
        webView1 = findViewById(R.id.webview);
        int groupPosition = getIntent().getIntExtra("key",0);
        int childPosition = getIntent().getIntExtra("child",1);
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



        if(groupPosition==0){
            setTitle("ઘઉ");
            if(childPosition == 0){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav1.html");}
            else if(childPosition == 1){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav2.html");}
            else if(childPosition == 2){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav3.html");}
            else if(childPosition == 3){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav4.html");}
            else if(childPosition == 4){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav5.html");}
            else if(childPosition == 5){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav6.html");}
            else if(childPosition == 6){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav7.html");}
            else if(childPosition == 7){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav8.html");}
            else if(childPosition == 8){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Ghau/ghav9.html");}
        }

        if(groupPosition==1) {
            setTitle("મકાઈ");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Makai/makai1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Makai/makai2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Makai/makai3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Makai/makai4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Makai/makai5.html");
            }
        }

        if(groupPosition==2) {
            setTitle("બાજરી");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Baajari/bajri1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Baajari/bajri2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Baajari/bajri3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Baajari/bajri4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Baajari/bajri5.html");
            } else if (childPosition == 5) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Baajari/bajri6.html");
            }
        }

        if(groupPosition==3) {
            setTitle("જુવાર");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Juvar/juvar1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Juvar/juvar2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Juvar/juvar3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Juvar/juvar4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Juvar/juvar5.html");
            } else if (childPosition == 5) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Juvar/juvar6.html");
            }
        }

        if(groupPosition==4) {
            setTitle("ડાંગર");
            if (childPosition == 0) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice1.html");
            } else if (childPosition == 1) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice2.html");
            } else if (childPosition == 2) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice3.html");
            } else if (childPosition == 3) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice4.html");
            } else if (childPosition == 4) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice5.html");
            } else if (childPosition == 5) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice6.html");
            } else if (childPosition == 6) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice7.html");
            } else if (childPosition == 7) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice8.html");
            } else if (childPosition == 8) {
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Rice/rice9.html");
            }

        }
    }

}