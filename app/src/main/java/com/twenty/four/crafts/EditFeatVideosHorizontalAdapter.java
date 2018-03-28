package com.twenty.four.crafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by avinash on 28/3/18.
 */

public class EditFeatVideosHorizontalAdapter extends RecyclerView.Adapter<EditFeatVideosHorizontalAdapter.MyViewHolder> {

    private Context context;
    private String[] urls;
    private int videoCount;

    SharedPref sharedPref;

    //declare and initialise
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = (RoundedImageView) view.findViewById(R.id._image);
        }

    }


    //constructor
    public EditFeatVideosHorizontalAdapter(Context context, String[] urls, int videoCount) {
        this.context = context;
        this.urls = urls;
        this.videoCount = videoCount;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageviewcellnewprofview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Log.e("iteration number", position + "");

        for(int i = 0; i<urls.length; i++) {

            String imageandext = urls[i].substring(urls[i].lastIndexOf('/') + 1);
            int reqdno = Integer.parseInt(imageandext.substring(5, imageandext.length() - 4));

            sharedPref = new SharedPref(context);{
}
            String id = " ";

            try {
                JSONObject jsonObject = new JSONObject(sharedPref.getSPData(context, "userdatamain"));

                id = jsonObject.optString("_id");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.e("img", reqdno + "");

        }

    }

    @Override
    public int getItemCount() {
        return videoCount;
    }
}


