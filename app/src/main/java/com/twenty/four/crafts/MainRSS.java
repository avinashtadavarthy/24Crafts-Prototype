package com.twenty.four.crafts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainRSS extends android.support.v4.app.Fragment {

    View myView;

    String chosenURL;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.rss_main,container,false);
        Bundle bundle = this.getArguments();

        if(this.getArguments() != null)
        chosenURL = bundle.getString("chosenURL");

        User.getInstance().navbarpos = 0;

        getActivity().setTitle("Newsfeed");
        RecyclerView recyclerView = myView.findViewById(R.id.recyclerview);



        ReadRss readRss = new ReadRss(getContext(), recyclerView, chosenURL);
        readRss.execute();

        return myView;
    }


    /*  RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_main);

        recyclerView = findViewById(R.id.recyclerview);

        ReadRss readRss = new ReadRss(this,recyclerView);
        readRss.execute();
    }*/
}
