package com.twenty.four.crafts;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        getSupportActionBar().setTitle("About Us");


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
