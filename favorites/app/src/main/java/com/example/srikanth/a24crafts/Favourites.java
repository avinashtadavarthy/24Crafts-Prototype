package com.example.srikanth.a24crafts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Favourites extends AppCompatActivity {

    RecyclerView recycle;
    GridLayoutManager grid_manage;
    Favs_GridAdapter gridAdapter;
    ArrayList<Integer> img_id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        gridAdapter=new Favs_GridAdapter(this,img_id);
        recycle=(RecyclerView) findViewById(R.id.thegrid);
        grid_manage= new GridLayoutManager(getApplicationContext(),mNoOfColumns+1);
        recycle.setLayoutManager(grid_manage);
        recycle.setAdapter(gridAdapter);

        for(int i=0;i<21;i++) {

            img_id.add(R.drawable.sample1);// insert pics here
            gridAdapter.notifyItemInserted(i);
        }

        gridAdapter.notifyDataSetChanged();//notify adapter


        //please dont forget this

    }

}
