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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.iyashwant.spiderprojectprototype.LoginSocial;
import com.example.iyashwant.spiderprojectprototype.R;
import com.example.iyashwant.spiderprojectprototype.registration.StartingScreen;
import com.github.clans.fab.FloatingActionButton;

public class Login2 extends AppCompatActivity {

    FloatingActionButton fabEmail, fabSign, fabExit;
    VideoView videoview;
    ImageButton fbLogin, googleLogin, instaLogin;
    RelativeLayout webhold;
    Button webclose;
    ProgressBar progressBar;
    WebView wv;

    private final String LOG_TAG = "Login2 Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        wv = (WebView) findViewById(R.id.webView);

        webhold = (RelativeLayout) findViewById(R.id.webhold);
        webhold.setVisibility(View.GONE);

        webclose = (Button) findViewById(R.id.webclose);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        initialiseViews();
        displayCoverVideo();
        initialiseFABEvents();
        //initialiseLoginButtonEvents();
        LoginpopUp();

        webclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webhold.setVisibility(View.GONE);
            }
        });
    }


    private void LoginpopUp()
    {

        ImageButton facebook = (ImageButton)findViewById(R.id.fb_login_button);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                        // TODO Auto-generated method stub
                        super.onPageFinished(view, url);
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }
        });


        ImageButton google = (ImageButton)findViewById(R.id.gl_login_button);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                webhold.setVisibility(View.VISIBLE);
                WebView wv = (WebView) findViewById(R.id.webView);
                wv.loadUrl("http:\\24crafts.tk:3000/login/google");
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        // TODO Auto-generated method stub
                        super.onPageFinished(view, url);
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }
        });

        ImageButton twitter = (ImageButton)findViewById(R.id.twitter_login_button);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                        // TODO Auto-generated method stub
                        super.onPageFinished(view, url);
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }


        });
    }

    private void initialiseLoginButtonEvents(){
        View.OnClickListener loginListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                //TODO:Add social login action below
                if(id==R.id.fb_login_button){
                    Intent socialpage = new Intent(getApplicationContext(),LoginSocial.class);
                    startActivity(socialpage);


                } else if(id == R.id.gl_login_button){
                    Intent socialpage = new Intent(getApplicationContext(),LoginSocial.class);
                    startActivity(socialpage);


                } else if(id==R.id.twitter_login_button){
                    Intent socialpage = new Intent(getApplicationContext(),LoginSocial.class);
                    startActivity(socialpage);


                } else {
                    Log.d(LOG_TAG, "Unregistered click event");
                }

            }
        };

        fbLogin.setOnClickListener(loginListener);
        googleLogin.setOnClickListener(loginListener);
        instaLogin.setOnClickListener(loginListener);
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

    private void initialiseViews(){
        fabEmail=(FloatingActionButton)findViewById(R.id.action_email_fab);
        fabSign = (FloatingActionButton)findViewById(R.id.action_sign_fab);
        fabExit = (FloatingActionButton)findViewById(R.id.action_exit_fab);

        videoview = (VideoView) findViewById(R.id.videoView);

        fbLogin = (ImageButton)findViewById(R.id.fb_login_button);
        googleLogin = (ImageButton)findViewById(R.id.gl_login_button);
        instaLogin = (ImageButton)findViewById(R.id.twitter_login_button);
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

        fabExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
