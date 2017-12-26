package com.twenty.four.crafts;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rajiv on 18/8/17.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.DirectoryViewHolder>{

    Context context;
    String name[],subject[],body[],time[];
    int no_items,img[];
    ImageButton trash,star;
    RecyclerViewClickListenerInbox mListener;

    GridAdapter(Context c,String name[],String subject[], String body[],String time[],int no_items,int img[],RecyclerViewClickListenerInbox listener)
    {
        this.mListener = listener;
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
        View row = inflater.inflate(R.layout.single_item,parent,false);
        DirectoryViewHolder dvh = new DirectoryViewHolder(row,mListener);

        return dvh;
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {


        String firstLetter = String.valueOf(name[position].charAt(0));
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
// generate random color
        int color = generator.getRandomColor();
//int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig().width(60).height(60).toUpperCase().endConfig()
                .buildRound(firstLetter, color); // radius in px
        holder.I.setImageDrawable(drawable);
        holder.nam.setText(name[position]);
        holder.bod.setText(body[position]);
        holder.sub.setText(subject[position]);
        holder.tim.setText(time[position]);
    }

    @Override
    public int getItemCount() {
        return no_items;
    }



    public class DirectoryViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        TextView nam,sub,bod,tim;
        CircleImageView I;
        ImageButton trash,star;
        RecyclerViewClickListenerInbox mListener;

        public DirectoryViewHolder(View itemView,RecyclerViewClickListenerInbox listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnLongClickListener(this);
            I = itemView.findViewById(R.id.IMG);
            nam = itemView.findViewById(R.id.name);
            sub = itemView.findViewById(R.id.subject);
            bod = itemView.findViewById(R.id.body);
            tim = itemView.findViewById(R.id.time);
            trash=itemView.findViewById(R.id.trash);
            star = itemView.findViewById(R.id.star);
        }

        @Override
        public boolean onLongClick(View view) {
            mListener.onLongClick(view,getAdapterPosition());
            return false;

        }
    }

}



