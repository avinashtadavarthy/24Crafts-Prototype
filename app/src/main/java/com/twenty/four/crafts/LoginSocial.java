package com.twenty.four.crafts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class LoginSocial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_social);
       final Button b1;

        final WebView wv1;

            b1=(Button)findViewById(R.id.button);


            wv1=(WebView)findViewById(R.id.webView);
            wv1.setWebViewClient(new MyBrowser());

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String url = ed1.getText().toString();

                    wv1.getSettings().setLoadsImagesAutomatically(true);
                    wv1.getSettings().setJavaScriptEnabled(true);
                    wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    wv1.loadUrl("https://www.google.com");
                }
            });
        }

        private class MyBrowser extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
    }
}
