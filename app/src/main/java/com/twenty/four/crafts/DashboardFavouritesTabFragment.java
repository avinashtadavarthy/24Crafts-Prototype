package com.twenty.four.crafts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class DashboardFavouritesTabFragment extends android.support.v4.app.Fragment {

    View myView;

    RecyclerView favs_grid;

    String[] mThumbIds = {
            "https://homepages.cae.wisc.edu/~ece533/images/airplane.png", "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", "https://homepages.cae.wisc.edu/~ece533/images/baboon.png",
            "https://homepages.cae.wisc.edu/~ece533/images/barbara.bmp", "https://homepages.cae.wisc.edu/~ece533/images/boat.png", "https://homepages.cae.wisc.edu/~ece533/images/boy.bmp",
            "https://homepages.cae.wisc.edu/~ece533/images/cat.png", "https://homepages.cae.wisc.edu/~ece533/images/fruits.png", "https://homepages.cae.wisc.edu/~ece533/images/frymire.png",
            "https://homepages.cae.wisc.edu/~ece533/images/girl.png", "https://homepages.cae.wisc.edu/~ece533/images/goldhill.png", "https://homepages.cae.wisc.edu/~ece533/images/lena.png",
            "https://homepages.cae.wisc.edu/~ece533/images/monarch.png", "https://homepages.cae.wisc.edu/~ece533/images/mountain.png", "https://homepages.cae.wisc.edu/~ece533/images/peppers.png",
            "https://homepages.cae.wisc.edu/~ece533/images/serrano.png", "https://source.unsplash.com/Xq1ntWruZQI/600x800", "https://source.unsplash.com/NYyCqdBOKwc/600x800",
            "https://source.unsplash.com/buF62ewDLcQ/600x800", "https://source.unsplash.com/THozNzxEP3g/600x800", "https://source.unsplash.com/USrZRcRS2Lw/600x800",
            "https://source.unsplash.com/PeFk7fzxTdk/600x800", "https://source.unsplash.com/LrMWHKqilUw/600x800", "https://source.unsplash.com/HN-5Z6AmxrM/600x800",
            "https://source.unsplash.com/CdVAUADdqEc/600x800", "https://source.unsplash.com/AWh9C-QjhE4/600x800"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_dashboard_favourites_tab_fragment,container,false);

        favs_grid = (RecyclerView) myView.findViewById(R.id.favs_grid);
        int numberOfColumns = 3;
        favs_grid.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), numberOfColumns));
        FavsRecyclerAdapter adapter = new FavsRecyclerAdapter(getActivity().getApplicationContext(), mThumbIds);
        favs_grid.setAdapter(adapter);
        favs_grid.setNestedScrollingEnabled(true);

        favs_grid.addOnItemTouchListener(new RecyclerItemClickListener(this.getActivity().getApplicationContext(), favs_grid ,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent page = new Intent(getActivity().getApplicationContext(), ProfileView.class)
                                .putExtra("thisistogetback", "do nothing")
                                .putExtra("fromwhom", "do nothing");
                        startActivity(page);
                        
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );

        return myView;
    }
}
