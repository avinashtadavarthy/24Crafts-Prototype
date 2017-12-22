package com.example.iyashwant.spiderprojectprototype;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class ProfileView extends AppCompatActivity {

    ImageButton edit_profile, profile_back, fav_profile;
    NestedScrollView nestedScrollView;
    ImageView video1, video2, video3;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        edit_profile = (ImageButton) findViewById(R.id.edit_profile);
        profile_back = (ImageButton) findViewById(R.id.profile_back);
        fav_profile = (ImageButton) findViewById(R.id.fav_profile);
        //video1 = findViewById(R.id.profileVideo1);
        video2 = findViewById(R.id.profileVideo2);
        video3 = findViewById(R.id.profileVideo3);

        //Picasso.with(getApplicationContext()).load("https://img.youtube.com/vi/eGCM444_mN0/mqdefault.jpg").into(video1);
        Picasso.with(getApplicationContext()).load("https://img.youtube.com/vi/eGCM444_mN0/mqdefault.jpg").into(video2);
        Picasso.with(getApplicationContext()).load("https://img.youtube.com/vi/eGCM444_mN0/mqdefault.jpg").into(video3);
        //DownloadThumbnailImageYoutubeVideo("https://www.youtube.com/watch?v=eGCM444_mN0");
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
                Intent i = new Intent(getApplicationContext(), ProfileViewEdit.class);
                startActivity(i);
            }
        });

        fav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i % 2 != 0) {
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

  /*  public void DownloadThumbnailImageYoutubeVideo(final String imgurl) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imgurl).getContent());
                    video1.post(new Runnable() {
                        @Override
                        public void run() {
                            if (bitmap != null) {
                                video1.setImageBitmap(bitmap);
                                video2.setImageBitmap(bitmap);
                                video3.setImageBitmap(bitmap);
                            }

                        }
                    });
                } catch (Exception e) {
                }
            }
        }).start();
*/



    public void playVideo(View view)
    {
        Intent i = new Intent(getApplicationContext(),YoutubePlayerActivity.class);
        startActivity(i);
    }

}
