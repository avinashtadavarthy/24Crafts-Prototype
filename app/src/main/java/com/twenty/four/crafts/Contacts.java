package com.twenty.four.crafts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.twenty.four.crafts.registration.Verification;
import com.twenty.four.crafts.registration.signup3;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Contacts extends AppCompatActivity  {
    RecyclerView recycler;
    LinearLayoutManager manager;
    ContactsAdapter adapter;

    String occupation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Intent intent = getIntent();

        occupation = intent.getExtras().getString("craft");//populate according to craft

        /*final List<ContactsHelper> obj_list = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://24crafts.cf:3001/")//base url is what does not change for any query from app side
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofitextract r = retrofit.create(Retrofitextract.class);

        Log.i("Failure", occupation);
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

*/



        //to get the data
        populateArray();
    }



    void populateArray() {
        String newurl = "http://24crafts.cf:3001/" + occupation;

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, newurl, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                adapter = new ContactsAdapter(Contacts.this, response);

                TextView head = (TextView) findViewById(R.id.heading);
                head.setText(occupation);

                recycler = (RecyclerView)findViewById(R.id.rv3);
                manager = new LinearLayoutManager(getApplicationContext());
                recycler.setLayoutManager(manager);
                recycler.setAdapter(adapter);

                adapter.notifyDataSetChanged();

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(getRequest);

    }



}
