package com.twenty.four.crafts.garlandview.profile;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.twenty.four.crafts.R;
import com.twenty.four.crafts.garlandview.details.DetailsData;


public class ProfileItem extends RecyclerView.ViewHolder {

    public ProfileItem(View itemView) {
        super(itemView);
    }

    void setContent(DetailsData data) {
        ((TextView)itemView.findViewById(R.id.tv_title)).setText(data.title);
        ((TextView)itemView.findViewById(R.id.tv_text)).setText(data.text);
    }

}
