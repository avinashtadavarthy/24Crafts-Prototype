package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
    }

    public void regclient(View view)
    {
        Intent i = new Intent(getApplicationContext(),signupclient.class);
        startActivity(i);
    }

    public void regcraftsmen(View view)
    {
        Intent i = new Intent(getApplicationContext(),signup.class);
        startActivity(i);
    }
}
