package com.example.iyashwant.spiderprojectprototype;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class InboxTabFragment extends android.support.v4.app.Fragment {

    View myView;

    RecyclerView recycler;
    LinearLayoutManager manage;
    GridAdapter adapter;

    String name[]={"Mont","rid","har","Ram","sid","sam","james","jarry"};
    String sub[]={"Hi","meeting","Materials","shootout","mall","meetup","meet","study"};
    String body[]={"long time","At 1030?","Bringing it to..","shot at 1045..","are you ..","will thecc..","is it..","I have.."};
    String time[]={"2h","3h","2h","7h","2h","2h","2h","2h"};
    int img[]={R.drawable.ima1,R.drawable.index,R.drawable.index,R.drawable.index,R.drawable.ima1,R.drawable.ima1,R.drawable.ima1,R.drawable.ima1};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.inbox_tab,container,false);

        manage=new LinearLayoutManager(getActivity());
        adapter=new GridAdapter(getActivity(),name,sub,body,time,8,img);//no of iems is the arguement
        recycler= (RecyclerView) myView.findViewById(R.id.recy);
        recycler.setLayoutManager(manage);
        recycler.setAdapter(adapter);
        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getActivity().getApplicationContext(), recycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //body[position] is the body of mail in that clicked position

                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        FloatingActionButton fab = (FloatingActionButton) myView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Need to add 'create messages' page", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return myView;
    }

}
