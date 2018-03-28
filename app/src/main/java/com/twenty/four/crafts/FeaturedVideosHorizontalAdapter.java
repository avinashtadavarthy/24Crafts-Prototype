package com.twenty.four.crafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by avinash on 28/3/18.
 */

public class FeaturedVideosHorizontalAdapter extends RecyclerView.Adapter<FeaturedVideosHorizontalAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> urls;

    //declare and initialise
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = (RoundedImageView) view.findViewById(R.id._image);
        }

    }


    //constructor
    public FeaturedVideosHorizontalAdapter(Context context, ArrayList<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public FeaturedVideosHorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageviewcellnewprofview, parent, false);

        return new FeaturedVideosHorizontalAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FeaturedVideosHorizontalAdapter.MyViewHolder holder, final int position) {

        Glide.with(context).load("https://img.youtube.com/vi/" + urls.get(position) + "/mqdefault.jpg").diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
