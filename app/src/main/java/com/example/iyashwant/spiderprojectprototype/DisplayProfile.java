package com.example.iyashwant.spiderprojectprototype;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DisplayProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        ViewPager viewPager = (ViewPager) findViewById(R.id.coverphotoviewpager);

        ImageAdapter adapter = new ImageAdapter(this); //Here we are defining the Imageadapter object

        viewPager.setAdapter(adapter); // Here we are passing and setting the adapter for the images

    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.


        return true;

    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will

        // automatically handle clicks on the Home/Up button, so long

        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();


        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {

            return true;

        }


        return super.onOptionsItemSelected(item);

    }

}
