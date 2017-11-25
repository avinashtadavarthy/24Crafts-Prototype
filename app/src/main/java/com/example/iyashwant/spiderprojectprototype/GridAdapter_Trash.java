package com.example.iyashwant.spiderprojectprototype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rajiv on 18/8/17.
 */

public class GridAdapter_Trash extends RecyclerView.Adapter<GridAdapter_Trash.DirectoryViewHolder>{

    Context context;

    String name[],subject[],body[],time[];
    int no_items,img[];
    ImageButton trash,star;


    GridAdapter_Trash(Context c, String name[], String subject[], String body[], String time[], int no_items, int img[])
    {
        this.context = c;
        this.no_items=no_items;
        this.name=name;
        this.subject=subject;
        this.body=body;
        this.time=time;
        this.img=img;
    }


    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_item_trash,parent,false);
        DirectoryViewHolder dvh = new DirectoryViewHolder(row);

        return dvh;
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {


        holder.I.setImageResource(img[position]);
        holder.nam.setText(name[position]);
        holder.bod.setText(body[position]);
        holder.sub.setText(subject[position]);
        holder.tim.setText(time[position]);
    }


    @Override
    public int getItemCount() {
        return no_items;
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder{

        TextView nam,sub,bod,tim;
        CircleImageView I;
        ImageButton trash,star;


        public DirectoryViewHolder(View itemView) {
            super(itemView);


            I = itemView.findViewById(R.id.IMG);
            nam = itemView.findViewById(R.id.name);
            sub = itemView.findViewById(R.id.subject);
            bod = itemView.findViewById(R.id.body);
            tim = itemView.findViewById(R.id.time);
            trash=itemView.findViewById(R.id.trash);
            star = itemView.findViewById(R.id.starred);

        }
    }

}



