package com.example.firebaseproject1;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Web_Site extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web__site);
webView=findViewById(R.id.webid);
        this.setTitle("Website");

        WebSettings webSettings=webView.getSettings();
        webSettings.getJavaScriptEnabled();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.fenipoly.edu.bd/");

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();}
    }
}
