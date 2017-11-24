package com.example.srikanth.a24crafts;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by rakesh on 2/9/17.
 */

class Utility {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }
}
