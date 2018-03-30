package com.twenty.four.crafts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class EncountersGrid extends android.support.v4.app.Fragment {

    View myView;

    RecyclerView encounters_grid;

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

    SharedPref sharedPref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.encounters_grid_fragment,container,false);
        getActivity().setTitle("Encounters");
        setHasOptionsMenu(true);

        sharedPref = new SharedPref(getActivity().getApplicationContext());

        encounters_grid = (RecyclerView) myView.findViewById(R.id.encounters_grid);
        int numberOfColumns = 3;
        encounters_grid.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), numberOfColumns));
        FavsRecyclerAdapter adapter = new FavsRecyclerAdapter(getActivity().getApplicationContext(), mThumbIds);
        encounters_grid.setAdapter(adapter);
        encounters_grid.setNestedScrollingEnabled(true);

        encounters_grid.addOnItemTouchListener(new RecyclerItemClickListener(this.getActivity().getApplicationContext(), encounters_grid ,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent page = new Intent(getActivity().getApplicationContext(), ProfileView.class)
                                .putExtra("thisistogetback", "do nothing")
                                .putExtra("fromwhom", "do nothing")
                                .putExtra("viewingmyprofile", "false");
                        startActivity(page);
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );

        return myView;
    }

    //functions space
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_encounters_grid_off, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case  R.id.menu_grid_off:
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new EncountersMain()).commit();
                sharedPref.storeSPData(getActivity().getApplicationContext(), "encountersView", "GridOff");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
