package com.example.iyashwant.spiderprojectprototype;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Avinash Tadavarthy on 05-Nov-17.
 */

public class ProfileView2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileview);

        getSupportActionBar().setTitle("View / Edit Your Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
