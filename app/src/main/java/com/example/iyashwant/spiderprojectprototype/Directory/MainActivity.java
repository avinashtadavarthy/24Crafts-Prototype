package com.example.iyashwant.spiderprojectprototype.Directory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.iyashwant.spiderprojectprototype.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GridAdapter adapter;
    String[] desc = {"Ad and Corporate Filmmakers","Advertising and Marketing","Agency","Art Directors","Audigraphers","Auditorium","Dance","Directors",
            "Cine Banners","Cine Designers","Cinema Laboratories","Cinematographers", "Classical Singers","Colour Lab","Costumers","Dialogue Writers",
            "Dubbing and Recording Studios","Dubbing Artist","Editing Studio-Editing Suite","Event Managers","Exhibitors Associations","Film Directors","Film Distributors","Film Editors",
    "Film Mediators","Film Producers","Film Writers","Graphic Designers","Hotels","Journalists","Journals(Websites)","Journals","Light Music Troupe",
    "Lyric Writers","Makeup Men & Hairdressers","Modelling and Portfolio","Music Directors","Music Incharge","Outdoor units",
    "Playback Singers","Press Photographers","Preview Theatres","PRO","Production Executive","Recording Studios","Script Writers",
    "Shooting House","Still Photographers","Studios","Stunt Masters","TV Serial Directors","TV Serial Producers","Unions and Associations",
            "Video Post Production Studios"};
    int[] img_id = {R.drawable.ad,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,
            R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,
            R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,
            R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,
            R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,
            R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,
            R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,
            R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def,R.drawable.def};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.i("Hello",desc[position]);//returns fine
                        Intent I = new Intent(MainActivity.this,contacts.class);
                        I.putExtra("craft",desc[position]);
                        startActivity(I);
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new GridAdapter(this,desc,img_id);///////////////////////////////////////////////
        recyclerView.setAdapter(adapter);
    }
}
