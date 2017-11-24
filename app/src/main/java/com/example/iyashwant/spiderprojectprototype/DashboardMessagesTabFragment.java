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

public class DashboardMessagesTabFragment extends Fragment {

    View myView;

    TabLayout client_messages_tabs;
    ViewPager client_messages_tabbed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_dashboard_messages_tab_fragment,container,false);

        client_messages_tabbed = (ViewPager) myView.findViewById(R.id.client_messages_tabbed);
        client_messages_tabbed.setAdapter(new DashboardMessagesTabFragment.MessagesTabFragmentAdapter(getChildFragmentManager(), getActivity().getApplicationContext()));

        client_messages_tabs = (TabLayout) myView.findViewById(R.id.client_messages_tabs);
        client_messages_tabs.setupWithViewPager(client_messages_tabbed);

        client_messages_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                client_messages_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                client_messages_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                client_messages_tabbed.setCurrentItem(tab.getPosition());
            }
        });

        return myView;
    }

    private class MessagesTabFragmentAdapter extends FragmentStatePagerAdapter {

        private String fragments [] = {"Inbox","Trash"};

        public MessagesTabFragmentAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new InboxTabFragment();
                case 1:
                    return new TrashTabFragment();
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
