package com.twenty.four.crafts.app_startup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.twenty.four.crafts.ForgotPassword;
import com.twenty.four.crafts.Item;
import com.twenty.four.crafts.JWTUtils;
import com.twenty.four.crafts.Main2Activity;
import com.twenty.four.crafts.Main3Activity;
import com.twenty.four.crafts.MySingleton;
import com.twenty.four.crafts.PushNotifMain;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button btnCraftsmen, btnClients;
    TextView forgotPassword;
    RelativeLayout parent;
    EditText email;
    TextInputEditText password;
    TextView ifwrongpass;
    ProgressDialog progressbar;
    AnimationDrawable animationDrawable;

    String uname, pword, jwtToken, subscribed, decodedJWT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCraftsmen = (Button) findViewById(R.id.btnCraftsmen);
        forgotPassword = findViewById(R.id.forgot_password);

        parent = findViewById(R.id.parentLayout);
        ifwrongpass = findViewById(R.id.ifwrongpass);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressbar = new ProgressDialog(Login.this);
        progressbar.setMessage("Logging in...");

        email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email.setText(getSPData("uname"));

        animationDrawable = (AnimationDrawable) parent.getBackground();

        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);



        animationDrawable.start();



        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    password.setHint("");
                else
                    password.setHint("Password");
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });

        btnCraftsmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    uname = email.getText().toString().trim();
                    pword = password.getText().toString().trim();



                    loginUser();

                } else {
                    ifwrongpass.setText("Empty fields found");
                }
            }
        });





        Button notif  = findViewById(R.id.pushnotif);
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PushNotifMain.class);
                startActivity(i);
            }
        });
    }




    private void loginUser() {

        progressbar.show();
        progressbar.setCancelable(false);


        String url = User.getInstance().BASE_URL + "login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.e("responseForLogin", response);

                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.optString("message").equals("Incorrect password.")) {

                        progressbar.dismiss();
                        ifwrongpass.setText("Incorrect Password!");

                    } else if(response.equals("{}")) {

                        progressbar.dismiss();
                        ifwrongpass.setText("Email Not Registered. Kindly Register!");

                    } else {

                        jwtToken = jsonObject.optString("token");
                        subscribed = jsonObject.optString("subscribed");

                        storeSPData("jwtToken", jwtToken);
                        storeSPData("subscribed", subscribed);

                        ArrayList<Item> items2 = new ArrayList<>();
                        items2 = Item.getTestingList(getApplicationContext(),"CraftsmenOpenAuditions");

                        Log.e("jwtToken",jwtToken);
                        String payLoadJWT = jwtToken.substring(jwtToken.indexOf(".")+1);
                        payLoadJWT = payLoadJWT.substring(0,payLoadJWT.indexOf("."));
                        Log.e("payLoadJWT",payLoadJWT);

                        try {
                            decodedJWT = JWTUtils.getJson(payLoadJWT);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String newurl = User.getInstance().BASE_URL + "user";

                        //to get the user data
                    /*    if(isClient.equals("false"))
                         newurl = User.getInstance().BASE_URL + "user";

                        else
                            newurl = User.getInstance().BASE_URL + "client";*/


                       // Log.e("newURL",newurl);


                        StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressbar.dismiss();

                                try {

                                    storeSPData("uname", uname);
                                    storeSPData("pword", pword);
                                    storeSPData("userdatamain", response);

                                    assignSPData();

                                   JSONObject obj = new JSONObject(response);
                                    String isClient = obj.optString("isClient");

                                    if(isClient.equals("false")) {
                                        Intent i = new Intent(Login.this,Main2Activity.class)
                                                .putExtra("userdata", response).putExtra("subscribed", subscribed);
                                        Log.e("userdata",response);
                                        startActivity(i);
                                    } else {

                                        Intent i = new Intent(Login.this,Main3Activity.class)
                                                .putExtra("userdata", response).putExtra("subscribed", subscribed);
                                        Log.e("userdata",response);
                                        startActivity(i);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                        //to get the user data
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("username", uname);
                params.put("password", pword);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }




    @Override
    public void onBackPressed() {

        Intent i = new Intent(getApplicationContext(), Login2.class);
        startActivity(i);

    }

    //keyboard disappears when you click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
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


    void assignSPData() {

        try {
            JSONObject jsonObject = new JSONObject(getSPData("userdatamain"));

            storeSPData("phone_verified", "true");

            if(jsonObject.optString("facebook").equals("")) storeSPData("facebook_verified", "false");
            else storeSPData("facebook_verified", "true");

            if(jsonObject.optString("google").equals("")) storeSPData("google_verified", "false");
            else storeSPData("google_verified", "true");

            if(jsonObject.optString("twitter").equals("")) storeSPData("twitter_verified", "false");
            else storeSPData("twitter_verified", "true");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
