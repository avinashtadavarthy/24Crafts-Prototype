package com.twenty.four.crafts;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

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



    public void updateUserDataMain(final Context c) {
        //to update userdatamain
        String newurl = User.getInstance().BASE_URL + "user";

        StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("userdataonupdate", response);
                storeSPData(c, "userdatamain", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("authorization", getSPData(c, "jwtToken"));

                return params;
            }
        };

        MySingleton.getInstance(c).addToRequestQueue(getRequest);
        //to update userdatamain
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
        storeSPData(c, "encountersView", "");
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



    public void storeSPData(Context c, String key, String data) {

        SharedPreferences mUserData = c.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    public String getSPData(Context c, String key) {

        SharedPreferences mUserData = c.getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }

}
