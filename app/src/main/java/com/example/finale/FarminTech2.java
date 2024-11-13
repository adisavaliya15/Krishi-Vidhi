package com.example.finale;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class FarminTech2 extends MyBaseActivity{
    WebView webView;
    private static long mLastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        View v =inflater.inflate(R.layout.activity_government_help2,null,false);
        drawer.addView(v,0);

        webView = findViewById(R.id.webview);

        ProgressBar progressBar = findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

        int position = getIntent().getIntExtra("Farming",0);

        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) { // 1000 = 1second
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        webView.loadData(String.valueOf(mLastClickTime), "text/html", "UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        if (position==0){
            setTitle("બાગાયતી ખેતી");
            webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Bagayati/bagayati.html");
        }
        else if (position==1){
            setTitle("હાયડ્રોપોનિક્સ");
            webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Hydrophonics/hydro.html");
        }
        else if (position==2){
            setTitle("ટેરેસ બગીચો");
            webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Teres%20garden/teres.html");
        }
        else if (position==3){
            setTitle("હર્બલ ગાર્ડન");
            webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Herbal/herbal.html");
        }
        else if (position==4){
            setTitle("નેટ હાઉસ");
            webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Net%20House/nethouse.html");
        }
        else if (position==5){
            setTitle("ઇઝરાઇલ ટેકનોલોજી");
            webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Iserael/iserael.html");
        }


        webView.setWebChromeClient(new WebChromeClient() {

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
    }
}
