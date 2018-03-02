package com.twenty.four.crafts.registration;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.FacebookSdk;
import com.twenty.four.crafts.MySingleton;
import com.twenty.four.crafts.ProfileView;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Verification extends AppCompatActivity {

    private String jwtToken;

    ImageView fb,insta,twitter,phone,google;
    TextView fb_text,insta_text,google_text,twitter_text,phone_text;
    Button verification_done, verification_skip;

    LinearLayout phone_layout, fb_layout, google_layout, insta_layout, twitter_layout;

    String fromhere = "Hello";
    String fromwhom = "hey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* * * */
        /* */ FacebookSdk.sdkInitialize(getApplicationContext());
        /* * * */
        setContentView(R.layout.activity_verification);

        phone_layout = (LinearLayout) findViewById(R.id.phone_layout);
        fb_layout = (LinearLayout) findViewById(R.id.fb_layout);
        google_layout = (LinearLayout) findViewById(R.id.google_layout);
        insta_layout = (LinearLayout) findViewById(R.id.insta_layout);
        twitter_layout = (LinearLayout) findViewById(R.id.twitter_layout);

        verification_done = (Button) findViewById(R.id.verification_done);
        verification_skip = (Button) findViewById(R.id.verification_skip);

        fromhere = getIntent().getStringExtra("fromhere");
        fromwhom = getIntent().getStringExtra("fromwhom");

        if(fromhere.equals("PhoneVerified")) {
            verification_done.setVisibility(View.GONE);
        } else if(fromhere.equals("FromSettings")) {
            verification_skip.setVisibility(View.GONE);
        }

        fb = (ImageView)findViewById(R.id.fb);
        insta=(ImageView) findViewById(R.id.insta);
        twitter = (ImageView) findViewById(R.id.twitter);
        phone = (ImageView) findViewById(R.id.phone);
        google = (ImageView) findViewById(R.id.google);



        fb_text = (TextView)findViewById(R.id.fb_text_veri);
        insta_text = (TextView)findViewById(R.id.insta_text_veri);
        twitter_text = (TextView)findViewById(R.id.twitter_text_veri);
        phone_text = (TextView)findViewById(R.id.phone_text_veri);
        google_text = (TextView)findViewById(R.id.google_text_veri);


        if(getSPData("facebook_verified").equals("true")) {
            fb.setImageResource(R.drawable.facebook);
            fb_text.setTextColor(Color.parseColor("#ff99cc00")); //#82ad00
            fb_text.setText("Verified!");
        }

        if(getSPData("google_verified").equals("true")) {
            google.setImageResource(R.drawable.googleg_standard_color_18);
            google_text.setTextColor(Color.parseColor("#ff99cc00"));
            google_text.setText("Verified!");
        }

       if(getSPData("instagram_verified").equals("true")) {
            insta.setImageResource(R.drawable.instagram_icon);
            insta_text.setTextColor(Color.parseColor("#ff99cc00"));
            insta_text.setText("Verified!");
        }

        if(getSPData("twitter_verified").equals("true")) {
            twitter.setImageResource(R.drawable.twitter);
            twitter_text.setTextColor(Color.parseColor("#ff99cc00"));
            twitter_text.setText("Verified!");
        }

        phone_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alert =  new AlertDialog.Builder(new ContextThemeWrapper(Verification.this, R.style.AlertDialog))
                        .setTitle("Phone Verification Done!")
                        .setMessage("Do you want to change the number?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(Verification.this, "Have to connect account kit here", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            }
        });


        fb_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getSPData("facebook_verified").equals("true")) {

                    AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(Verification.this, R.style.AlertDialog))
                            .setTitle("Facebook Verification Done!")
                            .setMessage("Do you want to change the account?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(Verification.this, "Log out account and enable change user", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                } else {

                    Toast.makeText(Verification.this, "Perform fb login to connect account", Toast.LENGTH_SHORT).show();

                }

            }
        });


        google_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getSPData("google_verified").equals("true")) {

                  AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(Verification.this, R.style.AlertDialog))
                            .setTitle("Google Verification Done!")
                            .setMessage("Do you want to change the account?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(Verification.this, "Log out account and enable change user", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                } else {

                    Toast.makeText(Verification.this, "Perform google login to connect account", Toast.LENGTH_SHORT).show();

                }

            }
        });



        insta_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getSPData("instagram_verified").equals("true")) {

                   AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(Verification.this, R.style.AlertDialog))
                            .setTitle("Instagram Verification Done!")
                            .setMessage("Do you want to change the account?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(Verification.this, "Log out account and enable change user", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                } else {

                    Toast.makeText(Verification.this, "Perform instagram login to connect account", Toast.LENGTH_SHORT).show();

                }

            }
        });


        twitter_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getSPData("twitter_verified").equals("true")) {

                 AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(Verification.this, R.style.AlertDialog))
                            .setTitle("Twitter Verification Done!")
                            .setMessage("Do you want to change the account?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(Verification.this, "Log out account and enable change user", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                } else {

                    Toast.makeText(Verification.this, "Perform twitter login to connect account", Toast.LENGTH_SHORT).show();

                }

            }
        });



        verification_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        verification_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginWithVolley();

                Intent next = new Intent(getApplicationContext(), ProfileView.class)
                        .putExtra("thisistogetback", "getback")
                        .putExtra("fromwhom", fromwhom);
                startActivity(next);

            }
        });

    }



    private void loginWithVolley() {

        String url = User.getInstance().BASE_URL + "login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    jwtToken = jsonObject.optString("token");








                    //to get the data

                    String newurl = User.getInstance().BASE_URL + "user";

                    StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(Verification.this, response, Toast.LENGTH_LONG).show();
                            storeSPData("allTheUserData", response);

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

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //to get the data









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

                params.put("username", getSPData("useremail"));
                params.put("password", getSPData("password"));

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
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

























/*fb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                fb.setImageResource(R.drawable.facebook);
                fb_text.setTextColor(Color.parseColor("#ff99cc00"));
                fb_text.setText("Verified!");
                return false;

            }
        });

        insta.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                insta.setImageResource(R.drawable.insta);
                insta_text.setTextColor(Color.parseColor("#ff99cc00"));
                insta_text.setText("Verified!");
                return false;
            }
        });


        twitter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                twitter.setImageResource(R.drawable.twitter);
                twitter_text.setTextColor(Color.parseColor("#ff99cc00"));
                twitter_text.setText("Verified!");
                return false;
            }

        });

        google.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                google.setImageResource(R.drawable.google);
                google_text.setTextColor(Color.parseColor("#ff99cc00"));
                google_text.setText("verified!");

                return false;
            }

        });
*/

