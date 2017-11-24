package com.example.iyashwant.spiderprojectprototype;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ClientDashboard extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);


        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_clientdashboard);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
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

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private MyOnClickListener(Context context) {
        }

        @Override
        public void onClick(View view) {

        }
    }
}

