package com.twenty.four.crafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by avinash on 17/3/18.
 */

public class FavsRecyclerAdapter extends RecyclerView.Adapter<FavsRecyclerAdapter.FavsViewHolder> {

    private Context context;
    private String[] mThumbIds;
    private LayoutInflater inflater;

    // data is passed into the constructor
    FavsRecyclerAdapter(Context context, String[] mThumbIds) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mThumbIds = mThumbIds;
    }

    // inflates the cell layout from xml when needed
    @Override
    public FavsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.favs_item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new FavsViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(FavsViewHolder holder, int position) {

        Glide.with(context).load(mThumbIds[position]).into(holder.fav_item);

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mThumbIds.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class FavsViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView fav_item;

        public FavsViewHolder(View itemView) {
            super(itemView);

            fav_item = (RoundedImageView) itemView.findViewById(R.id.fav_item);
        }

    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mThumbIds[id];
    }
}