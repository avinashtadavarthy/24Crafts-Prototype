package com.twenty.four.crafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rakesh on 2/8/17.
 */

public class DirectoryGridAdapter extends RecyclerView.Adapter<DirectoryGridAdapter.DirectoryViewHolder>{

    Context context;
    ArrayList<IconsClass> icons;

    DirectoryGridAdapter(Context c, ArrayList<IconsClass> classobj)
    {
        this.context = c;
        icons=classobj;
    }


    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.directorygridadapter,parent,false);
        DirectoryViewHolder dvh = new DirectoryViewHolder(row);

        return dvh;
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {

        holder.T.setText(icons.get(position).getCraft_name());
        holder.I.setImageResource(icons.get(position).getImg_id());
    }


    @Override
    public int getItemCount() {
        return icons.size();
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder{

        TextView T;
        ImageView I;


        public DirectoryViewHolder(View itemView) {
            super(itemView);

            T = itemView.findViewById(R.id.txtview);
            I = itemView.findViewById(R.id.imgview);
        }
    }
}

