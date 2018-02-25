package com.twenty.four.crafts;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rakave on 25/2/18.
 */

public class SharedPref {
    SharedPreferences mySharedPref;
    public SharedPref(Context context){
      mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }

    public Boolean loadNightModeState(){
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return state;
    }
}
