package com.example.iyashwant.spiderprojectprototype;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class DashboardFavouritesTabFragment extends android.support.v4.app.Fragment {

    View myView;

    RecyclerView favs_grid;
    FavsAdapter adapter;
    ArrayList<Integer> img_id = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_dashboard_favourites_tab_fragment,container,false);

        favs_grid = (RecyclerView) myView.findViewById(R.id.favs_grid);
        adapter = new FavsAdapter(getActivity().getApplicationContext(),img_id);

        favs_grid.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),3));
        favs_grid.setAdapter(adapter);

        for(int i=0;i<21;i++) {

            img_id.add(R.drawable.sample1);// insert pics here
            adapter.notifyItemInserted(i);
        }

        adapter.notifyDataSetChanged();//notify adapter

        return myView;
    }
}
