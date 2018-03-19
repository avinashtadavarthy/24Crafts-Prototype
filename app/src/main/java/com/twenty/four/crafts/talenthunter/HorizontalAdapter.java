package com.twenty.four.crafts.talenthunter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twenty.four.crafts.R;

import org.json.JSONArray;
import org.json.JSONException;

import static com.thefinestartist.utils.content.ContextUtil.startActivity;

/**
 * Created by avinash on 26/1/18.
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private Context context;

    private String[] who;
    private String[] craftinfo;
    private RecyclerViewClickListener mListener;

    //declare and initialise
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerViewClickListener mListener;
        public TextView txttemp;
        public RecyclerView inner_recyclerview;

        public MyViewHolder(View view,RecyclerViewClickListener listener) {
            super(view);
            mListener = listener;
            txttemp = (TextView) view.findViewById(R.id.txtTemp);
            inner_recyclerview = (RecyclerView) view.findViewById(R.id.inner_recyclerview);
            txttemp.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onCLick(v,getAdapterPosition());
        }
    }


    //constructor
    public HorizontalAdapter(Context context, String[] who, String[] craftinfo, RecyclerViewClickListener listener) {
        this.context = context;
        this.who = who;
        this.craftinfo = craftinfo;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item_view, parent, false);

        return new MyViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.txttemp.setText(who[position]);


        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.inner_recyclerview.setLayoutManager(verticalLayoutmanager);

        try {
            JSONArray jsonArray = new JSONArray(craftinfo[position]);
            String[] topassdata = new String[jsonArray.length()];

            for(int i = 0; i < jsonArray.length() ; i++) {
                topassdata[i] = jsonArray.getJSONObject(i).toString();
            }

            VerticalAdapter verticalAdapter = new VerticalAdapter(context, topassdata);

            holder.inner_recyclerview.setAdapter(verticalAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return who.length;
    }
}

