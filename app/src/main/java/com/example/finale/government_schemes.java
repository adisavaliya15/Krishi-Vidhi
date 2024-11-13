package com.example.finale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class government_schemes extends MyBaseActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_government_schemes, null, false);
        drawer.addView(v, 0);
        setTitle("સરકારી યોજનાઓ");

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

//for all web view
        web = findViewById(R.id.webview);
        web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        /*web.getSettings().getAllowContentAccess();
        web.getSettings().getAllowFileAccess();
        web.getSettings().getAllowFileAccessFromFileURLs();
        web.getSettings().getAllowUniversalAccessFromFileURLs();
        web.getSettings().getBuiltInZoomControls();
*/

        web.getSettings().setJavaScriptEnabled(true);


        web.setWebViewClient(new WebViewClient());
        web.loadUrl("https://ikhedut.gujarat.gov.in/iKhedutPublicScheme/");

        web.setWebChromeClient(new WebChromeClient() {

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

    @Override
    public void onBackPressed() {
        if (web != null && web.canGoBack())
            web.goBack();// if there is previous page open it
        else
            super.onBackPressed();//if there is no previous page, close app
    }
}