package com.twenty.four.crafts;

/**
 * Created by iyashwant on 25/7/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapterSpinner extends BaseAdapter {
    Context context;
    
    String[] list;
    LayoutInflater inflter;

    public CustomAdapterSpinner(Context applicationContext,String[] list) {
        this.context = applicationContext;
      
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public boolean isEnabled(int position) {
        if(position == 0)
            return false;

        else
            return true;

    }



    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spin_who, null);
       // ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.spin_text);
       // icon.setImageResource(flags[i]);
        names.setText(list[i]);
        return view;
    }
}