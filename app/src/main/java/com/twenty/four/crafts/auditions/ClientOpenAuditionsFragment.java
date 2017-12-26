package com.twenty.four.crafts.auditions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.twenty.four.crafts.CreateAuditions;
import com.twenty.four.crafts.DataModel;
import com.twenty.four.crafts.ForgotPassword;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.RecyclerViewClickListener;
import com.twenty.four.crafts.registration.StartingScreen;

import java.util.ArrayList;

public class ClientOpenAuditionsFragment extends Fragment {

    View myView;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private FloatingActionButton createaudition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_client_dashboard_layout,container,false);


        myOnClickListener = new ClientOpenAuditionsFragment.MyOnClickListener(getActivity().getApplicationContext());

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

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onCLick(View view, int position) {

                Toast.makeText(getActivity().getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void delClick(ImageView delButton, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure you want to permanently delete the audition?")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }

            @Override
            public void editClick(ImageView editbutton, int position) {

            }
        };



        removedItems = new ArrayList<Integer>();

        adapter = new ClientCustomAdapterOpenAuditions(data,listener,getContext());
        recyclerView.setAdapter(adapter);


        createaudition = (FloatingActionButton) myView.findViewById(R.id.createaudition);
        createaudition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(),CreateAuditions.class);
                startActivity(i);
            }
        });

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
