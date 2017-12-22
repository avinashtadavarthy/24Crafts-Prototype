package com.example.iyashwant.spiderprojectprototype.auditions;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by rakesh on 24/11/17.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.DataModel;
import com.example.iyashwant.spiderprojectprototype.R;
import com.example.iyashwant.spiderprojectprototype.RecyclerViewClickListener;
import com.mindorks.placeholderview.annotations.swipe.SwipeTouch;

public class ClientCustomAdapterOpenAuditions extends RecyclerView.Adapter<ClientCustomAdapterOpenAuditions.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    private RecyclerViewClickListener mListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewlocationname;
        TextView textViewnoofapplicants;
        ImageView imageViewIcon;
        Switch audition_switch;
        private RecyclerViewClickListener mListener;

        public MyViewHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            audition_switch = itemView.findViewById(R.id.audition_switch);
            this.textViewlocationname = (TextView) itemView.findViewById(R.id.LocationText);
            this.textViewnoofapplicants = (TextView) itemView.findViewById(R.id.applicantsText);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.dashboardimage);
        }

        @Override
        public void onClick(View view) {
            mListener.onCLick(audition_switch,getAdapterPosition());
        }
    }

    public ClientCustomAdapterOpenAuditions(ArrayList<DataModel> data,RecyclerViewClickListener listener) {
        this.dataSet = data;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnClickListener(ClientOpenAuditionsFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view,mListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewlocationname;
        TextView textViewVersion = holder.textViewnoofapplicants;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewVersion.setText(dataSet.get(listPosition).getVersion());

        if (dataSet.get(listPosition).getImage() == 0) imageView.setVisibility(View.GONE);

        else
        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}