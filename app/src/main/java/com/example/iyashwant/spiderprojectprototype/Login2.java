package com.example.iyashwant.spiderprojectprototype;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.github.clans.fab.FloatingActionButton;

public class Login2 extends AppCompatActivity {

    FloatingActionButton fabEmail, fabSign, fabExit;
    VideoView videoview;
    ImageButton fbLogin, googleLogin, instaLogin;

    private final String LOG_TAG = "Login2 Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        initialiseViews();
        displayCoverVideo();
        initialiseFABEvents();
        initialiseLoginButtonEvents();

    }

    private void initialiseLoginButtonEvents(){
        View.OnClickListener loginListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                //TODO:Add social login action below
                if(id==R.id.fb_login_button){

                } else if(id == R.id.gl_login_button){

                } else if(id==R.id.inst_login_button){

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
        instaLogin = (ImageButton)findViewById(R.id.inst_login_button);
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
