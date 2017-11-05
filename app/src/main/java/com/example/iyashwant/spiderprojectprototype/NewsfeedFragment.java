package com.example.iyashwant.spiderprojectprototype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class NewsfeedFragment extends android.support.v4.app.Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.second_layout,container,false);
        return myView;
    }
}
