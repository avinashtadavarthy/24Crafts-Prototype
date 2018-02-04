package com.twenty.four.crafts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class NewsfeedFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    View myView;

    Button bn1, bn2, bn3, bn4, bn5, bn6, bn7;
    FrameLayout content_frame_newsfeed;

    String chosenURL;


    Bundle bundle;
    Fragment frag;
    android.support.v4.app.FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.newsfeed_fragment,container,false);

        bundle = new Bundle();
        frag = new MainRSS();
        fragmentManager = getFragmentManager();

        getActivity().setTitle("Newsfeed");

        bn1 = (Button) myView.findViewById(R.id.bn1);
        bn1.setOnClickListener(this);
        bn2 = (Button) myView.findViewById(R.id.bn2);
        bn2.setOnClickListener(this);
        bn3 = (Button) myView.findViewById(R.id.bn3);
        bn3.setOnClickListener(this);
        bn4 = (Button) myView.findViewById(R.id.bn4);
        bn4.setOnClickListener(this);
        bn5 = (Button) myView.findViewById(R.id.bn5);
        bn5.setOnClickListener(this);
        bn6 = (Button) myView.findViewById(R.id.bn6);
        bn6.setOnClickListener(this);
        bn7 = (Button) myView.findViewById(R.id.bn7);
        bn7.setOnClickListener(this);

        content_frame_newsfeed = myView.findViewById(R.id.content_frame_newsfeed);


        //setting by default
        bn1.setBackgroundResource(R.drawable.button_box_selected);
        bn2.setBackgroundResource(R.drawable.button_box);
        bn3.setBackgroundResource(R.drawable.button_box);
        bn4.setBackgroundResource(R.drawable.button_box);
        bn5.setBackgroundResource(R.drawable.button_box);
        bn6.setBackgroundResource(R.drawable.button_box);
        bn7.setBackgroundResource(R.drawable.button_box);

        chosenURL = "http://tamil.thehindu.com/tamilnadu/rss/";
        bundle.putString("chosenURL", chosenURL);
        frag.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.content_frame_newsfeed, frag).commit();



        return myView;
    }


    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        final Fragment frag = new MainRSS();
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {

            case R.id.bn1:

                bn1.setBackgroundResource(R.drawable.button_box_selected);
                bn2.setBackgroundResource(R.drawable.button_box);
                bn3.setBackgroundResource(R.drawable.button_box);
                bn4.setBackgroundResource(R.drawable.button_box);
                bn5.setBackgroundResource(R.drawable.button_box);
                bn6.setBackgroundResource(R.drawable.button_box);
                bn7.setBackgroundResource(R.drawable.button_box);

                chosenURL = "http://tamil.thehindu.com/tamilnadu/rss/";
                bundle.putString("chosenURL", chosenURL);
                frag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame_newsfeed, frag).commit();

                break;

            case R.id.bn2:

                bn1.setBackgroundResource(R.drawable.button_box);
                bn2.setBackgroundResource(R.drawable.button_box_selected);
                bn3.setBackgroundResource(R.drawable.button_box);
                bn4.setBackgroundResource(R.drawable.button_box);
                bn5.setBackgroundResource(R.drawable.button_box);
                bn6.setBackgroundResource(R.drawable.button_box);
                bn7.setBackgroundResource(R.drawable.button_box);

                chosenURL = "http://www.bbc.co.uk/tamil/india/index.xml";
                bundle.putString("chosenURL", chosenURL);
                frag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame_newsfeed, frag).commit();

                break;

            case R.id.bn3:

                bn1.setBackgroundResource(R.drawable.button_box);
                bn2.setBackgroundResource(R.drawable.button_box);
                bn3.setBackgroundResource(R.drawable.button_box_selected);
                bn4.setBackgroundResource(R.drawable.button_box);
                bn5.setBackgroundResource(R.drawable.button_box);
                bn6.setBackgroundResource(R.drawable.button_box);
                bn7.setBackgroundResource(R.drawable.button_box);

                chosenURL = "http://zeenews.india.com/tamil/chennai.xml";
                bundle.putString("chosenURL", chosenURL);
                frag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame_newsfeed, frag).commit();

                break;

            case R.id.bn4:

                bn1.setBackgroundResource(R.drawable.button_box);
                bn2.setBackgroundResource(R.drawable.button_box);
                bn3.setBackgroundResource(R.drawable.button_box);
                bn4.setBackgroundResource(R.drawable.button_box_selected);
                bn5.setBackgroundResource(R.drawable.button_box);
                bn6.setBackgroundResource(R.drawable.button_box);
                bn7.setBackgroundResource(R.drawable.button_box);

                chosenURL = "https://tamil.filmibeat.com/rss/filmibeat-tamil-fb.xml";
                bundle.putString("chosenURL", chosenURL);
                frag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame_newsfeed, frag).commit();

                break;

            case R.id.bn5:

                bn1.setBackgroundResource(R.drawable.button_box);
                bn2.setBackgroundResource(R.drawable.button_box);
                bn3.setBackgroundResource(R.drawable.button_box);
                bn4.setBackgroundResource(R.drawable.button_box);
                bn5.setBackgroundResource(R.drawable.button_box_selected);
                bn6.setBackgroundResource(R.drawable.button_box);
                bn7.setBackgroundResource(R.drawable.button_box);

                chosenURL = "https://tamil.oneindia.com/rss/tamil-news-fb.xml";
                bundle.putString("chosenURL", chosenURL);
                frag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame_newsfeed, frag).commit();

                break;

            case R.id.bn6:

                bn1.setBackgroundResource(R.drawable.button_box);
                bn2.setBackgroundResource(R.drawable.button_box);
                bn3.setBackgroundResource(R.drawable.button_box);
                bn4.setBackgroundResource(R.drawable.button_box);
                bn5.setBackgroundResource(R.drawable.button_box);
                bn6.setBackgroundResource(R.drawable.button_box_selected);
                bn7.setBackgroundResource(R.drawable.button_box);

                chosenURL = "http://cinema.dinamalar.com/rss.php";
                bundle.putString("chosenURL", chosenURL);
                frag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame_newsfeed, frag).commit();

                break;

            case R.id.bn7:

                bn1.setBackgroundResource(R.drawable.button_box);
                bn2.setBackgroundResource(R.drawable.button_box);
                bn3.setBackgroundResource(R.drawable.button_box);
                bn4.setBackgroundResource(R.drawable.button_box);
                bn5.setBackgroundResource(R.drawable.button_box);
                bn6.setBackgroundResource(R.drawable.button_box);
                bn7.setBackgroundResource(R.drawable.button_box_selected);

                chosenURL = "http://cinema.dinakaran.com/Rss/RssKollywood.aspx";
                bundle.putString("chosenURL", chosenURL);
                frag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame_newsfeed, frag).commit();

                break;

        }

    }


}
