package com.twenty.four.crafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by rakesh on 3/8/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.DirectoryViewHolder>{

    Context context;
    String data;
    int no;

    ContactsAdapter(Context c, String data)
    {
        this.context = c;
        this.data = data;
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

        try {
            JSONArray jsonArray = new JSONArray(data);

            JSONObject jsonObject = jsonArray.getJSONObject(position);

            if(jsonObject.optString("NAME").equals("")) holder.name1.setVisibility(View.GONE);
            else holder.name1.setText(jsonObject.optString("NAME"));
            if(jsonObject.optString("PHONE").equals("")) holder.phone1.setVisibility(View.GONE);
            else holder.phone1.setText(jsonObject.optString("PHONE"));
            if(jsonObject.optString("EMAIL").equals("")) holder.email1.setVisibility(View.GONE);
            else holder.email1.setText(jsonObject.optString("EMAIL"));
            if(jsonObject.optString("WEBSITE").equals("")) holder.website1.setVisibility(View.GONE);
            else holder.website1.setText(jsonObject.optString("WEBSITE"));
            if(jsonObject.optString("ADDRESS").equals("")) holder.address1.setVisibility(View.GONE);
            else holder.address1.setText(jsonObject.optString("ADDRESS"));



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {

        JSONArray jsonArray = null;
        try {

            jsonArray = new JSONArray(data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray.length();
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


