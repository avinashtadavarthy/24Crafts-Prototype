package com.twenty.four.crafts.app_startup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twenty.four.crafts.MySingleton;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.registration.StartingScreen;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Login2 extends AppCompatActivity {

    FloatingActionButton fabEmail, fabSign;
    VideoView videoview;
    LinearLayout facebook, google, instagram;
    LinearLayout sign_up_button, login_button;

    ImageView signup_iconz, login_iconz;
    TextView otherlogins;

    //fb login integration
    CallbackManager callbackManager;
    String access;
    public String firstname, lastname, email, gender, imgurl;
    String url = "https://graph.facebook.com/me?fields=id,verified,first_name,last_name,name,gender,email,cover.height(720),picture.height(720),age_range&access_token=";

    private final String LOG_TAG = "Login2 Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login2);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(
                callbackManager,
                new FacebookCallback < LoginResult > () {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        access = loginResult.getAccessToken().getToken();


                        //making the request and getting the data
                        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url + access, null,
                                new Response.Listener<JSONObject>()
                                {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        firstname = response.optString("first_name");
                                        lastname = response.optString("last_name");
                                        email = response.optString("email");
                                        gender = response.optString("gender");

                                        try {
                                            JSONObject picture = response.getJSONObject("picture");
                                            JSONObject data = picture.getJSONObject("data");
                                            imgurl = data.optString("url");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        Toast.makeText(Login2.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                        //transferring data
                                        Bundle bundle = new Bundle();
                                        bundle.putString("firstname", firstname);
                                        bundle.putString("lastname", lastname);
                                        bundle.putString("email", email);
                                        bundle.putString("gender", gender);
                                        bundle.putString("imgurl", imgurl);

                                        Intent i = new Intent(Login2.this, StartingScreen.class);
                                        i.putExtras(bundle);
                                        startActivity(i);
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        Toast.makeText(Login2.this, "Login Error", Toast.LENGTH_SHORT).show();
                                        error.printStackTrace();
                                    }
                                }
                        );

                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(getRequest);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(Login2.this, "Login attempt cancelled.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(Login2.this, "Login attempt failed", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        facebook = (LinearLayout) findViewById(R.id.fb_login_button);
        google = (LinearLayout) findViewById(R.id.gl_login_button);
        instagram = (LinearLayout) findViewById(R.id.instagram_login_button);

        //fabEmail=(FloatingActionButton)findViewById(R.id.action_email_fab);
        //fabSign = (FloatingActionButton)findViewById(R.id.action_sign_fab);

        videoview = (VideoView) findViewById(R.id.videoView);

        otherlogins = (TextView) findViewById(R.id.otherlogins);
        sign_up_button = (LinearLayout) findViewById(R.id.sign_up_button);
        login_button = (LinearLayout) findViewById(R.id.login_button);

        signup_iconz = (ImageView) findViewById(R.id.signup_iconz);
        login_iconz = (ImageView) findViewById(R.id.login_iconz);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            otherlogins.setElevation(8);
            signup_iconz.setElevation(6);
            login_iconz.setElevation(6);
            facebook.setElevation(5);
            google.setElevation(5);
            instagram.setElevation(5);
        }

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getApplicationContext(),StartingScreen.class);
                startActivity(nextpage);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getApplicationContext(),Login.class);
                startActivity(nextpage);
            }
        });

        displayCoverVideo();

    }

    public void fbLogin(View view) {

        if(AccessToken.getCurrentAccessToken()!=null) {
            LoginManager.getInstance().logOut();
            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("user_photos", "email", "user_birthday", "public_profile"));
        } else {

            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("user_photos", "email", "user_birthday", "public_profile"));

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    private void displayCoverVideo(){
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }


    private void initialiseFABEvents(){
        fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getApplicationContext(),Login.class);
                startActivity(nextpage);
            }
        });

        fabSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getApplicationContext(),StartingScreen.class);
                startActivity(nextpage);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoview = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoview.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoview.stopPlayback();
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

}
