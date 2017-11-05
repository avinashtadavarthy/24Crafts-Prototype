package com.example.iyashwant.spiderprojectprototype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Avinash Tadavarthy on 05-Nov-17.
 */

public class OpenAuditionsFragment extends Fragment {

    View myView;

    //all the code that goes before onCreate in an activity comes here

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.open_auditions_tab,container,false);


        //all the code that goes inside onCreate in an activity comes here


        return myView;
    }


    //all the code that comes after onCreate in an activity comes here


}
