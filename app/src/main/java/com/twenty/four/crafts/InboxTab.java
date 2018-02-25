package com.twenty.four.crafts;

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

public class InboxTab extends Fragment {

    View myView;

    TabLayout inbox_tabs;
    ViewPager inbox_tabbed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_inbox_tab,container,false);

        User.getInstance().navbarpos = 3;

        inbox_tabbed = (ViewPager) myView.findViewById(R.id.inbox_tabbed);
        inbox_tabbed.setAdapter(new InboxTab.InboxAdapter(getChildFragmentManager(), getActivity().getApplicationContext()));

        inbox_tabs = (TabLayout) myView.findViewById(R.id.inbox_tabs);
        inbox_tabs.setupWithViewPager(inbox_tabbed);

        inbox_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                inbox_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                inbox_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                inbox_tabbed.setCurrentItem(tab.getPosition());
            }
        });

        return myView;
    }

    private class InboxAdapter extends FragmentStatePagerAdapter {

        private String fragments [] = {"Inbox","Trash"};

        public InboxAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
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
