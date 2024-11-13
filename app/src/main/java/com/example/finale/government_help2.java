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

public class government_help2 extends MyBaseActivity {
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
            
            int position = getIntent().getIntExtra("kacheri_key",0);

        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) { // 1000 = 1second
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        webView.loadData(String.valueOf(mLastClickTime), "text/html", "UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


            if (position==0){
                setTitle("ગુજરાત રાજ્ય બીજ નિગમ લિમિટેડ");
                webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Helplines/hl2.html");
            }
            else if (position==1){
                setTitle("ગુજરાત રાજ્ય બીજ પ્રમાણન એજન્સી");
                webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Helplines/hl3.html");
            }
            else if (position==2){
                setTitle("ગુજરાત ખેત ઉધોગ નિગમ");
                webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Helplines/hl4.html");
            }
            else if (position==3){
                setTitle("ગુજરાત રાજ્ય જમીન વિકાસ નિગમ લિમિટેડ");
                webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Helplines/hl5.html");
            }
            else if (position==4){
                setTitle("સેન્ટર ઓફ એક્સલેન્સ");
                webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Helplines/hl6.html");
            }
            else if (position==5){
                setTitle("જીલ્લા બગાયત કચેરી");
                webView.loadUrl("http://krishividhi.000webhostapp.com/assets/Helplines/hl1.html");
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