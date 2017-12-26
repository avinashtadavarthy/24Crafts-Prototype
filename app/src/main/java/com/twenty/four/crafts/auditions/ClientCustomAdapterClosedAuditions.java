package com.twenty.four.crafts.auditions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twenty.four.crafts.DataModel;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.RecyclerViewClickListener;

import java.util.ArrayList;

/**
 * Created by rakesh on 24/11/17.
 */

public class ClientCustomAdapterClosedAuditions extends RecyclerView.Adapter<ClientCustomAdapterClosedAuditions.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    private RecyclerViewClickListener mListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewlocationname;
        TextView textViewnoofapplicants;
        ImageView imageViewIcon,delButton;
        RecyclerViewClickListener mListener;

        public MyViewHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            delButton = itemView.findViewById(R.id.deleteicon1);
            this.textViewlocationname = (TextView) itemView.findViewById(R.id.LocationText);
            this.textViewnoofapplicants = (TextView) itemView.findViewById(R.id.applicantsText);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.dashboardimage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.delClick(delButton,getAdapterPosition());
        }
    }

    public ClientCustomAdapterClosedAuditions(ArrayList<DataModel> data, RecyclerViewClickListener listener) {
        this.dataSet = data;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout_closed_aud, parent, false);

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