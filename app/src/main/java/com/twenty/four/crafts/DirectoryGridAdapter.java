package com.twenty.four.crafts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rakesh on 2/8/17.
 */

public class DirectoryGridAdapter extends BaseAdapter{

    ArrayList<IconsClass> classobj;
    Context context;

    private static LayoutInflater inflater=null;

    DirectoryGridAdapter(Context context,ArrayList<IconsClass> classobj)
    {
        this.context = context;
        this.classobj = classobj;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return classobj.size();
    }

    @Override
    public Object getItem(int i) {
        return classobj.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.directorygridadapter, null);
        holder.tv=(TextView) rowView.findViewById(R.id.txtview);
        holder.img=(ImageView) rowView.findViewById(R.id.imgview);

        holder.tv.setText(classobj.get(i).craft_name);
        holder.img.setImageResource(classobj.get(i).img_id);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               // Toast.makeText(context, "You Clicked "+i, Toast.LENGTH_LONG).show();

            }
        });

        return rowView;

    }


}

