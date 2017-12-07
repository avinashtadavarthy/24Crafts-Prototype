package com.example.iyashwant.spiderprojectprototype.auditions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.R;

import java.util.List;

/**
 * Created by srikanth on 10/11/17.
 */

public class AppliedAuditionsRecyclerAdapter extends RecyclerView.Adapter<AppliedAuditionsRecyclerAdapter.CustomViewHolder> {

    Context mContext;
    List<AuditionHelper> mAuditionHelpersList;

    public AppliedAuditionsRecyclerAdapter(Context context, List<AuditionHelper> auditionHelperList){
        mContext = context;
        mAuditionHelpersList =  auditionHelperList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.audition_rv_item, parent, false);
        return new CustomViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        AuditionHelper auditionHelper = mAuditionHelpersList.get(position);
        holder.auditionTitle.setText(auditionHelper.getTitle());
        //holder.auditionLocation.setText(auditionHelper.getLocation());
        //holder.noOfApplicants.setText("No of Applicants:"+auditionHelper.getApplicants());
        //String photoUrl = auditionHelper.getPhotoUrl();

        //Picasso.with(mContext).load(photoUrl).into(holder.auditionProfile);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView auditionTitle, auditionLocation, noOfApplicants;
        ImageView auditionProfile;

        public CustomViewHolder(View itemView) {
            super(itemView);

            auditionTitle = (TextView) itemView.findViewById(R.id.audition_title);
            auditionLocation = (TextView) itemView.findViewById(R.id.audition_location);
            noOfApplicants = (TextView) itemView.findViewById(R.id.no_of_applicants_tv);
            //auditionProfile = (ImageView) itemView.findViewById(R.id.no_of_applicants_tv);


        }
    }


    @Override
    public int getItemCount() {
        return mAuditionHelpersList.size();
    }
}
