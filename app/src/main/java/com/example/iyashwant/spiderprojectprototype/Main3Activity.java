package com.example.iyashwant.spiderprojectprototype;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
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
            navigationView.getMenu().performIdentifierAction(R.id.dashboard,0);
        }


        LinearLayout header_for_clients = (LinearLayout) navigationView.getHeaderView(0).findViewById(R.id.header_for_clients);
        header_for_clients.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Main3Activity.this, ProfileView.class);
                startActivity(i);
            }
        });

        ImageView aud_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.aud_handy);
        aud_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Main3Activity.this, "Connect to Auditions Tab", Toast.LENGTH_SHORT).show();

                DrawerLayout mDrawerLayout;
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();

            }
        });

        ImageView fav_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.fav_handy);
        fav_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Main3Activity.this, "Connect to Favourites Tab", Toast.LENGTH_SHORT).show();


                DrawerLayout mDrawerLayout;
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();

            }
        });

        ImageView inbox_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.inbox_handy);
        inbox_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Main3Activity.this, "Connect to Messages Tab", Toast.LENGTH_SHORT).show();

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
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if(id == R.id.dashboard){
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new ClientDashboard()).commit();

        } else if (id == R.id.talent_hunt) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new SecondFragment()).commit();

        } else if (id == R.id.nearby) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new PeopleNearbyFragment()).commit();

        } else if (id == R.id.directory) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_clients, new DirectoryFragment()).commit();

        } else if (id == R.id.settings) {
            Intent i = new Intent(this,Settings.class).putExtra("type","clients");
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
