package com.twenty.four.crafts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReferAndEarn2 extends AppCompatActivity {

    String userdatamain,refCode;

    String ETRefCode = "",jwtToken;

    TextView t;
    EditText e;

    Button verify,share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_and_earn2);


        verify = findViewById(R.id.buttonRAE);
        share = findViewById(R.id.button2RAE);

        t = findViewById(R.id.textView3RAE);
        e = findViewById(R.id.editTextRAE);

        userdatamain = getSPData("userdatamain");

        try {
            refCode = new JSONObject(userdatamain).optString("referralCode").substring(0,8);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("refCode",refCode);

        t.setText(refCode);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIntent();
            }
        });




        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = e.getText().toString();
                verifyRefCode(text);
                e.setText("");
            }
        });
    }

    private void verifyRefCode(String text) {

        ETRefCode = text;

        Log.e("Length",ETRefCode.length() + "");

        String url = User.getInstance().BASE_URL + "user/refer/" + ETRefCode;

        if(ETRefCode.equals("") || ETRefCode.length() < 8)
            Toast.makeText(ReferAndEarn2.this, "Wrong Referral Code", Toast.LENGTH_SHORT).show();

        else
            routetoRefCode(url);


    }

    private void routetoRefCode(String url) {


        jwtToken = getSPData("jwtToken");


        StringRequest getRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("referandearn",response);

                if(response.equals("Referral code already used, cannot reuse!"))
                    Toast.makeText(ReferAndEarn2.this, response, Toast.LENGTH_SHORT).show();

                else if(response.equals("Referral code- not found, try again"))
                    Toast.makeText(ReferAndEarn2.this, response, Toast.LENGTH_SHORT).show();

                else
                {
                    try {
                        JSONObject object = new JSONObject(response);

                        if(object.optString("Msg").equals("Cannot use referral code for yourself!"))
                            Toast.makeText(ReferAndEarn2.this,"Cannot use referral code for yourself!",Toast.LENGTH_LONG).show();


                        else
                            Toast.makeText(ReferAndEarn2.this, "Congratulations! You have earned 40 coins", Toast.LENGTH_SHORT).show();

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }


                    int c = userRequest();
                }




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




    /*private void showSnackbar() {

        final Snackbar snackbar = Snackbar.make(mDrawerLayout,"Unverified Email",Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("REFRESH", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int c = userRequest();

            }
        });

        View snackbarView = snackbar.getView();

        snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbarBackground));

        if(check == 0)
            snackbar.show();

        else
            snackbar.dismiss();

    }
*/




    private int userRequest() {

        //to get the user data
        String newurl = User.getInstance().BASE_URL + "user";

        StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.e("userRequest",response);

                storeSPData("userdatamain", response);

                   /* JSONObject obj = new JSONObject(response);


                    String emailVerified = obj.optString("emailVerification");
                    Log.e("emailverified",emailVerified);
                    storeSPData("emailVerified",emailVerified);

                    int check = check(emailVerified);

                    switch (check)
                    {
                        case 0:showSnackbar();break;
                        case 1:showSnackbar();break;
                    }

                    Log.e("check",check+"");

                    recreate();*/


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



        return 0;
    }

    private int check(String emailVerified) {

        if(emailVerified.equals("false"))
            return 0;

        else
            return 1;

    }

    public void shareIntent()
    {
        String shareText = "Check out 24Crafts App. Open your door to stardom and earn free goodies! http://24crafts.tk/refcode=" + refCode;
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(sharingIntent,"Share using"));
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
