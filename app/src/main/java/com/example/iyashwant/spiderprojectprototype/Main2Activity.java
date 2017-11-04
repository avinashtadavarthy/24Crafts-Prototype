package com.example.iyashwant.spiderprojectprototype;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.iyashwant.spiderprojectprototype.Swipe.Tinderswipemain;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageButton profile_edits = (ImageButton) navigationView.getHeaderView(0).findViewById(R.id.profile_edits);
        profile_edits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Main2Activity.this, ProfileView2.class);
                startActivity(i);
            }
        });

        if(savedInstanceState==null){
            navigationView.getMenu().performIdentifierAction(R.id.inbox,0);
        }
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
            fragmentManager.beginTransaction().replace(R.id.content_frame, new InboxTab2()).commit();

        } else if (id == R.id.auditions) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AuditionsTab2()).commit();

        } else if (id == R.id.newsfeed) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SecondFragment()).commit();

        } else if (id == R.id.nearby) {
            Intent i = new Intent(this,PeopleNearby.class);
            startActivity(i);

        } else if (id == R.id.encounters) {
            Intent i = new Intent(getApplicationContext(),Tinderswipemain.class);
            startActivity(i);

        } else if (id == R.id.directory) {
            Toast.makeText(this, "Directory", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,Directory.class);
            startActivity(i);

        } else if (id == R.id.promote) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AuditionFragment()).commit();

        } else if (id == R.id.subscribe) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AuditionFragment()).commit();

        } else if (id == R.id.settings) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AuditionFragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
