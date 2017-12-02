package com.example.iyashwant.spiderprojectprototype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rakesh on 3/8/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.DirectoryViewHolder>{

    Context context;
    List<ContactsHelper> object;
    int no;

    ContactsAdapter(Context c, List<ContactsHelper> obj)
    {
        this.context = c;
       object=obj;
    }


    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.contactsadapter,parent,false);
        DirectoryViewHolder dvh = new DirectoryViewHolder(row);

        return dvh;
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {

        if(!(object.get(position).getNAME().equals("")))
        holder.name1.setText(object.get(position).getNAME());

        else
            holder.name1.setVisibility(View.GONE);
        if(!(object.get(position).getPHONE().equals("")))
        holder.phone1.setText(object.get(position).getPHONE());
        else
            holder.phone1.setVisibility(View.GONE);
        if(!(object.get(position).getEMAIL().equals("")))
        holder.email1.setText(object.get(position).getEMAIL());
        else
            holder.email1.setVisibility(View.GONE);
        if(!(object.get(position).getWEBSITE().equals("")))
        holder.website1.setText(object.get(position).getWEBSITE());
        else
            holder.website1.setVisibility(View.GONE);
        if(!(object.get(position).getADDRESS().equals("")))
        holder.address1.setText(object.get(position).getADDRESS());
        else
            holder.address1.setVisibility(View.GONE);

    }


    @Override
    public int getItemCount() {
        return object.size();
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


