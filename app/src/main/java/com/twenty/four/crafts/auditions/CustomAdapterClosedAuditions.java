package com.twenty.four.crafts.auditions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twenty.four.crafts.DataModel;
import com.twenty.four.crafts.R;

import java.util.ArrayList;

public class CustomAdapterClosedAuditions extends RecyclerView.Adapter<CustomAdapterClosedAuditions.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewlocationname;
        TextView textViewnoofapplicants;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewlocationname = (TextView) itemView.findViewById(R.id.LocationText);
            this.textViewnoofapplicants = (TextView) itemView.findViewById(R.id.applicantsText);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.dashboardimage);
        }
    }

    public CustomAdapterClosedAuditions(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.auditions_cards_layout, parent, false);

        view.setOnClickListener(ClosedAuditionsFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
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