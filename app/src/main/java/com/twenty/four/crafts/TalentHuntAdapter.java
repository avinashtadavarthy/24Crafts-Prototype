package com.twenty.four.crafts;

/**
 * Created by rajiv on 2/9/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rajiv on 18/8/17.
 */

public class TalentHuntAdapter extends RecyclerView.Adapter<TalentHuntAdapter.DirectoryViewHolder>{

    Context context;



    ArrayList<Talent_support_class> dataset;


    TalentHuntAdapter(Context c, ArrayList<Talent_support_class> support_class)
    {
        this.context = c;
        dataset=support_class;

    }


    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.talenthuntadapter,parent,false);
        DirectoryViewHolder dvh = new DirectoryViewHolder(row);

        return dvh;
    }

    @Override
    public void onBindViewHolder(final DirectoryViewHolder holder, final int position) {


        holder.I1.setImageResource(dataset.get(position).getImg_1());
        holder.I2.setImageResource(dataset.get(position).getImg_2());
        holder.I3.setImageResource(dataset.get(position).getImg_3());
        holder.craft_name.setText(dataset.get(position).getCraft_name());
        int c = Color.argb(255,3,155,5);
        int d = Color.argb(255,155,4,28);
        int e = Color.argb(255,0,150,150);
        if(dataset.get(position).getBgc()==0)
        holder.overal.setBackgroundColor(c);
        else  if(dataset.get(position).getBgc()==1)
            holder.overal.setBackgroundColor(d);
        else
            holder.overal.setBackgroundColor(e);








    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder{

        TextView craft_name;
        ImageView I1,I2,I3;
        CardView overal;



        public DirectoryViewHolder(View itemView) {
            super(itemView);


            I1 = (ImageView) itemView.findViewById(R.id.pic1);
            I2 = (ImageView) itemView.findViewById(R.id.pic2);
            I3 = (ImageView) itemView.findViewById(R.id.pic3);
            overal=(CardView) itemView.findViewById(R.id.overall);
            craft_name = (TextView) itemView.findViewById(R.id.craftname);





        }




    }

}




