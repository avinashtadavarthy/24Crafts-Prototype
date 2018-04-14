package com.twenty.four.crafts;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Theme Stuff ---- Start
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.night);
        } else {
            setTheme(R.style.day);
        }

        // Status Bar Tint
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            Window w = getWindow();
//            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            w.setBackgroundDrawable(getResources().getDrawable(R.drawable.b2));
            Window w = getWindow();
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            Drawable bgTint = this.getResources().getDrawable(R.drawable.b2);
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            w.setStatusBarColor(this.getResources().getColor(R.color.transparent_black_hex_11));
            w.setNavigationBarColor(this.getResources().getColor(R.color.transparent_black_hex_11));
            w.setBackgroundDrawable(bgTint);
        }
        //Theme Stuff ---- End

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        getSupportActionBar().setTitle("About Us");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.b2));


        TextView privac =  (TextView) findViewById(R.id.privacy);
        TextView tandc = (TextView) findViewById(R.id.tandc);
        LinearLayout website = (LinearLayout) findViewById(R.id.website);
        LinearLayout copy = (LinearLayout) findViewById(R.id.copy);
        TextView change_log = (TextView) findViewById(R.id.change_log);

            privac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AboutUs.this, PrivacyPolicy.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);

                }
            });

            tandc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AboutUs.this, TermsConditions.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);

                }
            });

            website.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.geass.technology"));
                    startActivity(i);
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);

                }
            });

            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AboutUs.this, "Add Copyright page", Toast.LENGTH_SHORT).show();
                }
            });

            change_log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AboutUs.this, "Add Changelog page", Toast.LENGTH_SHORT).show();
                }
            });

        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home: finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



        public void goBacktoPrevious(View view)
        {
            onBackPressed();
        }


}
