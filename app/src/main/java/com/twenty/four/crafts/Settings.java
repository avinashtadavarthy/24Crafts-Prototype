package com.twenty.four.crafts;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.twenty.four.crafts.registration.Verification;

public class Settings extends AppCompatActivity {

    LinearLayout info, verify, coinsfree, morecoins, daily, contactus, aboutus;
    View baccinfo,bverify,bfreecoins,bbuymorecoins,bdaily;
    Switch darkSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Theme Stuff ---- Start
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.day);
        }
        else {setTheme(R.style.night);}
        //Theme Stuff ---- End

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String type = getIntent().getStringExtra("type");

        info = (LinearLayout) findViewById(R.id.info);
        verify = (LinearLayout) findViewById(R.id.verify);
        coinsfree = (LinearLayout) findViewById(R.id.coinsfree);
        morecoins = (LinearLayout) findViewById(R.id.morecoins);

        daily = (LinearLayout) findViewById(R.id.daily);
        contactus = (LinearLayout) findViewById(R.id.contactus);
        aboutus = (LinearLayout) findViewById(R.id.aboutus);

        baccinfo = findViewById(R.id.belowaccinfo);
        bverify = findViewById(R.id.belowverify);
        bfreecoins = findViewById(R.id.belowfreecoins);
        bbuymorecoins = findViewById(R.id.belowbuymorecoins);

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

        //Theme stuff #2
        darkSwitch=findViewById(R.id.darkSwitch);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            darkSwitch.setChecked(true);
        }
        darkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    refreshApp();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    refreshApp();
                }
            }
        });

    }
    //Theme stuff #3
    public void refreshApp(){
        Intent i = new Intent(getApplicationContext(), MainRSS.class);
        startActivity(i);
        finish();
    }
}
