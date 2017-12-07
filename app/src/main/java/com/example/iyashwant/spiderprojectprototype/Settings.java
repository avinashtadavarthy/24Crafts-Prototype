package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void freecoins(View view)
    {
        Intent intent = new Intent(getApplicationContext(),FreeCoins.class);
        startActivity(intent);
    }

    public void accountinfo(View view)
    {
        Intent intent = new Intent(getApplicationContext(),AccountInfo.class);
        startActivity(intent);
    }

}
