package com.twenty.four.crafts.app_startup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
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
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.twenty.four.crafts.Main2Activity;
import com.twenty.four.crafts.MySingleton;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;
import com.twenty.four.crafts.registration.StartingScreen;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Login2 extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

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
    GoogleSignInOptions gso;
    Button signIn;
    GoogleApiClient googleApiClient;
    GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    private static final int REQ_CODE = 9001;

    //instagram login integration
    public static final String CLIENT_ID = "1669cbcf37994f0fb1c1b4ee97ed54e6";
    public static final String CLIENT_SECRET = "70d6d7783f9d4d4a82beb4f70b5894f8";
    public static final String CALLBACKURL = "http://geass.technology";

    public static final String APIURL = "https://api.instagram.com/v1";
    private static final String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKENURL ="https://api.instagram.com/oauth/access_token";
    public static final String USETHISSHIT = "https://api.instagram.com/oauth/authorize/?client_id=1669cbcf37994f0fb1c1b4ee97ed54e6&redirect_uri=http://geass.technology&response_type=token";

    String authURLString = AUTHURL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + CALLBACKURL + "&response_type=code&display=touch&scope=likes+comments+relationships";
    String tokenURLString = TOKENURL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";
    String request_token;
    Button login_for_instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* * * */
        /* */ FacebookSdk.sdkInitialize(getApplicationContext());
        /* * * */
        setContentView(R.layout.activity_login2);

        ImageView openinggif = (ImageView) findViewById(R.id.openinggif);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(openinggif);
        Glide.with(this).load(R.raw.samplegif2).into(imageViewTarget);

        bundle = new Bundle();

        facebook = (LinearLayout) findViewById(R.id.fb_login_button);
        google = (LinearLayout) findViewById(R.id.gl_login_button);
        instagram = (LinearLayout) findViewById(R.id.instagram_login_button);

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

                storeSPData("facebook_verified", "false");
                storeSPData("google_verified", "false");
                storeSPData("instagram_verified", "false");
                storeSPData("twitter_verified", "false");

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

        //facebook signin integration
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

                                        //User.mInstance.setFacebook_verified(true);
                                        storeSPData("facebook_verified", "true");

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


        //google signin integration
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();

        signIn = (Button) findViewById(R.id.bn_login);
        signIn.setOnClickListener(this);


        //instagram signin integration
        login_for_instagram = (Button) findViewById(R.id.login_for_instagram);



    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bn_login:

                if (googleApiClient != null && googleApiClient.isConnected()) {

                    signOut();
                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent, REQ_CODE);

                } else {

                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent, REQ_CODE);

                }

                break;
        }

    }

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
            }


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

        if(requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onBackPressed() {

        ActivityCompat.finishAffinity(Login2.this);
        finish();

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

}
