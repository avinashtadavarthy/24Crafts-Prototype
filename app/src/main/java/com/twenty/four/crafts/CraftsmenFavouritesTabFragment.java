package com.twenty.four.crafts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class CraftsmenFavouritesTabFragment extends android.support.v4.app.Fragment {

    View myView;

    GridView favs_grid;
    int[] mThumbIds = {
            R.drawable.sample1, R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1, R.drawable.sample1
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_dashboard_favourites_tab_fragment,container,false);

        getActivity().setTitle("Favourites");

        favs_grid = (GridView) myView.findViewById(R.id.favs_grid);
        FavsAdapter adapter = new FavsAdapter(getActivity().getApplicationContext(), mThumbIds);
       favs_grid.setAdapter(adapter);

       favs_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Intent page = new Intent(getActivity().getApplicationContext(), ProfileView.class)
                       .putExtra("thisistogetback", "do nothing")
                       .putExtra("fromwhom", "do nothing");
               startActivity(page);

           }
       });

        return myView;
    }
}
