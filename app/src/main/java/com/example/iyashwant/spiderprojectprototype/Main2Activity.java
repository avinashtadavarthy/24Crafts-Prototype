package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences sharedPref = this.getPreferences(MODE_PRIVATE);
        String jwttoken = sharedPref.getString(getString(R.string.jwtTokenKey), null);
        if(jwttoken==null){
            Intent intent = new Intent(this, Login2.class);
            startActivity(intent);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState==null){
            navigationView.getMenu().performIdentifierAction(R.id.inbox,0);
        }

        ImageButton profile_edits = (ImageButton) navigationView.getHeaderView(0).findViewById(R.id.profile_edits);
        profile_edits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Main2Activity.this, ProfileView2.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

       if (id == R.id.inbox) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new InboxTab()).commit();

        } else if (id == R.id.auditions) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AuditionsTab()).commit();

        } else if (id == R.id.newsfeed) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new NewsfeedFragment()).commit();

        } else if (id == R.id.nearby) {
           fragmentManager.beginTransaction().replace(R.id.content_frame, new PeopleNearbyFragment()).commit();

        } else if (id == R.id.encounters) {
           fragmentManager.beginTransaction().replace(R.id.content_frame, new SwipeFragment()).commit();

       } else if (id == R.id.directory) {
           fragmentManager.beginTransaction().replace(R.id.content_frame, new DirectoryFragment()).commit();

        } else if (id == R.id.promote) {
           Intent i = new Intent(getApplicationContext(),PromoteProfilePopUp.class);
           startActivity(i);
           //fragmentManager.beginTransaction().replace(R.id.content_frame, new SecondFragment()).commit();

        } else if (id == R.id.subscribe) {
           Intent i = new Intent(getApplicationContext(),SubscribePopUp.class);
           startActivity(i);
           // fragmentManager.beginTransaction().replace(R.id.content_frame, new SecondFragment()).commit();

        } else if (id == R.id.settings) {
           Intent i = new Intent(this,Settings.class);
           startActivity(i);

       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
