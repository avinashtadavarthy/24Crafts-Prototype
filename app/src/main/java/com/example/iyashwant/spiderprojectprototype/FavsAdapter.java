package com.example.iyashwant.spiderprojectprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Avinash Tadavarthy on 25-Nov-17.
 */

public class FavsAdapter extends RecyclerView.Adapter<FavsAdapter.FavsViewHolder> {

    Context context;
    ArrayList<Integer> favList;

    public FavsAdapter(Context c, ArrayList<Integer> favList) {
        this.context = c;
        this.favList = favList;
    }


    @Override
    public FavsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.favs_item,parent,false);
        FavsViewHolder dvh = new FavsViewHolder(row);

        return dvh;
    }

    @Override
    public void onBindViewHolder(FavsViewHolder holder, final int position) {

        Picasso.with(context).load(R.drawable.sample1).fit().into(holder.fav_item);

    }

    @Override
    public int getItemCount() {
        return favList.size();
    }


    public class FavsViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView fav_item;

        public FavsViewHolder(View itemView) {
            super(itemView);
            fav_item = (RoundedImageView) itemView.findViewById(R.id.fav_item);
        }

    }


}

