package com.twenty.four.crafts.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;
import com.twenty.four.crafts.R;

import java.util.ArrayList;

public class OnBoardingPage extends AppCompatActivity {

    String fromhere, fromwhom;

    FloatingActionButton nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_page);

        fromhere = getIntent().getStringExtra("fromhere");
        fromwhom = getIntent().getStringExtra("fromwhom");

        nextbtn = (FloatingActionButton) findViewById(R.id.nextbtn);
        nextbtn.setVisibility(View.GONE);


        PaperOnboardingPage scr1 = new PaperOnboardingPage("Hotels",
                "All hotels and hostels are sorted by hospitality rating",
                Color.parseColor("#678FB4"), R.drawable.artdirector, R.drawable.edit_white);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Banks",
                "We carefully verify all banks before add them into the app",
                Color.parseColor("#65B0B4"), R.drawable.filmdirectors, R.drawable.heart_outline);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Stores",
                "All local stores are categorized for your convenience",
                Color.parseColor("#9B90BC"), R.drawable.com_facebook_button_icon_white, R.drawable.ic_check_white_48dp);
        PaperOnboardingPage scr4 = new PaperOnboardingPage("Welcome to 24crafts",
                "Hello, " + getSPData("name") + "\n Get ready to open your door to stardom!",
                Color.parseColor("#808080"), R.drawable.classicalsingers, R.drawable.ic_warning_outline_white);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        elements.add(scr4);

        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.onboard_container, onBoardingFragment);
        fragmentTransaction.commit();


        onBoardingFragment.setOnChangeListener(new PaperOnboardingOnChangeListener() {
            @Override
            public void onPageChanged(int prev, int present) {
                if(present == 3) {
                    nextbtn.setVisibility(View.VISIBLE);
                }
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(OnBoardingPage.this, Verification.class)
                        .putExtra("fromhere", fromhere)
                        .putExtra("fromwhom", fromwhom);
                startActivity(next);
            }
        });

        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                Intent next = new Intent(OnBoardingPage.this, Verification.class)
                        .putExtra("fromhere", fromhere)
                        .putExtra("fromwhom", fromwhom);
                startActivity(next);
            }
        });

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

}
