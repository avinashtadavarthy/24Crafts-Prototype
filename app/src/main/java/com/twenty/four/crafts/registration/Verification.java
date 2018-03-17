package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.squareup.picasso.Picasso;
import com.twenty.four.crafts.MySingleton;
import com.twenty.four.crafts.ProfileView;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;
import com.twenty.four.crafts.app_startup.Login2;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.services.AccountService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class Verification extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    ImageView fb,twitterim,phone,googleim;
    TextView fb_text,google_text,twitter_text,phone_text;
    Button verification_done, verification_skip;

    LinearLayout phone_layout, fb_layout, google_layout, twitter_layout;

    String fromhere = "Hello";
    String fromwhom = "hey";



    //fb login integration
    CallbackManager callbackManager;
    String access;
    public String firstname, lastname, email, gender, imgurl;
    String url = "https://graph.facebook.com/me?fields=id,verified,first_name,last_name,name,gender,email,cover.height(720),picture.height(720),age_range&access_token=";
    LinearLayout fb_layout_verified;
    Boolean fb_open = false;
    CircleImageView fb_dp;
    TextView fb_uname;


    //twitter login integration
    TwitterAuthClient client;
    LinearLayout twitter_layout_verified;
    Boolean twit_open = false;
    CircleImageView twit_dp;
    TextView twit_uname;

    //google login integration
    GoogleSignInOptions gso;
    Button signIn;
    GoogleApiClient googleApiClient;
    GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    private static final int REQ_CODE = 9001;
    LinearLayout google_layout_verified;
    Boolean goog_open = false;
    CircleImageView goog_dp;
    TextView goog_uname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /* */ FacebookSdk.sdkInitialize(getApplicationContext()); /* */
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        setContentView(R.layout.activity_verification);

        getSupportActionBar().setTitle("Verification");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phone_layout = (LinearLayout) findViewById(R.id.phone_layout);
        fb_layout = (LinearLayout) findViewById(R.id.fb_layout);
        google_layout = (LinearLayout) findViewById(R.id.google_layout);
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
        twitterim = (ImageView) findViewById(R.id.twitter);
        phone = (ImageView) findViewById(R.id.phone);
        googleim = (ImageView) findViewById(R.id.google);

        fb_text = (TextView)findViewById(R.id.fb_text_veri);
        twitter_text = (TextView)findViewById(R.id.twitter_text_veri);
        phone_text = (TextView)findViewById(R.id.phone_text_veri);
        google_text = (TextView)findViewById(R.id.google_text_veri);


        if(getSPData("facebook_verified").equals("true")) {
            fb.setImageResource(R.drawable.facebook);
            fb_text.setTextColor(Color.parseColor("#ff99cc00")); //#82ad00
            fb_text.setText("Verified!");
        }

        if(getSPData("google_verified").equals("true")) {
            googleim.setImageResource(R.drawable.googleg_standard_color_18);
            google_text.setTextColor(Color.parseColor("#ff99cc00"));
            google_text.setText("Verified!");
        }

        if(getSPData("twitter_verified").equals("true")) {
            twitterim.setImageResource(R.drawable.twitter);
            twitter_text.setTextColor(Color.parseColor("#ff99cc00"));
            twitter_text.setText("Verified!");
        }





        // for the popups below, if verified
        fb_layout_verified = (LinearLayout) findViewById(R.id.fb_layout_verified);
        fb_layout_verified.setVisibility(View.GONE);
        fb_dp = findViewById(R.id.fb_dp);
        fb_uname = findViewById(R.id.fb_uname);

        google_layout_verified = (LinearLayout) findViewById(R.id.google_layout_verified);
        google_layout_verified.setVisibility(View.GONE);
        goog_dp = findViewById(R.id.goog_dp);
        goog_uname = findViewById(R.id.goog_uname);

        twitter_layout_verified = (LinearLayout) findViewById(R.id.twitter_layout_verified);
        twitter_layout_verified.setVisibility(View.GONE);
        twit_dp = findViewById(R.id.twit_dp);
        twit_uname = findViewById(R.id.twit_uname);
        // for the popups below, if verified

        checkVerificationProgress();






        //facebook signin integration
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        access = loginResult.getAccessToken().getToken();


                        //making the request and getting the data
                        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url + access, null,
                                new Response.Listener<JSONObject>()
                                {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        storeSPData("facebookJSON", response.toString());

                                        Toast.makeText(Verification.this, "Successfully connected your account to facebook!", Toast.LENGTH_SHORT).show();

                                        //User.mInstance.setFacebook_verified(true);
                                        storeSPData("facebook_verified", "true");

                                        fb_layout_verified.setVisibility(View.VISIBLE);
                                        fb.setImageResource(R.drawable.facebook);
                                        fb_text.setTextColor(Color.parseColor("#ff99cc00")); //#82ad00
                                        fb_text.setText("Verified!");

                                        updateStatusOfSocialLogin();
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        Toast.makeText(Verification.this, "Login Error", Toast.LENGTH_SHORT).show();
                                        error.printStackTrace();
                                    }
                                }
                        );

                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(getRequest);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(Verification.this, "Login attempt cancelled.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        exception.printStackTrace();
                        Toast.makeText(Verification.this, "Login attempt failed", Toast.LENGTH_SHORT).show();
                    }

                }
        );
        //facebook login integration - end



        //google signin integration
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
        //google signin integration - end



        phone_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Verification.this, "Phone Verification done!", Toast.LENGTH_SHORT).show();

            }
        });


        fb_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getSPData("facebook_verified").equals("true")) {

                    Toast.makeText(Verification.this, "Facebook account connected!", Toast.LENGTH_SHORT).show();

                } else {
                    LoginManager.getInstance().logInWithReadPermissions(Verification.this, Arrays.asList("user_photos", "email", "user_birthday", "public_profile"));
                }

            }
        });


        google_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getSPData("google_verified").equals("true")) {

                    Toast.makeText(Verification.this, "Google account connected!", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent, REQ_CODE);
                }

            }
        });



        twitter_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getSPData("twitter_verified").equals("true")) {

                    Toast.makeText(Verification.this, "Twitter account connected!", Toast.LENGTH_SHORT).show();

                } else {

                    client = new TwitterAuthClient();
                    client.authorize(Verification.this, new Callback<TwitterSession>() {
                        @Override
                        public void success(Result<TwitterSession> result) {
                            TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                            AccountService accountService = twitterApiClient.getAccountService();
                            Call<com.twitter.sdk.android.core.models.User> call = accountService.verifyCredentials(true, true, true);
                            call.enqueue(new Callback<com.twitter.sdk.android.core.models.User>() {
                                @Override
                                public void success(Result<com.twitter.sdk.android.core.models.User> result) {
                                    String fullname = result.data.name;

                                    storeSPData("twitter_verified", "true");

                                    if(!fullname.contains(" ")) {
                                        firstname = fullname;
                                        lastname = "null";
                                    }
                                    else {
                                        firstname = fullname.substring(0, fullname.lastIndexOf(" "));
                                        lastname = fullname.substring(fullname.lastIndexOf(" ")+1);
                                    }
                                    if(result.data.email.equals("")) email = result.data.email;
                                    else email = "null";
                                    imgurl = result.data.profileImageUrl.replace("_normal", "_400x400");

                                    JSONObject twitter = new JSONObject();
                                    try {
                                        twitter.put("firstname", firstname);
                                        twitter.put("lastname", lastname);
                                        twitter.put("email", email);
                                        twitter.put("imgurl", imgurl);

                                    } catch (JSONException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }

                                    storeSPData("twitterJSON", twitter.toString());

                                    twitter_layout_verified.setVisibility(View.VISIBLE);
                                    twitterim.setImageResource(R.drawable.twitter);
                                    twitter_text.setTextColor(Color.parseColor("#ff99cc00"));
                                    twitter_text.setText("Verified!");

                                    updateStatusOfSocialLogin();

                                    Toast.makeText(Verification.this, "Successfully connected your account to Twitter!", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void failure(TwitterException exception) {

                                    Toast.makeText(Verification.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                                    exception.printStackTrace();
                                }
                            });
                        }

                        @Override
                        public void failure(TwitterException e) {
                            Toast.makeText(Verification.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    });

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

                Intent next = new Intent(getApplicationContext(), ProfileView.class)
                        .putExtra("thisistogetback", "getback")
                        .putExtra("fromwhom", fromwhom);
                startActivity(next);

               /* Intent next = new Intent(getApplicationContext(), ProfileView.class)
                        .putExtra("thisistogetback", "getback")
                        .putExtra("fromwhom", fromwhom);
                startActivity(next);*/

            }
        });

    }

    //google login start

    private void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                //do something
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleResult(GoogleSignInResult result) {

        if(result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            if(account != null) {

                storeSPData("google_verified", "true");

                firstname = account.getDisplayName();
                lastname = account.getFamilyName();
                email = account.getEmail();
                if(account.getPhotoUrl() != null) {
                    imgurl = account.getPhotoUrl().toString();
                } else {
                    imgurl = "null";
                }

                JSONObject google = new JSONObject();
                try {
                    google.put("firstname", firstname);
                    google.put("lastname", lastname);
                    google.put("email", email);
                    google.put("imgurl", imgurl);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                storeSPData("googleJSON", google.toString());

                google_layout_verified.setVisibility(View.VISIBLE);
                googleim.setImageResource(R.drawable.googleg_standard_color_18);
                google_text.setTextColor(Color.parseColor("#ff99cc00"));
                google_text.setText("Verified!");

                updateStatusOfSocialLogin();

                if(firstname.contains(" "))
                    firstname = firstname.substring(0, firstname.indexOf(" "));

                Toast.makeText(Verification.this, "Successfully connected your account to google!", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(Verification.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }
    //google login end



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home: finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //google
        if(requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }

        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //twitter
        if(TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE == requestCode) {
            client.onActivityResult(requestCode, resultCode, data);
        }

    }



    private void checkVerificationProgress() {

        if(!getSPData("userdatamain").equals("")) {

        try {
            JSONObject jsonObject = new JSONObject(getSPData("userdatamain"));

            if(jsonObject.optString("facebook").equals("")) {
                storeSPData("facebook_verified", "false");
                fb_layout_verified.setVisibility(View.GONE);
            }
            else {
                storeSPData("facebook_verified", "true");
                storeSPData("facebookJSON", jsonObject.optString("facebook"));
                fb_layout_verified.setVisibility(View.VISIBLE);

                JSONObject facebook = new JSONObject(jsonObject.optString("facebook"));

                fb_uname.setText(facebook.optString("first_name") + " " + facebook.optString("last_name"));
                    JSONObject picture = facebook.getJSONObject("picture");
                    JSONObject data = picture.getJSONObject("data");
                    imgurl = data.optString("url");
                Picasso.with(getApplicationContext()).load(imgurl).into(fb_dp);
            }

            if(jsonObject.optString("google").equals("")) {
                storeSPData("google_verified", "false");
                google_layout_verified.setVisibility(View.GONE);
            }
            else {
                storeSPData("google_verified", "true");
                storeSPData("googleJSON", jsonObject.optString("google"));
                google_layout_verified.setVisibility(View.VISIBLE);

                JSONObject google = new JSONObject(jsonObject.optString("google"));

                goog_uname.setText(google.optString("firstname"));
                Picasso.with(getApplicationContext()).load(google.optString("imgurl")).into(goog_dp);

            }

            if(jsonObject.optString("twitter").equals("")) {
                storeSPData("twitter_verified", "false");
                twitter_layout_verified.setVisibility(View.GONE);
            }
            else {
                storeSPData("twitter_verified", "true");
                storeSPData("twitterJSON", jsonObject.optString("twitter"));
                twitter_layout_verified.setVisibility(View.VISIBLE);

                JSONObject twitter = new JSONObject(jsonObject.optString("twitter"));

                twit_uname.setText(twitter.optString("firstname") + " " + twitter.optString("lastname"));
                Picasso.with(getApplicationContext()).load(twitter.optString("imgurl")).into(twit_dp);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        }

    }


    void updateStatusOfSocialLogin() {
        String url = User.getInstance().BASE_URL + "user/update";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Verification.this, response, Toast.LENGTH_SHORT).show();


                //to update userdatamain
                String newurl = User.getInstance().BASE_URL + "user";

                StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("userdataonupdate", response);
                        storeSPData("userdatamain", response);

                        checkVerificationProgress();
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

                        params.put("authorization", getSPData("jwtToken"));

                        return params;
                    }
                };

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(getRequest);
                //to update userdatamain



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

                params.put("authorization", getSPData("jwtToken"));

                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("password", getSPData("pword"));
                params.put("google", getSPData("googleJSON"));
                params.put("facebook", getSPData("facebookJSON"));
                params.put("twitter", getSPData("twitterJSON"));

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

