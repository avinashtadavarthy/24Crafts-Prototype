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
 * Created by avinash on 26/1/18.
 */
public class EditFeatPhotosHorizontalAdapter extends RecyclerView.Adapter<EditFeatPhotosHorizontalAdapter.MyViewHolder> {

    private Context context;
    private String[] urls;
    private int photoCount;

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
    public EditFeatPhotosHorizontalAdapter(Context context, String[] urls, int photoCount) {
        this.context = context;
        this.urls = urls;
        this.photoCount = photoCount;
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

                sharedPref = new SharedPref(context);
                String id = " ";

                try {
                    JSONObject jsonObject = new JSONObject(sharedPref.getSPData(context, "userdatamain"));

                    id = jsonObject.optString("_id");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Log.e("img", reqdno + "");

                switch(position) {
                    case 0:
                        if(reqdno == position) {
                            Glide.with(context).load(User.getInstance().BASE_URL + "users/" + id + "/photos/Image" + position + ".png").diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.image);
                        } else {
                            holder.image.setImageResource(R.drawable.maleleft);
                        }
                        break;
                    case 1:
                        if(reqdno == position) {
                        Glide.with(context).load(User.getInstance().BASE_URL + "users/" + id + "/photos/Image" + position + ".png").diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.image);
                    } else {
                        holder.image.setImageResource(R.drawable.male_front);
                    }
                    break;
                    case 2:
                        if(reqdno == position) {
                            Glide.with(context).load(User.getInstance().BASE_URL + "users/" + id + "/photos/Image" + position + ".png").diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.image);
                        } else {
                            holder.image.setImageResource(R.drawable.maleright);
                        }
                        break;
                    default:
                        if(reqdno == position) {
                            Glide.with(context).load(User.getInstance().BASE_URL + "users/" + id + "/photos/Image" + position + ".png").diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.image);
                        } else {
                            holder.image.setImageResource(R.drawable.male_front);
                        }
                        break;
                    //for each case, if the photo exists in that position, then populate, else, fuck it!
                }

            }


    }

    @Override
    public int getItemCount() {
        return photoCount;
    }
}

