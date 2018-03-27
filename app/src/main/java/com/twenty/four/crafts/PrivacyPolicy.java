package com.twenty.four.crafts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PrivacyPolicy extends AppCompatActivity {

    String terms_Url = "file:///android_asset/privacypolicy.html";
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);


        getSupportActionBar().setTitle("Privacy Policy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(terms_Url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }
        else {
            finish();
        }
    }
}
