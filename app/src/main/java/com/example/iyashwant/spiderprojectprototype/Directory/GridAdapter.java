package com.example.iyashwant.spiderprojectprototype.Directory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.R;

/**
 * Created by rakesh on 2/8/17.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.DirectoryViewHolder>{

    Context context;
    String[] desc;
    int[] imgid;

    GridAdapter(Context c,String[] desc, int[] imgid)
    {
        this.context = c;
        this.desc = desc;
        this.imgid = imgid;
    }


    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.singleitem,parent,false);
        DirectoryViewHolder dvh = new DirectoryViewHolder(row);

        return dvh;
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {

        holder.T.setText(desc[position]);
        holder.I.setImageResource(imgid[position]);
    }


    @Override
    public int getItemCount() {
        return imgid.length;
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

