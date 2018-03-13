package com.twenty.four.crafts.registration;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;
import com.twenty.four.crafts.ProfileView;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.SecondFragment;

import java.util.ArrayList;

public class OnBoardingPage extends AppCompatActivity {

    String togetback, fromwhom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_page);

        togetback = getIntent().getStringExtra("thisistogetback");
        fromwhom = getIntent().getStringExtra("fromwhom");


        PaperOnboardingPage scr1 = new PaperOnboardingPage("Hotels",
                "All hotels and hostels are sorted by hospitality rating",
                Color.parseColor("#678FB4"), R.drawable.artdirector, R.drawable.edit_white);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Banks",
                "We carefully verify all banks before add them into the app",
                Color.parseColor("#65B0B4"), R.drawable.filmdirectors, R.drawable.heart_outline);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Stores",
                "All local stores are categorized for your convenience",
                Color.parseColor("#9B90BC"), R.drawable.com_facebook_button_icon_white, R.drawable.ic_check_white_48dp);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.onboard_container, onBoardingFragment);
        fragmentTransaction.commit();



        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                Intent next = new Intent(OnBoardingPage.this, ProfileView.class)
                        .putExtra("thisistogetback", togetback)
                        .putExtra("fromwhom", fromwhom);
                startActivity(next);
            }
        });

    }
}
