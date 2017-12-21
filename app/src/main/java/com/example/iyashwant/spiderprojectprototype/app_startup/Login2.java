package com.example.iyashwant.spiderprojectprototype.app_startup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.iyashwant.spiderprojectprototype.LoginSocial;
import com.example.iyashwant.spiderprojectprototype.R;
import com.example.iyashwant.spiderprojectprototype.registration.StartingScreen;
import com.github.clans.fab.FloatingActionButton;

public class Login2 extends AppCompatActivity {

    FloatingActionButton fabEmail, fabSign;
    VideoView videoview;
    LinearLayout facebook, google, instagram;

    /*
    RelativeLayout webhold;
    Button webclose;
    ProgressBar progressBar;
    WebView wv;
    */

    private final String LOG_TAG = "Login2 Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        facebook = (LinearLayout) findViewById(R.id.fb_login_button);
        google = (LinearLayout) findViewById(R.id.gl_login_button);
        instagram = (LinearLayout) findViewById(R.id.instagram_login_button);

        fabEmail=(FloatingActionButton)findViewById(R.id.action_email_fab);
        fabSign = (FloatingActionButton)findViewById(R.id.action_sign_fab);

        videoview = (VideoView) findViewById(R.id.videoView);

        displayCoverVideo();
        initialiseFABEvents();
        //initialiseLoginButtonEvents();
        LoginpopUp();

        /*
        wv = (WebView) findViewById(R.id.webView);
        webhold = (RelativeLayout) findViewById(R.id.webhold);
        webhold.setVisibility(View.GONE);
        webclose = (Button) findViewById(R.id.webclose);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        webclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webhold.setVisibility(View.GONE);
            }
        });
        */

    }


    private void LoginpopUp()
    {

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                webhold.setVisibility(View.VISIBLE);
                wv.loadUrl("http:\\24crafts.tk:3000/login/facebook");
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        progressBar.setVisibility(View.GONE);
                    }
                });
                */

            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http:\\24crafts.tk:3000/login/google"));
                startActivity(i);
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
                */

            }
        });


        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                webhold.setVisibility(View.VISIBLE);
                WebView wv = (WebView) findViewById(R.id.webView);
                wv.loadUrl("http:\\24crafts.tk:3000/login/twitter");
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        progressBar.setVisibility(View.GONE);
                    }
                });
                */

            }


        });
    }

    private void displayCoverVideo(){
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }


    private void initialiseFABEvents(){
        fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getApplicationContext(),Login.class);
                startActivity(nextpage);
            }
        });

        fabSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getApplicationContext(),StartingScreen.class);
                startActivity(nextpage);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoview = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoview.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoview.stopPlayback();
    }

    //keyboard disappears when you click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
