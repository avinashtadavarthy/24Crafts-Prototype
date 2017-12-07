package com.example.iyashwant.spiderprojectprototype;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Avinash Tadavarthy on 05-Nov-17.
 */

public class ClientDashboard extends Fragment {

    View myView;

    TabLayout client_dashboard_tabs;
    ViewPager client_dashboard_tabbed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_client_dashboard,container,false);


        getActivity().setTitle("Dashboard");

        client_dashboard_tabbed = (ViewPager) myView.findViewById(R.id.client_dashboard_tabbed);
        client_dashboard_tabbed.setAdapter(new ClientDashboard.ClientDashboardAdapter(getFragmentManager(), getActivity().getApplicationContext()));

        client_dashboard_tabs = (TabLayout) myView.findViewById(R.id.client_dashboard_tabs);
        client_dashboard_tabs.setupWithViewPager(client_dashboard_tabbed);

        client_dashboard_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                client_dashboard_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                client_dashboard_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                client_dashboard_tabbed.setCurrentItem(tab.getPosition());
            }
        });

        return myView;
    }

    public class ClientDashboardAdapter extends FragmentStatePagerAdapter {

        public String fragments [] = {"Auditions", "My Favourites", "Messages"};

        public ClientDashboardAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new DashboardAuditionsTabFragment();
                case 1:
                    return new DashboardFavouritesTabFragment();
                case 2:
                    return new DashboardMessagesTabFragment();
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

