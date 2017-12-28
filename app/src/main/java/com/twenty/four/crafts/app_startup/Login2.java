package com.twenty.four.crafts.app_startup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
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

public class Login2 extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    FloatingActionButton fabEmail, fabSign;
    VideoView videoview;
    LinearLayout facebook, google, instagram;
    LinearLayout sign_up_button, login_button;

    ImageView signup_iconz, login_iconz;
    TextView otherlogins;

    Bundle bundle;

    //fb login integration
    CallbackManager callbackManager;
    String access;
    public String firstname, lastname, email, gender, imgurl;
    String url = "https://graph.facebook.com/me?fields=id,verified,first_name,last_name,name,gender,email,cover.height(720),picture.height(720),age_range&access_token=";

    //google login integration
    Button signIn;
    GoogleApiClient googleApiClient;
    GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    private static final int REQ_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login2);

        bundle = new Bundle();

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

               bundle.putString("firstname", "null");
                bundle.putString("lastname", "null");
                bundle.putString("email", "null");
                bundle.putString("gender", "null");
                bundle.putString("imgurl", "null");

                Intent i = new Intent(Login2.this, StartingScreen.class);
                i.putExtras(bundle);
                startActivity(i);
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


        //google signin integration
        signIn = (Button) findViewById(R.id.bn_login);
        signIn.setOnClickListener(this);
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bn_login:

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, REQ_CODE);

                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleResult(GoogleSignInResult result) {

        if(result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            firstname = account.getDisplayName();
            lastname = account.getFamilyName();
            email = account.getEmail();
            imgurl = account.getPhotoUrl().toString();

            if(firstname.contains(" "))
                firstname = firstname.substring(0, firstname.indexOf(" "));

            bundle.putString("firstname", firstname);
            bundle.putString("lastname", lastname);
            bundle.putString("email", email);
            bundle.putString("gender", " ");
            bundle.putString("imgurl", imgurl);

            Toast.makeText(Login2.this, "Login Successful", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(Login2.this, StartingScreen.class);
                                        i.putExtras(bundle);
                                        startActivity(i);
        } else {
            Toast.makeText(Login2.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }

    //facebook login

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

        if(requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }

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
