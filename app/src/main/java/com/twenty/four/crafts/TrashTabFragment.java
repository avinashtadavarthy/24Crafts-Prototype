package com.twenty.four.crafts;

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

public class TrashTabFragment extends android.support.v4.app.Fragment {

    View myView;

    RecyclerView recycler_trash;
    LinearLayoutManager manage_trash;
    GridAdapter_Trash adapter_trash;

    String name[]={"Mont","rid","har","Ram","sid","sam","james","jarry"};
    String sub[]={"Hi","meeting","Materials","shootout","mall","meetup","meet","study"};
    String body[]={"long time","At 1030?","Bringing it to..","shot at 1045..","are you ..","will thecc..","is it..","I have.."};
    String time[]={"2h","3h","2h","7h","2h","2h","2h","2h"};
    int img[]={R.drawable.ima1,R.drawable.index,R.drawable.index,R.drawable.index,R.drawable.ima1,R.drawable.ima1,R.drawable.ima1,R.drawable.ima1};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.trash_tab,container,false);

        manage_trash = new LinearLayoutManager(getActivity());
        adapter_trash = new GridAdapter_Trash(getActivity(),name,sub,body,time,8,img);//no of iems is the arguement
        recycler_trash = (RecyclerView) myView.findViewById(R.id.recy_trash);
        recycler_trash.setLayoutManager(manage_trash);
        recycler_trash.setAdapter(adapter_trash);

        recycler_trash.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getActivity().getApplicationContext(), recycler_trash ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //body[position] is the body of mail in that clicked position
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        FloatingActionButton fab_trash = (FloatingActionButton) myView.findViewById(R.id.fab_trash);
        fab_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Will clear out all the messages in trash", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return myView;
    }

}
