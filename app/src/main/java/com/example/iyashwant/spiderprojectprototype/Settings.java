package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.registration.Verification;

public class Settings extends AppCompatActivity {

    LinearLayout info, verify, coinsfree, morecoins, themes, daily, contactus, aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String type = getIntent().getStringExtra("type");

        info = (LinearLayout) findViewById(R.id.info);
        verify = (LinearLayout) findViewById(R.id.verify);
        coinsfree = (LinearLayout) findViewById(R.id.coinsfree);
        morecoins = (LinearLayout) findViewById(R.id.morecoins);
        themes = (LinearLayout) findViewById(R.id.themes);
        daily = (LinearLayout) findViewById(R.id.daily);
        contactus = (LinearLayout) findViewById(R.id.contactus);
        aboutus = (LinearLayout) findViewById(R.id.aboutus);

        if(type.equals("clients")) {
            coinsfree.setVisibility(View.GONE);
            morecoins.setVisibility(View.GONE);
            daily.setVisibility(View.GONE);
        }

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AccountInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

        if(type.equals("crafts")) {
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Verification.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);
                }
            });

        } else if(type.equals("clients")) {
            //stuff
        }

        coinsfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FreeCoins.class);
                startActivity(intent);
            }
        });

        themes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stuff
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stuff
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stuff
            }
        });



        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(Settings.this, AboutUs.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

    }
}
