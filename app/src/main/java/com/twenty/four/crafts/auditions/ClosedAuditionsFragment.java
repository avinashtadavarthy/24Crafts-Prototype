package com.twenty.four.crafts.auditions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twenty.four.crafts.DataModel;
import com.twenty.four.crafts.R;

import java.util.ArrayList;

/**
 * Created by Avinash Tadavarthy on 05-Nov-17.
 */

public class ClosedAuditionsFragment extends Fragment {

    View myView;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_client_dashboard_layout2,container,false);


        myOnClickListener = new ClosedAuditionsFragment.MyOnClickListener(getActivity().getApplicationContext());

        recyclerView = (RecyclerView) myView.findViewById(R.id.my_recycler_view_clientdashboard);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<>();
        for (int i = 0; i < MyData.nameArray.length-1; i++) {
            data.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.versionArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new CustomAdapterClosedAuditions(data);
        recyclerView.setAdapter(adapter);


        return myView;
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private MyOnClickListener(Context context) {
        }

        @Override
        public void onClick(View view) {

        }
    }

}
