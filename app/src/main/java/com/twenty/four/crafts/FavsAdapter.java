package com.twenty.four.crafts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by Avinash Tadavarthy on 25-Nov-17.
 */

public class FavsAdapter extends BaseAdapter {

    private Context context;

    private String[] mThumbIds;

    private LayoutInflater inflater;

    public FavsAdapter(Context context, String[] mThumbIds) {
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

        if(convertView == null) {

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.favs_item, null);
        }

        RoundedImageView fav_item = (RoundedImageView) gridView.findViewById(R.id.fav_item);
        Glide.with(context).load(mThumbIds[position]).into(fav_item);
//        fav_item.setImageResource(mThumbIds[position]);

        return gridView;
    }
}

