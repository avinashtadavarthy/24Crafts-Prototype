package com.twenty.four.crafts;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.AlertTheme;
import com.irozon.alertview.AlertView;
import com.irozon.alertview.objects.AlertAction;
import com.kila.apprater_dialog.lars.AppRater;
import com.twenty.four.crafts.talenthunter.GarlandViewMain;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.blurry.Blurry;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AppBarLayout appBarLayout;
    String emailVerified = "";
    int check = 0;
    DrawerLayout drawer;
    String dialogtextverifyemail = "Please verify your email to continue using the app";
    String userdata, subscribed, jwtToken;
    JSONObject userdatamain;

    TextView nav_name, nav_craft, coinCount;
    ImageView coverpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        new AppRater.StarBuilder(this, "com.twenty.four.crafts")
                .showDefault()
                .minimumNumberOfStars(3)
                .email("raku18998@gmail.com")
                .timesToLaunch(3)
                .daysToWait(1)
                .appLaunched();


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ImageView aud_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.aud_handy);
        final ImageView fav_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.fav_handy);
        final ImageView inbox_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.inbox_handy);


        userdata = getSPData("userdatamain");
        jwtToken = getSPData("jwtToken");
        subscribed = getSPData("subscribed");


        try {
            userdatamain = new JSONObject(userdata);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        emailVerified = userdatamain.optString("emailVerification");
        storeSPData("emailVerified", emailVerified);


        nav_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_name);
        nav_craft = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_craft);
        //      coinCount = (TextView) navigationView.getHeaderView(0).findViewById(R.id.coinCount);
        coverpic = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.coverpic);

        nav_name.setText(userdatamain.optString("name"));
        nav_craft.setText(User.getInstance().getCategoryFromTag(userdatamain.optString("category")));
