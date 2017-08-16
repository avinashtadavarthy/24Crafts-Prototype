package com.example.iyashwant.spiderprojectprototype.Directory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.R;

/**
 * Created by rakesh on 3/8/17.
 */

public class Recycler_adapter extends RecyclerView.Adapter<Recycler_adapter.DirectoryViewHolder>{

    Context context;
    String[] name;
    String[] phone;
    String[] email;
    String[] address;
    String[] website;
    int no;

    Recycler_adapter(Context c,String[] name, String[] phone, String[] email,String[] address,String[] website,int no_of_people)
    {
        this.context = c;
        this.name = name;
        this.phone = phone;
        this.email=email;
        this.address=address;
        this.website=website;
        this.no=no_of_people;
    }


    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_contact_file,parent,false);
        DirectoryViewHolder dvh = new DirectoryViewHolder(row);

        return dvh;
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {

        if(!(name[position].equals("")))
        holder.name1.setText("Name:  " +name[position]);
        else
            holder.name1.setVisibility(View.INVISIBLE);
        if(!(phone[position].equals("")))
        holder.phone1.setText("Phone:  "+phone[position]);
        else
            holder.phone1.setVisibility(View.INVISIBLE);
        if(!(email[position].equals("")))
        holder.email1.setText("E-mail:  "+email[position]);
        else
            holder.email1.setVisibility(View.INVISIBLE);
        if(!(website[position].equals("")))
        holder.website1.setText("Website:  "+website[position]);
        else
            holder.website1.setVisibility(View.INVISIBLE);
        if(!(address[position].equals("")))
        holder.address1.setText("Address:  "+address[position]);
        else
            holder.address1.setVisibility(View.INVISIBLE);

    }


    @Override
    public int getItemCount() {
        return no;
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder{

        TextView name1,address1,email1,website1,phone1;



        public DirectoryViewHolder(View itemView) {
            super(itemView);

            name1 = itemView.findViewById(R.id.name);
            email1 = itemView.findViewById(R.id.email);
            website1 = itemView.findViewById(R.id.website);
            phone1 =itemView.findViewById(R.id.phone);
            address1 = itemView.findViewById(R.id.address);
        }
    }
}


