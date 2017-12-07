package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.iyashwant.spiderprojectprototype.registration.signup;
import com.example.iyashwant.spiderprojectprototype.registration.signupclient;

public class StartingScreen extends AppCompatActivity {

    ImageView craftsman_reg, client_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        craftsman_reg = (ImageView) findViewById(R.id.craftsman_reg);
        client_reg = (ImageView) findViewById(R.id.client_reg);

        craftsman_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),signup.class);
                startActivity(i);
            }
        });

        client_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),signupclient.class);
                startActivity(i);
            }
        });

    }

}
