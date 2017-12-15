package com.example.iyashwant.spiderprojectprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Avinash Tadavarthy on 25-Nov-17.
 */

public class FavsAdapter extends BaseAdapter {

    private Context context;

    private int[] mThumbIds;

    private LayoutInflater inflater;

    public FavsAdapter(Context context, int mThumbIds[]) {
        this.context = context;
        this.mThumbIds = mThumbIds;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return mThumbIds[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if(convertView == null){

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.favs_item, null);
        }

        RoundedImageView fav_item = (RoundedImageView) gridView.findViewById(R.id.fav_item);
        fav_item.setImageResource(mThumbIds[position]);

        return gridView;
    }
}

