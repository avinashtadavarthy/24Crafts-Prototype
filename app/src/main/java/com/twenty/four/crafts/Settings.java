package com.twenty.four.crafts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.twenty.four.crafts.registration.Verification;


public class Settings extends AppCompatActivity {

    LinearLayout info, verify, coinsfree, morecoins, daily, contactus, aboutus;
    View baccinfo, bverify, bfreecoins, bbuymorecoins;
    Switch darkSwitch;
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
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.b2));


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


        if (type.equals("clients")) {
            coinsfree.setVisibility(View.GONE);
            morecoins.setVisibility(View.GONE);
            daily.setVisibility(View.GONE);
            bfreecoins.setVisibility(View.GONE);
            bbuymorecoins.setVisibility(View.GONE);
            verify.setVisibility(View.GONE);
        }

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_enter, R.anim.left_out);
            }
        });

        if (type.equals("crafts")) {
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Verification.class).putExtra("fromhere", "FromSettings");
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);
                }
            });

        } else if (type.equals("clients")) {
            //stuff
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), VerifyYourself.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_enter, R.anim.left_out);
                }
            });
        }

        morecoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PurchaseCoins.class);
                startActivity(intent);
            }
        });
        coinsfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FreeCoins.class);
                startActivity(intent);
            }
        });


        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LuckyWheelMain.class);
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
        darkSwitch = findViewById(R.id.darkSwitch);
        if (sharedPref.loadNightModeState()) {
            darkSwitch.setChecked(true);
        }
        darkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    sharedPref.setNightModeState(true);
                    recreate();
                } else {
                    sharedPref.setNightModeState(false);
                    recreate();
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Shared Preferences
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(String key) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }


    public void goBackSettings(View view) {
        onBackPressed();
    }


}
