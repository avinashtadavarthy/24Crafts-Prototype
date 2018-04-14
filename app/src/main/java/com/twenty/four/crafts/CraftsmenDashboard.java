package com.twenty.four.crafts;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.twenty.four.crafts.auditions.AuditionsTab;

public class CraftsmenDashboard extends Fragment {

    View myView;

    TabLayout craftsmen_dashboard_tabs;
    ViewPager craftsmen_dashboard_tabbed;
    SharedPref sharedPref;
    
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_craftsmen_dashboard, container, false);

        getActivity().setTitle("Dashboard");

        craftsmen_dashboard_tabbed = (ViewPager) myView.findViewById(R.id.craftsmen_dashboard_tabbed);
        craftsmen_dashboard_tabbed.setAdapter(new CraftsmenDashboard.CraftsmenDashboardAdapter(getChildFragmentManager(), getActivity().getApplicationContext()));

        craftsmen_dashboard_tabs = (TabLayout) myView.findViewById(R.id.craftsmen_dashboard_tabs);
        craftsmen_dashboard_tabs.setupWithViewPager(craftsmen_dashboard_tabbed);

        String getData = getArguments().getString("tab");
        if (getData != null) {
            int data = 0;
            if(getData.equals("audition"))
            {
                craftsmen_dashboard_tabbed.setCurrentItem(0);
                User.getInstance().navbarposclient = 1;
                data = 1;
            }
            else if(getData.equals("fav"))
            {
                craftsmen_dashboard_tabbed.setCurrentItem(1);
                User.getInstance().navbarposclient = 2;
                data = 2;
            }
            else if(getData.equals("message"))
            {
                craftsmen_dashboard_tabbed.setCurrentItem(2);
                User.getInstance().navbarposclient = 3;
                data = 3;
            }
            Intent intent = new Intent(getActivity().getApplicationContext(), Main2Activity.class);
            intent.putExtra("navbarposclient",data);
        }

        craftsmen_dashboard_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                craftsmen_dashboard_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                craftsmen_dashboard_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                craftsmen_dashboard_tabbed.setCurrentItem(tab.getPosition());
            }
        });


        return myView;
    }



    public class CraftsmenDashboardAdapter extends FragmentStatePagerAdapter {

        public String fragments [] = {"Auditions", "Favourites", "Messages"};

        public CraftsmenDashboardAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new AuditionsTab();
                case 1:
                    return new CraftsmenFavouritesTabFragment();
                case 2:
                    return new InboxTab();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }


}
