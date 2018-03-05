package com.twenty.four.crafts;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import com.twenty.four.crafts.garlandview.main.TalentHunterMain;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ImageView aud_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.aud_handy);
        final ImageView fav_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.fav_handy);
        final ImageView inbox_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.inbox_handy);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

}
