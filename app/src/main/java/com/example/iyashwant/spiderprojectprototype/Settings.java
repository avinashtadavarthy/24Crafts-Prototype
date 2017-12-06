package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    TextView accountinfo, verify, buycoins, themes, dailycheck, contact, aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aboutus = (TextView) findViewById(R.id.aboutus);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i = new Intent(Settings.this, AboutUs.class);
                startActivity(i);

            }
        });

    }

    public void freecoins(View view)
    {
        Intent intent = new Intent(getApplicationContext(),FreeCoins.class);
        startActivity(intent);
    }

}
