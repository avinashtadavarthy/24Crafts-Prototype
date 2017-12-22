package com.twenty.four.crafts.auditions;

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

import com.twenty.four.crafts.R;

public class AuditionsTab extends Fragment {

    View myView;

    TabLayout auditions_tabs;
    ViewPager auditions_tabbed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_auditions_tab,container,false);

        getActivity().setTitle("Auditions");

        auditions_tabbed = (ViewPager) myView.findViewById(R.id.auditions_tabbed);
        auditions_tabbed.setAdapter(new AudAdapter(getFragmentManager(), getActivity().getApplicationContext()));

        auditions_tabs = (TabLayout) myView.findViewById(R.id.auditions_tabs);
        auditions_tabs.setupWithViewPager(auditions_tabbed);

        auditions_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                auditions_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                auditions_tabbed.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                auditions_tabbed.setCurrentItem(tab.getPosition());
            }
        });
        
        return myView;
    }

    private class AudAdapter extends FragmentStatePagerAdapter {

        private String fragments [] = {"Open","Closed","Applied"};

        public AudAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new OpenAuditionsFragment();
                case 1:
                    return new ClosedAuditionsFragment();
                case 2:
                    return new AppliedAuditionsFragment();
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
