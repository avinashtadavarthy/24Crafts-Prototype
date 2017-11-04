package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Contacts extends AppCompatActivity  {
    RecyclerView recycler;
    LinearLayoutManager manager;
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Intent intent = getIntent();
        final String occupation = intent.getExtras().getString("craft");//populate according to craft
        final List<ContactsHelper> obj_list = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.59.84.77:3000")//base url is what does not change for any query from app side
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Retrofitextract r = retrofit.create(Retrofitextract.class);
        Log.i("Failure",occupation);
        Call<List<ContactsHelper>> call= r.getLongUrl(occupation);
        adapter=new ContactsAdapter(this,obj_list);
        call.enqueue(new Callback<List<ContactsHelper>>() {
            @Override
            public void onResponse(Call<List<ContactsHelper>> call, Response<List<ContactsHelper>> response) {


                obj_list.addAll(response.body());
                Log.i("Resp : ", String.valueOf(response.body().size()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ContactsHelper>> call, Throwable t) {
                Log.i("Failure2",occupation);

            }
        });





        TextView head = (TextView) findViewById(R.id.heading);
        head.setText(occupation);
        recycler = (RecyclerView)findViewById(R.id.rv3);
        manager=new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }



}
