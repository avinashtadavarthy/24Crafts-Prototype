package com.twenty.four.crafts;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by rakesh on 18/12/17.
 */

public interface RecyclerViewClickListener {

    void onCLick(View view, int position);
    void delClick(ImageView delButton, int position);
    void editClick(ImageView editbutton, int position);
}
