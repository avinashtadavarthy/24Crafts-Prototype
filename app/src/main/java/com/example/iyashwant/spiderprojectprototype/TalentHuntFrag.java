package com.example.iyashwant.spiderprojectprototype;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class TalentHuntFrag extends android.support.v4.app.Fragment {
    RecyclerView recycler;
    LinearLayoutManager manage;
    TalentHuntAdapter adapter;
    Talent_support_class class_object;


    ArrayList<Talent_support_class> data=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_talenthuntfrag, container, false);

        manage=new LinearLayoutManager(getActivity());
        adapter=new TalentHuntAdapter(getActivity(),data);
        //the 5th arguement is a number based on which a certain color code is set. The color code corresponding to each number can be set in the Grid_adapter class onBindViewHolder method and the appropriate parametre passed
        data.add(new Talent_support_class(R.drawable.female1,R.drawable.female2,R.drawable.male1,0,"Actress"));
        data.add(new Talent_support_class(R.drawable.female2,R.drawable.female1,R.drawable.male2,2,"Actor"));
        data.add(new Talent_support_class(R.drawable.male1,R.drawable.male2,R.drawable.female1,1,"Singer"));
        data.add(new Talent_support_class(R.drawable.male1,R.drawable.male1,R.drawable.female1,0,"Dancer"));
        data.add(new Talent_support_class(R.drawable.female1,R.drawable.male2,R.drawable.female1,1,"Choreo"));
        data.add(new Talent_support_class(R.drawable.male1,R.drawable.female2,R.drawable.female1,2,"Side artists"));

        adapter.notifyDataSetChanged();
        recycler= (RecyclerView) v.findViewById(R.id.recy);
        recycler.setLayoutManager(manage);
        recycler.setAdapter(adapter);
        setHasOptionsMenu(true);

        return v;
    }






}
