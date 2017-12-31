package com.twenty.four.crafts.app_startup;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.twenty.four.crafts.R;

public class SplashScreen extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    ImageView splashlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashlogo = (ImageView) findViewById(R.id.splashlogo);
        if (android.os.Build.VERSION.SDK_INT >= 21) splashlogo.setElevation(10);

        ImageView splashgif = (ImageView) findViewById(R.id.splashgif);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(splashgif);
        Glide.with(this).load(R.raw.samplevideo).into(imageViewTarget);


        new Handler().postDelayed(new Runnable() {

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

}