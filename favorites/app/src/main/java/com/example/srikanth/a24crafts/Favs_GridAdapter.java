package com.example.srikanth.a24crafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rakesh on 2/9/17.
 */

public class Favs_GridAdapter extends RecyclerView.Adapter<Favs_GridAdapter.DirectoryViewHolder>{

    Context context;

    ArrayList<Integer> imgid;

    Favs_GridAdapter(Context c, ArrayList<Integer> imgid)
    {
        this.context = c;

        this.imgid = imgid;
    }


    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.favs_single_item,parent,false);
        DirectoryViewHolder dvh = new DirectoryViewHolder(row);

        return dvh;
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {

        Picasso.with(context).load(R.drawable.sample1).fit().into(holder.I);
       // holder.I.setImageDrawable();
    }


    @Override
    public int getItemCount() {
        return imgid.size();
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder{

        TextView T;
        ImageView I;


        public DirectoryViewHolder(View itemView) {
            super(itemView);

            I = (ImageView) itemView.findViewById(R.id.photo);
        }
    }
}



