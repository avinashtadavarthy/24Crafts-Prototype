package com.twenty.four.crafts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class CraftsmenAppliedAuditionsFrag extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View myView;

    private FloatingActionButton createaudition;
    SwipeRefreshLayout swipeRefreshLayout;
    FoldingCellListAdapter adapter;
    ArrayList<Item> items = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.from(container.getContext()).inflate(R.layout.activity_auditions_main,container,false);

        ListView theListView = (ListView) myView.findViewById(R.id.mainListView);
        swipeRefreshLayout = myView.findViewById(R.id.swipe_container);


        AndroidNetworking.initialize(getActivity().getApplicationContext());
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));

        // prepare elements to display


       /* // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });*/

       if(getSPData("viewAppliedAuditions").equals("") || getSPData("viewAppliedAuditions").equals(null))
       {

           items = Item.getTestingList(getActivity().getApplicationContext(), "CraftsmenAppliedAuditions");
           adapter = new FoldingCellListAdapter(getActivity().getApplicationContext(), items,getActivity());
           theListView.setAdapter(adapter);


           theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                   // toggle clicked cell state
                   ((FoldingCell) view).toggle(false);
                   // register in adapter that state for selected cell is toggled
                   adapter.registerToggle(pos);
               }
           });
       }

       else
       {

           items = setFoldingAdapter(getSPData("viewAppliedAuditions"));
           adapter = new FoldingCellListAdapter(getActivity().getApplicationContext(), items,getActivity());
           theListView.setAdapter(adapter);

           theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                   // toggle clicked cell state
                   ((FoldingCell) view).toggle(false);
                   // register in adapter that state for selected cell is toggled
                   adapter.registerToggle(pos);
               }
           });


       }
        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)


       /* // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();

            }
        });*/

        // set elements to adapter


        // set on click event listener to list view


        createaudition = (FloatingActionButton) myView.findViewById(R.id.createaudition);
        createaudition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(),CreateAuditions.class);
                startActivity(i);
            }
        });

        String isClient = "";
        String userdata =  getSPData("userdatamain");
        try {
            JSONObject object = new JSONObject(userdata);
            isClient = object.optString("isClient");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(isClient.equals("false"))
            createaudition.setVisibility(View.GONE);

        return myView;
    }


    @Override
    public void onResume() {
        super.onResume();

      /*  final ArrayList<Item> items = Item.getTestingList(getActivity().getApplicationContext(), "CraftsmenOpenAuditions");
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(getActivity().getApplicationContext(), items);
        theListView.setAdapter(adapter);*/

       // getActivity().recreate();
    }

    //Shared Preferences
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = getActivity().getApplicationContext().getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(String key) {

        SharedPreferences mUserData = getActivity().getApplicationContext().getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }

    @Override
    public void onRefresh() {

        //storeSPData("viewAppliedAuditions","");


        items = Item.getTestingList2(getActivity().getApplicationContext(), "CraftsmenAppliedAuditions",getActivity());
        adapter = new FoldingCellListAdapter(getActivity().getApplicationContext(), items,getActivity());
       // getActivity().recreate();



    }


    public ArrayList<Item> setFoldingAdapter(String response)
    {

        final ArrayList<Item> items = new ArrayList<>();

        try {

            JSONArray responseSharedPref = new JSONArray(response);


            for(int i=0; i<responseSharedPref.length(); i++) {
                JSONObject jsonObject = responseSharedPref.getJSONObject(i);

                String id = jsonObject.optString("_id"),
                        location = jsonObject.optString("auditionLocation"),
                        auditionDate = User.getInstance().getFormattedDate(jsonObject.optString("auditionDate")),
                        auditionTime = jsonObject.optString("auditionTime"),
                        projectName = jsonObject.optString("title"),
                        projectType = jsonObject.optString("projectType"),
                        description = jsonObject.optString("description"),
                        innerPhoneNumber = jsonObject.optString("contactNo"),
                        innerName = jsonObject.optString("senderName"),
                        innerApplnFrom = User.getInstance().getFormattedDate(jsonObject.optString("applicationFromDate")),
                        innerApplnTo = User.getInstance().getFormattedDate(jsonObject.optString("applicationToDate")),
                        innerAuditionLocation = jsonObject.optString("auditionLocation"),
                        innerProjectDescription = jsonObject.optString("description"),
                        innerImageURL = jsonObject.optString("auditionImageURL"),
                        innerSenderImageURL = jsonObject.optString("senderProfileImage");

                String auditionDateFinal = User.getInstance().getDate(auditionDate);

                innerImageURL = "hello";
                innerSenderImageURL = "hey";

                items.add(new Item(id, location, auditionDateFinal, auditionTime, projectName, projectType, description, innerPhoneNumber, innerName, innerApplnFrom, innerApplnTo, innerAuditionLocation, innerProjectDescription, innerImageURL, innerSenderImageURL));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return items;

    }









}



   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditions_main);

        // get our list view
        ListView theListView = (ListView) findViewById(R.id.mainListView);

        // prepare elements to display
        final ArrayList<Item> items = Item.getTestingList();

        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        theListView.setAdapter(adapter);

        // set on click event listener to list view
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });

    }*/

