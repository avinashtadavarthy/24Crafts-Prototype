package com.twenty.four.crafts.auditions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by rakesh on 24/11/17.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.twenty.four.crafts.DataModel;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.RecyclerViewClickListener;

public class ClientCustomAdapterOpenAuditions extends RecyclerView.Adapter<ClientCustomAdapterOpenAuditions.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    private RecyclerViewClickListener mListener;
    Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewlocationname;
        TextView textViewnoofapplicants;
        ImageView imageViewIcon,delButton,editbutton;
        Switch auditionsSwitch;
        RecyclerViewClickListener mListener;

        public MyViewHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            this.delButton = itemView.findViewById(R.id.deleteicon);
            this.editbutton = itemView.findViewById(R.id.editicon);
            this.textViewlocationname = (TextView) itemView.findViewById(R.id.LocationText);
            this.textViewnoofapplicants = (TextView) itemView.findViewById(R.id.applicantsText);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.dashboardimage);
            this.auditionsSwitch = itemView.findViewById(R.id.open_auditions_switch);
            delButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onCLick(view,getAdapterPosition());
            mListener.delClick(delButton,getAdapterPosition());
            mListener.editClick(editbutton,getAdapterPosition());
        }
    }

    public ClientCustomAdapterOpenAuditions(ArrayList<DataModel> data, RecyclerViewClickListener listener, Context context) {
        this.dataSet = data;
        mListener = listener;
        mContext = context;
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

        holder.auditionsSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    Toast.makeText(mContext, "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Unchecked", Toast.LENGTH_SHORT).show();

                }


            }


        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}