package com.example.iyashwant.spiderprojectprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ProfileView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        ImageView mainImg = (ImageView) findViewById(R.id.MainImage);
        ImageView sideImg1 = (ImageView) findViewById(R.id.SideImage1);
        ImageView sideImg2 = (ImageView) findViewById(R.id.SideImage2);
        ImageView bottomImg1 = (ImageView) findViewById(R.id.BottomImage1);
        ImageView bottomImg2 = (ImageView) findViewById(R.id.BottomImage2);
        ImageView bottomImg3 = (ImageView) findViewById(R.id.BottomImage3);
        ImageView bottomImg4 = (ImageView) findViewById(R.id.BottomImage4);
        Picasso.with(this).load("http://i.imgur.com/DoA1JJF.jpg").into(mainImg);
        Picasso.with(this).load("http://i.imgur.com/CUSEWr8.jpg").into(sideImg1);
        Picasso.with(this).load("http://i.imgur.com/9aOoyS1.jpg").into(sideImg2);
        Picasso.with(this).load("http://i.imgur.com/P7aM0RD.jpg").into(bottomImg1);
        Picasso.with(this).load("http://i.imgur.com/KNiOOEm.jpg").into(bottomImg2);
        Picasso.with(this).load("http://i.imgur.com/qpccd8j.jpg").into(bottomImg3);
        Picasso.with(this).load("http://i.imgur.com/1mVoFZS.jpg").into(bottomImg4);
    }
}
