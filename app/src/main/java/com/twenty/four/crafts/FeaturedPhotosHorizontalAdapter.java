package com.twenty.four.crafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by avinash on 26/1/18.
 */
public class FeaturedPhotosHorizontalAdapter extends RecyclerView.Adapter<FeaturedPhotosHorizontalAdapter.MyViewHolder> {

    private Context context;
    private String[] urls;

    //declare and initialise
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = (RoundedImageView) view.findViewById(R.id._image);
        }

    }


    //constructor
    public FeaturedPhotosHorizontalAdapter(Context context, String[] urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageviewcellnewprofview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Glide.with(context).load("http://" + urls[position]).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return urls.length;
    }
}