//        coinCount.setText(userdatamain.optString("coinCount"));
        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.sample1);
        Blurry.with(getApplicationContext()).from(icon).into(coverpic);


        int dataInt = 0;
        if (getIntent() != null) {

            dataInt = getIntent().getIntExtra("navbarposclient", 0);
        }

        if (dataInt == 1) {
            aud_handy.setBackgroundColor(Color.parseColor("#123456"));
            fav_handy.setBackgroundColor(Color.parseColor("#000000"));
            inbox_handy.setBackgroundColor(Color.parseColor("#000000"));
        } else if (dataInt == 2) {
            fav_handy.setBackgroundColor(Color.parseColor("#123456"));
            aud_handy.setBackgroundColor(Color.parseColor("#000000"));
            inbox_handy.setBackgroundColor(Color.parseColor("#000000"));

        } else if (dataInt == 3) {

            inbox_handy.setBackgroundColor(Color.parseColor("#123456"));
            fav_handy.setBackgroundColor(Color.parseColor("#000000"));
            aud_handy.setBackgroundColor(Color.parseColor("#000000"));
        } else {
            inbox_handy.setBackgroundColor(Color.parseColor("#000000"));
            fav_handy.setBackgroundColor(Color.parseColor("#000000"));
            aud_handy.setBackgroundColor(Color.parseColor("#000000"));
        }


        if (emailVerified.equals("false")) {

            final AlertView alert = new AlertView("VERIFICATION", "Please verify your email to continue using the 24 Crafts App", AlertStyle.DIALOG);
            alert.addAction(new AlertAction("RESEND EMAIL", AlertActionStyle.POSITIVE, action -> {
                resendEmailRequest();
            }));
            alert.addAction(new AlertAction("Cancel", AlertActionStyle.DEFAULT, action -> {

            }));
            alert.setTheme(AlertTheme.LIGHT);
            alert.show(this);

//            final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(new ContextThemeWrapper(Main3Activity.this, R.style.AlertDialog)).setMessage(dialogtextverifyemail).
//                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    })
//                    .show();
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        }


        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_client);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_client);
        final FrameLayout contentLayout = (FrameLayout) findViewById(R.id.content_frame_clients);

        //to alter the display size on scroll
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) contentLayout.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, appBarLayout.getMeasuredHeight() + verticalOffset);
                contentLayout.requestLayout();
            }

        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            navigationView.getMenu().performIdentifierAction(R.id.dashboard, 0);
        }


        LinearLayout header_for_clients = (LinearLayout) navigationView.getHeaderView(0).findViewById(R.id.header_for_clients);
        header_for_clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main3Activity.this, ProfileView.class)
                        .putExtra("thisistogetback", "do nothing")
                        .putExtra("fromwhom", "do nothing")
                        .putExtra("viewingmyprofile", "true");
                startActivity(i);
            }
        });


        final Bundle data = new Bundle();//Use bundle to pass data


        aud_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aud_handy.setBackgroundColor(Color.parseColor("#123456"));
                fav_handy.setBackgroundColor(Color.parseColor("#000000"));
                inbox_handy.setBackgroundColor(Color.parseColor("#000000"));

                Fragment fragment = new ClientDashboard();//Get Fragment Instance
                data.putString("tab", "audition");//put string, int, etc in bundle with a key value
                fragment.setArguments(data);//Finally set argument bundle to fragment
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_clients, fragment).commit();

                DrawerLayout mDrawerLayout;
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();
            }
        });


        fav_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fav_handy.setBackgroundColor(Color.parseColor("#123456"));
                aud_handy.setBackgroundColor(Color.parseColor("#000000"));
                inbox_handy.setBackgroundColor(Color.parseColor("#000000"));

                Fragment fragment = new ClientDashboard();//Get Fragment Instance
                data.putString("tab", "fav");//put string, int, etc in bundle with a key value
                fragment.setArguments(data);//Finally set argument bundle to fragment
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_clients, fragment).commit();

                DrawerLayout mDrawerLayout;
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();

            }
        });


        inbox_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                inbox_handy.setBackgroundColor(Color.parseColor("#123456"));
                fav_handy.setBackgroundColor(Color.parseColor("#000000"));
                aud_handy.setBackgroundColor(Color.parseColor("#000000"));


                Fragment fragment = new ClientDashboard();//Get Fragment Instance
                data.putString("tab", "message");//put string, int, etc in bundle with a key value
                fragment.setArguments(data);//Finally set argument bundle to fragment
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_clients, fragment).commit();

                DrawerLayout mDrawerLayout;
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        emailVerified = getSPData("emailVerified");
        if (emailVerified.equals("false")) {
            showSnackbar();
        }
    }


    private void showSnackbar() {

        final Snackbar snackbar = Snackbar.make(drawer, "Unverified Email", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("REFRESH", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int c = userRequest();

            }
        });

        View snackbarView = snackbar.getView();

        snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbarBackground));

        if (check == 0)
            snackbar.show();

        else
            snackbar.dismiss();

    }

    private void resendEmailRequest() {

        jwtToken = getSPData("jwtToken");
        String url = User.getInstance().BASE_URL + "resendVerificationMail";

        AndroidNetworking.get(url)
                .addHeaders("authorization", jwtToken)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("resendEmail", response);

                        Toast.makeText(Main3Activity.this, response, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private int userRequest() {

        //to get the user data
        String newurl = User.getInstance().BASE_URL + "user";

        StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.e("userRequest", response);
                try {

                    storeSPData("userdatamain", response);

                    JSONObject obj = new JSONObject(response);


                    emailVerified = obj.optString("emailVerification");
                    Log.e("emailverified", emailVerified);
                    storeSPData("emailVerified", emailVerified);

                    check = check(emailVerified);

                    switch (check) {
                        case 0:
                            showSnackbar();
                            break;
                        case 1:
                            showSnackbar();
                            break;
                    }

                    Log.e("check", check + "");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("authorization", jwtToken);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(getRequest);


        return check;
    }

    private int check(String emailVerified) {

        if (emailVerified.equals("false"))
            return 0;

        else
            return 1;

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            AlertView alert = new AlertView("EXIT", "Do you want to exit from 24Crafts?", AlertStyle.IOS);
            alert.addAction(new AlertAction("Yes", AlertActionStyle.NEGATIVE, action -> {
                ActivityCompat.finishAffinity(Main3Activity.this);
                finish();
            }));
            alert.setTheme(AlertTheme.LIGHT);
            alert.show(this);

//            AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(Main3Activity.this, R.style.AlertDialog))
//                    .setMessage("Do you really want to exit?")
//                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.finishAffinity(Main3Activity.this);
//                            finish();
//                        }
//                    })
//                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .show();
//                    alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.dashboard) {
            Bundle data = new Bundle();
            Fragment fragment = new ClientDashboard();
            data.putString("tab", "audition"); //put string, int, etc in bundle with a key value
            fragment.setArguments(data);
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, fragment).commit();
            appBarLayout.setTargetElevation(0);

        } else if (id == R.id.talent_hunt) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new GarlandViewMain()).commit();
            if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.nearby) {
            //fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new PeopleNearbyFragment()).commit();
            Intent intent = new Intent(Main3Activity.this, RadarView.class);
            startActivity(intent);

            if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.directory) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new DirectoryFragment()).commit();
            if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.settings) {
            Intent i = new Intent(this, Settings.class).putExtra("type", "clients");
            startActivity(i);

        } else if (id == R.id.clientverify) {
            Intent i = new Intent(this, ClientVerification1Activity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
