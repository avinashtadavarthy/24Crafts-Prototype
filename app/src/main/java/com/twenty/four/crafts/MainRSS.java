package com.twenty.four.crafts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainRSS extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener{

    View myView;

    String chosenURL;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.rss_main,container,false);
        Bundle bundle = this.getArguments();

        if(this.getArguments() != null)
        chosenURL = bundle.getString("chosenURL");

        User.getInstance().navbarpos = 0;

        getActivity().setTitle("Newsfeed");
        recyclerView = myView.findViewById(R.id.recyclerview);
        swipeRefreshLayout = myView.findViewById(R.id.swipeLayout);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));




        ReadRss readRss = new ReadRss(getContext(), recyclerView, chosenURL,swipeRefreshLayout);
        readRss.execute();

        return myView;
    }

    @Override
    public void onRefresh() {
        new ReadRss(getContext(),recyclerView,chosenURL,swipeRefreshLayout).execute();
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
