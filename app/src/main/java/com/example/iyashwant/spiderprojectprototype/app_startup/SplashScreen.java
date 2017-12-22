package com.example.iyashwant.spiderprojectprototype.app_startup;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.iyashwant.spiderprojectprototype.R;

public class SplashScreen extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    VideoView splashvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashvideo = (VideoView) findViewById(R.id.splashvideo);



        String uriPath = "android.resource://com.example.iyashwant.spiderprojectprototype/" + R.raw.opening;
        Uri uri = Uri.parse(uriPath);
        splashvideo.setVideoURI(uri);
        splashvideo.requestFocus();
        splashvideo.start();


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, Login2.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        splashvideo= (VideoView) findViewById(R.id.splashvideo);
        String uriPath = "android.resource://com.example.iyashwant.spiderprojectprototype/" + R.raw.opening;
        Uri uri = Uri.parse(uriPath);
        splashvideo.setVideoURI(uri);
        splashvideo.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashvideo.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        splashvideo.stopPlayback();
    }

}