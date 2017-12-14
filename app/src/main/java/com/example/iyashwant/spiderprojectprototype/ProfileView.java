package com.example.iyashwant.spiderprojectprototype;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ProfileView extends AppCompatActivity {

    ImageButton edit_profile, profile_back, fav_profile;
    NestedScrollView nestedScrollView;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        edit_profile = (ImageButton) findViewById(R.id.edit_profile);
        profile_back = (ImageButton) findViewById(R.id.profile_back);
        fav_profile = (ImageButton) findViewById(R.id.fav_profile);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ProfileViewEdit.class);
                startActivity(i);
            }
        });

        fav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i%2!=0){
                    ObjectAnimator animY = ObjectAnimator.ofFloat(fav_profile, "translationY", -100f, 0f);
                    animY.setDuration(800);
                    animY.setInterpolator(new BounceInterpolator());
                    animY.start();
                    fav_profile.setImageResource(R.drawable.heart_full);
                } else {
                    ObjectAnimator animY = ObjectAnimator.ofFloat(fav_profile, "translationY", -100f, 0f);
                    animY.setDuration(800);
                    animY.setInterpolator(new BounceInterpolator());
                    animY.start();
                    fav_profile.setImageResource(R.drawable.heart_outline);
                }
            }
        });


        //ViewPager viewPager = (ViewPager) findViewById(R.id.coverphotoviewpager);
        //ImageAdapter adapter = new ImageAdapter(this); //Here we are defining the Imageadapter object
        //viewPager.setAdapter(adapter); // Here we are passing and setting the adapter for the images

    }

}
