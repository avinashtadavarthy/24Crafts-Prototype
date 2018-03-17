package com.twenty.four.crafts.talenthunter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.twenty.four.crafts.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by avinash on 26/1/18.
 */

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {

   private Context context;
   private String[] craftinfo;
//    public ArrayList<String> craftinfo;

    final String [] who = {
            "Actor",
            "Actress",
            "Art Department",
            "Assistant Director",
            "Child Artist",
            "Choreographer",
            "Costumer",
            "Dancer",
            "Designer",
            "DI",
            "Dialogue Writer",
            "Director of Photography",
            "Dubbing Artist",
            "Editor",
            "Focus Puller",
            "Hair Dresser",
            "Location Manager",
            "Lyric Writer",
            "Makeup Man",
            "Mic Department",
            "Music Director",
            "Pet Supplier",
            "PRO",
            "Production Food",
            "Production Manager",
            "Script Writer",
            "Set Department",
            "SFX",
            "Side Artist",
            "Singer",
            "Sound Mixing Engineer",
            "Sound Recording Engineer",
            "Still Photographer",
            "Story Board Artist",
            "Stuntman",
            "Vehicle Driver",
            "VFX"
    };




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name;
        public TextView item_age;
        public CircleImageView item_image;
        public TextView item_bio;


        public MyViewHolder(View view) {
            super(view);

            item_name = (TextView) view.findViewById(R.id.item_name);
            item_age = (TextView) view.findViewById(R.id.item_age);
            item_bio = (TextView) view.findViewById(R.id.item_bio);
            item_image = (CircleImageView) view.findViewById(R.id.item_image);

        }
    }


    public VerticalAdapter(Context context, String[] craftinfo) {
        this.context = context;
        this.craftinfo = craftinfo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_item_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        try {
            JSONObject jsonObject = new JSONObject(craftinfo[position]);

            //to calculate age
            Calendar dob = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            int year = Integer.parseInt(jsonObject.optString("dob").substring(0,4));
            int month = Integer.parseInt(jsonObject.optString("dob").substring(5,7));
            int day = Integer.parseInt(jsonObject.optString("dob").substring(8,10));
            dob.set(year,month,day);
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) age--;
            String ageS = "" + age;

            holder.item_age.setText(ageS);
            holder.item_name.setText(jsonObject.optString("name"));
            holder.item_bio.setText(jsonObject.optString("bio"));

            if(!jsonObject.optString("profileImageURL").equals(""))
            Glide.with(context).load(jsonObject.optString("profileImageURL")).into(holder.item_image);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return craftinfo.length;
    }
}