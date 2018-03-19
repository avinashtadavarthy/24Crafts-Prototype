package com.twenty.four.crafts;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RadarView extends AppCompatActivity {

    String profImgURL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_view);
        AndroidNetworking.initialize(getApplicationContext());
        routeTogetUserDetails();
    }

    private void routeTogetUserDetails() {

        String jwtToken = getSPData("jwtToken");
        String url = User.getInstance().BASE_URL + "user/getDetails";

        AndroidNetworking.post(url)
                .addHeaders("authorization",jwtToken)
                .addBodyParameter("idArray","5aaf6017ddf1a65958513991")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            profImgURL = jsonObject.optString("profileImageURL");
                            initView();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //response.getJSONObject(0);

                        initView();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }


    private void initView(){
        RadarViewClass mRadarView = (RadarViewClass) findViewById(R.id.radar_view);
        mRadarView.setSearching(true);

        for(int i=0;i<15;i++)
        mRadarView.addPoint();

    }




    //Shared Preferences
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(String key) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }
}
