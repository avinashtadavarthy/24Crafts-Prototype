package com.twenty.four.crafts;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.twenty.four.crafts.garlandview.main.TalentHunterMain;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AppBarLayout appBarLayout;
    String emailVerified = "";
    int check = 0;
    DrawerLayout drawer;
    String dialogtextverifyemail = "Please verify your email to continue using the app";
    String userdata,subscribed,jwtToken;
    JSONObject userdatamain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ImageView aud_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.aud_handy);
        final ImageView fav_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.fav_handy);
        final ImageView inbox_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.inbox_handy);


        userdata = getIntent().getStringExtra("userdata");
        subscribed = getIntent().getStringExtra("subscribed");

        jwtToken = getSPData("jwtToken");


        try {
            userdatamain = new JSONObject(userdata);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        emailVerified = userdatamain.optString("emailVerification");
        storeSPData("emailVerified",emailVerified);



        int dataInt = 0;
        if(getIntent() != null) {

            dataInt = getIntent().getIntExtra("navbarposclient",0);
        }

        if(dataInt == 1)
        {
            aud_handy.setBackgroundColor(Color.parseColor("#123456"));
            fav_handy.setBackgroundColor(Color.parseColor("#000000"));
            inbox_handy.setBackgroundColor(Color.parseColor("#000000"));
        }

        else if(dataInt == 2)
        {
            fav_handy.setBackgroundColor(Color.parseColor("#123456"));
            aud_handy.setBackgroundColor(Color.parseColor("#000000"));
            inbox_handy.setBackgroundColor(Color.parseColor("#000000"));

        }

        else if(dataInt == 3)
        {

            inbox_handy.setBackgroundColor(Color.parseColor("#123456"));
            fav_handy.setBackgroundColor(Color.parseColor("#000000"));
            aud_handy.setBackgroundColor(Color.parseColor("#000000"));
        }

        else
        {
            inbox_handy.setBackgroundColor(Color.parseColor("#000000"));
            fav_handy.setBackgroundColor(Color.parseColor("#000000"));
            aud_handy.setBackgroundColor(Color.parseColor("#000000"));
        }








        if(emailVerified.equals("false"))
        {
            final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(new ContextThemeWrapper(Main3Activity.this,R.style.AlertDialog)).setMessage(dialogtextverifyemail).
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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



        if(savedInstanceState==null){
            navigationView.getMenu().performIdentifierAction(R.id.dashboard,0);
        }


        LinearLayout header_for_clients = (LinearLayout) navigationView.getHeaderView(0).findViewById(R.id.header_for_clients);
        header_for_clients.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Main3Activity.this, ProfileView.class)
                        .putExtra("thisistogetback", "do nothing")
                        .putExtra("fromwhom", "do nothing");
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
        if(emailVerified.equals("false"))
        {
            showSnackbar();
        }
    }



    private void showSnackbar() {

        final Snackbar snackbar = Snackbar.make(drawer,"Unverified Email",Snackbar.LENGTH_INDEFINITE);
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

    private int userRequest() {

        //to get the user data
        String newurl = User.getInstance().BASE_URL + "user";

        StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.e("userRequest",response);
                try {

                    storeSPData("userdatamain", response);

                    JSONObject obj = new JSONObject(response);


                    emailVerified = obj.optString("emailVerification");
                    Log.e("emailverified",emailVerified);
                    storeSPData("emailVerified",emailVerified);

                    check = check(emailVerified);

                    switch (check)
                    {
                        case 0:showSnackbar();break;
                        case 1:showSnackbar();break;
                    }

                    Log.e("check",check+"");



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



        return check;
    }

    private int check(String emailVerified) {

        if(emailVerified.equals("false"))
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

            AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(Main3Activity.this, R.style.AlertDialog))
                    .setMessage("Do you really want to exit?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.finishAffinity(Main3Activity.this);
                            finish();
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
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if(id == R.id.dashboard){
            Bundle data = new Bundle();
            Fragment fragment = new ClientDashboard();
            data.putString("tab", "audition"); //put string, int, etc in bundle with a key value
            fragment.setArguments(data);
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, fragment).commit();
            appBarLayout.setTargetElevation(0);

        } else if (id == R.id.talent_hunt) {
//            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new TalentHuntFragment()).commit();
                Intent intent = new Intent(Main3Activity.this,TalentHunterMain.class);
                startActivity(intent);
              if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.nearby) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new PeopleNearbyFragment()).commit();
            if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.directory) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new DirectoryFragment()).commit();
            if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.settings) {
            Intent i = new Intent(this,Settings.class).putExtra("type","clients");
            startActivity(i);

        } else if (id == R.id.clientverify){
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
