package com.twenty.four.crafts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PromoteProfilePopUp extends AppCompatActivity {

    String jwtToken = "";
    String url = "";
    TextView twelvehrs,twentyfourhrs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote_profile_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.6));


        twelvehrs = findViewById(R.id.button1PromoteProfile);
        twentyfourhrs = findViewById(R.id.button2PromoteProfile);

        jwtToken = getSPData("jwtToken");

        twelvehrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = User.getInstance().BASE_URL + "user/coins/payment/promotedProfile12/50";
                routeToPromoteProfile();

            }
        });


        twentyfourhrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = User.getInstance().BASE_URL + "user/coins/payment/promotedProfile24/90";
                routeToPromoteProfile();

            }
        });

    }




    private void routeToPromoteProfile() {

        jwtToken = getSPData("jwtToken");

        String newurl = url;

        StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("promoteprofile",response);

                Intent i = getIntent();
                i.putExtra("responsePromoteProfile",response);
                setResult(Activity.RESULT_OK,i);

                finish();



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

                params.put("authorization", jwtToken);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(getRequest);





    }



    public void purchaseCoins(View view)
    {
        Intent intent = new Intent(getApplicationContext(),PurchaseCoins.class);
        startActivity(intent);
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
