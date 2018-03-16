package com.twenty.four.crafts;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by rakave on 25/2/18.
 */

public class SharedPref {

    SharedPreferences mySharedPref;

    public SharedPref(Context context){
      mySharedPref = context.getSharedPreferences("filename", MODE_PRIVATE);
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



    public void clearAllSharedPrefs(Context c) {

        //registration purpose
        storeSPData(c, "isClient", "");
        storeSPData(c, "firstname", "");
        storeSPData(c, "lastname", "");
        storeSPData(c, "email", "");
        storeSPData(c, "password", "");
        storeSPData(c, "dob", "");
        storeSPData(c, "gender", "");
        storeSPData(c, "residingin", "");
        storeSPData(c, "hometown", "");
        storeSPData(c, "languagesspoken", "");
        storeSPData(c, "category", "");
        storeSPData(c, "bodyType", "");
        storeSPData(c, "hairColor", "");
        storeSPData(c, "hairLength", "");
        storeSPData(c, "eyeColor", "");
        storeSPData(c, "skinTone", "");
        storeSPData(c, "facialHair", "");
        storeSPData(c, "height", "");
        storeSPData(c, "weight", "");
        storeSPData(c, "hipsize", "");
        storeSPData(c, "chestSize", "");
        storeSPData(c, "waistSize", "");
        storeSPData(c, "phonenumber", "");
        storeSPData(c, "name", "");
        storeSPData(c, "facebookJSON", "");
        storeSPData(c, "googleJSON", "");
        storeSPData(c, "twitterJSON", "");
        storeSPData(c, "hasPrevExp", "");
        storeSPData(c, "prevExp", "");

        //app purpose
        storeSPData(c, "phone_verified", "");
        storeSPData(c, "facebook_verified", "");
        storeSPData(c, "google_verified", "");
        storeSPData(c, "twitter_verified", "");
        storeSPData(c, "userdatamain", "");
        storeSPData(c, "jwtToken", "");
        storeSPData(c, "subscribed", "");
        storeSPData(c, "pword", "");
    }


    public void clearSelectedSharedPrefs(Context c) {

        //registration purpose
        storeSPData(c, "isClient", "");
        storeSPData(c, "firstname", "");
        storeSPData(c, "lastname", "");
        storeSPData(c, "email", "");
        storeSPData(c, "password", "");
        storeSPData(c, "dob", "");
        storeSPData(c, "gender", "");
        storeSPData(c, "residingin", "");
        storeSPData(c, "hometown", "");
        storeSPData(c, "languagesspoken", "");
        storeSPData(c, "category", "");
        storeSPData(c, "bodyType", "");
        storeSPData(c, "hairColor", "");
        storeSPData(c, "hairLength", "");
        storeSPData(c, "eyeColor", "");
        storeSPData(c, "skinTone", "");
        storeSPData(c, "facialHair", "");
        storeSPData(c, "height", "");
        storeSPData(c, "weight", "");
        storeSPData(c, "hipsize", "");
        storeSPData(c, "chestSize", "");
        storeSPData(c, "waistSize", "");
        storeSPData(c, "phonenumber", "");
        storeSPData(c, "name", "");
    }



    private void storeSPData(Context c, String key, String data) {

        SharedPreferences mUserData = c.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(Context c, String key) {

        SharedPreferences mUserData = c.getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }

}
