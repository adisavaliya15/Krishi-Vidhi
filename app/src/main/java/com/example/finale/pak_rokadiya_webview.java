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

public class pak_rokadiya_webview extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.expandable_listview_webview, null, false);
        drawer.addView(v, 0);


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
            setTitle("કપાસ");
            if(childPosition == 0){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Kapas/kapas1.html");}
            else if(childPosition == 1){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Kapas/kapas2.html");}
            else if(childPosition == 2){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Kapas/kapas3.html");}
            else if(childPosition == 3){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Kapas/kapas4.html");}
            else if(childPosition == 4){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Kapas/kapas5.html");}
            else if(childPosition == 5){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Kapas/kapas6.html");}
            else if(childPosition == 6){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Kapas/kapas7.html");}
            else if(childPosition == 7){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Kapas/kapas8.html");}
        }

        if(groupPosition==1){
            setTitle("શેરડી");
            if(childPosition == 0){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Sherdi/sherdi1.html");}
            else if(childPosition == 1){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Sherdi/sherdi2.html");}
            else if(childPosition == 2){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Sherdi/sherdi3.html");}
            else if(childPosition == 3){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Sherdi/sherdi4.html");}
            else if(childPosition == 4){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Sherdi/sherdi5.html");}
            else if(childPosition == 5){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Sherdi/sherdi6.html");}
            else if(childPosition == 6){
                webView1.loadUrl("http://krishividhi.000webhostapp.com/assets/Sherdi/sherdi7.html");}
        }
    }
}
