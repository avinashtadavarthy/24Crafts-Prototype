package com.twenty.four.crafts;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.twenty.four.crafts.registration.Verification;

public class Settings extends AppCompatActivity {

    LinearLayout info, verify, coinsfree, morecoins, themes, daily, contactus, aboutus;
    View baccinfo,bverify,bfreecoins,bbuymorecoins,bthemes,bdaily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String type = getIntent().getStringExtra("type");

        info = (LinearLayout) findViewById(R.id.info);
        verify = (LinearLayout) findViewById(R.id.verify);
        coinsfree = (LinearLayout) findViewById(R.id.coinsfree);
        morecoins = (LinearLayout) findViewById(R.id.morecoins);
        themes = (LinearLayout) findViewById(R.id.themes);
        daily = (LinearLayout) findViewById(R.id.daily);
        contactus = (LinearLayout) findViewById(R.id.contactus);
        aboutus = (LinearLayout) findViewById(R.id.aboutus);

        baccinfo = findViewById(R.id.belowaccinfo);
        bverify = findViewById(R.id.belowverify);
        bfreecoins = findViewById(R.id.belowfreecoins);
        bbuymorecoins = findViewById(R.id.belowbuymorecoins);
        bthemes = findViewById(R.id.belowthemes);
        bdaily = findViewById(R.id.belowdaily);

        if(type.equals("clients")) {
            coinsfree.setVisibility(View.GONE);
            morecoins.setVisibility(View.GONE);
            daily.setVisibility(View.GONE);

            bfreecoins.setVisibility(View.GONE);
            bbuymorecoins.setVisibility(View.GONE);
            bdaily.setVisibility(View.GONE);
        }

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AccountInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

        if(type.equals("crafts")) {
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Verification.class).putExtra("fromhere", "FromSettings");
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);
                }
            });

        } else if(type.equals("clients")) {
            //stuff
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),VerifyYourself.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);
                }
            });
        }

        morecoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PurchaseCoins.class);
                startActivity(intent);
            }
        });
        coinsfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FreeCoins.class);
                startActivity(intent);
            }
        });

        themes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stuff
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),LuckyWheelMain.class);
                startActivity(intent);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stuff
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://support@geass.technology"));
                startActivity(i);
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);

            }
        });



        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(Settings.this, AboutUs.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

    }
}
