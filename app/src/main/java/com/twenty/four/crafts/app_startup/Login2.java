package com.twenty.four.crafts.app_startup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
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
import com.twenty.four.crafts.Main2Activity;
import com.twenty.four.crafts.Main3Activity;
import com.twenty.four.crafts.MySingleton;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.SharedPref;
import com.twenty.four.crafts.User;
import com.twenty.four.crafts.registration.StartingScreen;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.services.AccountService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class Login2 extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    LinearLayout facebook, google;
    LinearLayout sign_up_button, login_button;

    ImageView signup_iconz, login_iconz;
    TextView otherlogins;

    Bundle bundle;

    SharedPref sharedPref;

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

    //twitter login integration
    TwitterAuthClient client;
    Button twitter;

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


    ImageView splashlogo;

    ProgressDialog progressbar;
    String jwtToken, subscribed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /* */  FacebookSdk.sdkInitialize(getApplicationContext());     /* */
        /* */            Twitter.initialize(this);              /* */
        /* */  AndroidNetworking.initialize(getApplicationContext());   /* */
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        setContentView(R.layout.activity_login2);

        sharedPref = new SharedPref(getApplicationContext());


        if(!getSPData("uname").equals("") && !getSPData("pword").equals("")) {

            progressbar = new ProgressDialog(Login2.this);
            progressbar.setCancelable(false);

            loginUser();

        }

        ImageView openinggif = (ImageView) findViewById(R.id.openinggif);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(openinggif);
        Glide.with(this).load(R.raw.samplevideo).into(imageViewTarget);

        splashlogo = (ImageView) findViewById(R.id.splashlogo);
        if (android.os.Build.VERSION.SDK_INT >= 21) splashlogo.setElevation(10);

        bundle = new Bundle();

        facebook = (LinearLayout) findViewById(R.id.fb_login_button);
        google = (LinearLayout) findViewById(R.id.gl_login_button);

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
        }

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storeSPData("phone_verified", "false");
                storeSPData("facebook_verified", "false");
                storeSPData("google_verified", "false");
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
                sharedPref.clearAllSharedPrefs(getApplicationContext());
                Intent nextpage = new Intent(getApplicationContext(),Login.class);
                startActivity(nextpage);
            }
        });

        //facebook signin integration
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback < LoginResult > () {
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
                                        storeSPData("facebookEmail", response.optString("email"));

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

                                        checkIfUserExists(email, "facebook");
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
                        exception.printStackTrace();
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


        //twitter signin integration
        client = new TwitterAuthClient();
        twitter = findViewById(R.id.twitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.authorize(Login2.this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                        AccountService accountService = twitterApiClient.getAccountService();
                        Call<com.twitter.sdk.android.core.models.User> call = accountService.verifyCredentials(true, true, true);
                        call.enqueue(new Callback<com.twitter.sdk.android.core.models.User>() {
                            @Override
                            public void success(Result<com.twitter.sdk.android.core.models.User> result) {
                                String fullname = result.data.name;
                                long userId = result.data.getId();

                                storeSPData("twitter_verified", "true");

                                if(!fullname.contains(" ")) {
                                    firstname = fullname;
                                    lastname = "null";
                                }
                                else {
                                    firstname = fullname.substring(0, fullname.lastIndexOf(" "));
                                    lastname = fullname.substring(fullname.lastIndexOf(" ")+1);
                                }
                                if(!result.data.email.equals("")) email = result.data.email;
                                else email = "null";
                                imgurl = result.data.profileImageUrl.replace("_normal", "_400x400");

                                JSONObject twitter = new JSONObject();
                                try {
                                    twitter.put("userId", userId);
                                    twitter.put("firstname", firstname);
                                    twitter.put("lastname", lastname);
                                    twitter.put("email", email);
                                    twitter.put("imgurl", imgurl);

                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                storeSPData("twitterJSON", twitter.toString());
                                storeSPData("twitterEmail", Long.toString(userId));



                                bundle.putString("firstname", firstname);
                                bundle.putString("lastname", lastname);
                                bundle.putString("email", email);
                                bundle.putString("gender", " ");
                                bundle.putString("imgurl", imgurl);

                                Toast.makeText(Login2.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                checkIfUserExists(Long.toString(userId), "twitter");

                            }

                            @Override
                            public void failure(TwitterException exception) {

                                Toast.makeText(Login2.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                                exception.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Toast.makeText(Login2.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
            }
        });



    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bn_login:

                google.setBackgroundResource(R.drawable.round_card_google);

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
                storeSPData("googleEmail", email);

            if(firstname.contains(" "))
                firstname = firstname.substring(0, firstname.indexOf(" "));

            bundle.putString("firstname", firstname);
            bundle.putString("lastname", lastname);
            bundle.putString("email", email);
            bundle.putString("gender", " ");
            bundle.putString("imgurl", imgurl);

            Toast.makeText(Login2.this, "Login Successful", Toast.LENGTH_SHORT).show();

                checkIfUserExists(email, "google");

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

        //google
        if(requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }

        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //twitter
        client.onActivityResult(requestCode, resultCode, data);

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



    private void loginUser() {

        progressbar.show();
        progressbar.setMessage("Getting existing logged in status...");
        progressbar.setCancelable(false);


        String url = User.getInstance().BASE_URL + "login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response);

                        jwtToken = jsonObject.optString("token");
                        subscribed = jsonObject.optString("subscribed");

                    storeSPData("jwtToken", jwtToken);
                    storeSPData("subscribed", subscribed);

                        //to get the user data
                        String newurl = User.getInstance().BASE_URL + "user";

                        StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressbar.dismiss();

                                storeSPData("userdatamain", response);

                                try {

                                    JSONObject obj = new JSONObject(response);
                                    String isClient = obj.optString("isClient");

                                    if(isClient.equals("true")) {
                                        Intent i = new Intent(Login2.this,Main3Activity.class)
                                                .putExtra("userdata", response).putExtra("subscribed", subscribed);
                                        Log.e("userdata",response);
                                        startActivity(i);
                                    } else {
                                        Intent i = new Intent(Login2.this,Main2Activity.class)
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

                params.put("username", getSPData("uname"));
                params.put("password", getSPData("pword"));

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }





    void checkIfUserExists(String email, String source) {

        if (source.equals("facebook")) {

            AndroidNetworking.post(User.getInstance().BASE_URL + "loginFB")
                    .addBodyParameter("facebookEmail", email)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.e("directlogintest", response.toString());

                            if (!response.optString("token").equals("")) {
                                storeSPData("jwtToken", response.optString("token"));

                                loginUserDirectly();

                            } else if(response.optString("message").equals("User not found in Database")) {

                                 Intent i = new Intent(Login2.this, StartingScreen.class);
                                            i.putExtras(bundle);
                                            startActivity(i);
                            }

                        }
                        @Override
                        public void onError(ANError error) {
                            error.printStackTrace();
                        }
                    });

        } else if (source.equals("google")) {

            AndroidNetworking.post(User.getInstance().BASE_URL + "loginGoogle")
                    .addBodyParameter("googleEmail", email)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (!response.optString("token").equals("")) {

                                Log.e("directlogintest", response.toString());

                                storeSPData("jwtToken", response.optString("token"));
                                 loginUserDirectly();

                            } else if(response.optString("message").equals("User not found in Database")){

                               Intent i = new Intent(Login2.this, StartingScreen.class);
                                            i.putExtras(bundle);
                                            startActivity(i);
                            }

                        }
                        @Override
                        public void onError(ANError error) {
                            error.printStackTrace();
                        }
                    });

        } else if (source.equals("twitter")) {

            AndroidNetworking.post(User.getInstance().BASE_URL + "loginTwitter")
                    .addBodyParameter("twitterEmail", email)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (!response.optString("token").equals("")) {

                                Log.e("directlogintest", response.toString());

                                storeSPData("jwtToken", response.optString("token"));
                                 loginUserDirectly();

                            } else if(response.optString("message").equals("User not found in Database")) {

                                Intent i = new Intent(Login2.this, StartingScreen.class);
                                            i.putExtras(bundle);
                                            startActivity(i);
                            }

                        }
                        @Override
                        public void onError(ANError error) {
                            error.printStackTrace();
                        }
                    });

        }

    }



    void loginUserDirectly() {

        AndroidNetworking.get(User.getInstance().BASE_URL + "user")
                .addHeaders("authorization", getSPData("jwtToken"))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        storeSPData("userdatamain", response.toString());

                            String isClient = response.optString("isClient");

                            if(isClient.equals("true")) {
                                Intent i = new Intent(Login2.this,Main3Activity.class)
                                        .putExtra("userdata", response.toString()).putExtra("subscribed", subscribed);
                                Log.e("userdata",response.toString());
                                startActivity(i);
                            } else {
                                Intent i = new Intent(Login2.this,Main2Activity.class)
                                        .putExtra("userdata", response.toString()).putExtra("subscribed", subscribed);
                                Log.e("userdata",response.toString());
                                startActivity(i);
                            }

                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                    }
                });

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
