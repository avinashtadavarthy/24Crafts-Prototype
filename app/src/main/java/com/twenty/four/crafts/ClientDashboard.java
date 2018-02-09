package com.twenty.four.crafts;

import android.content.Context;
import android.content.Intent;
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

import com.twenty.four.crafts.auditions.DashboardAuditionsTabFragment;

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

        String getData = getArguments().getString("tab");
        if (getData != null) {
            int data = 0;
            if(getData.equals("audition"))
            {
                client_dashboard_tabbed.setCurrentItem(0);
                User.getInstance().navbarposclient = 1;
                data = 1;
            }
            else if(getData.equals("fav"))
            {
                client_dashboard_tabbed.setCurrentItem(1);
                User.getInstance().navbarposclient = 2;
                data = 2;
            }
            else if(getData.equals("message"))
            {
                client_dashboard_tabbed.setCurrentItem(2);
                User.getInstance().navbarposclient = 3;
                data = 3;
            }
            Intent intent = new Intent(getActivity().getApplicationContext(),Main3Activity.class);
            intent.putExtra("navbarposclient",data);
        }

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

        public String fragments [] = {"Auditions", "Favourites", "Messages"};

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

