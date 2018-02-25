package com.twenty.four.crafts;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.kila.apprater_dialog.lars.AppRater;
import com.twenty.four.crafts.auditions.AuditionsTab;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    AppBarLayout appBarLayout;
    DrawerLayout mDrawerLayout;

    JSONObject userdatamain = null;
    String userdata, subscribed;

    TextView nav_name, nav_craft, coinCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AppRater.StarBuilder(this,"com.twenty.four.crafts")
                .showDefault()
                .minimumNumberOfStars(3)
                .email("raku18998@gmail.com")
                .timesToLaunch(3)
                .daysToWait(1)
                .appLaunched();

        setContentView(R.layout.activity_main2);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        mDrawerLayout = findViewById(R.id.drawer_layout);

       /* nav_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_name);
        nav_craft = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_craft);
        coinCount = (TextView) navigationView.getHeaderView(0).findViewById(R.id.coinCount);

        nav_name.setText(userdatamain.optString("name"));
        nav_craft.setText(userdatamain.optString("category"));
        coinCount.setText(userdatamain.optString("coinCount"));*/

        /*  SharedPreferences sharedPref = this.getPreferences(MODE_PRIVATE);
        String jwttoken = sharedPref.getString(getString(R.string.jwtTokenKey), null);
        if(jwttoken==null){
            Intent intent = new Intent(this, Login2.class);
            startActivity(intent);
        }
        */

        userdata = getIntent().getStringExtra("userdata");
        subscribed = getIntent().getStringExtra("subscribed");

        try {
            userdatamain = new JSONObject(userdata);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(subscribed.equals("false")) {
            final LovelyCustomDialog lovelyCustomDialog = new LovelyCustomDialog(this);

            lovelyCustomDialog.setView(R.layout.activity_subscribe_pop_up)
                    .setTopColorRes(R.color.indigo_500)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("Subscribe")
                    .setMessage("Subscribe to 24Crafts to enjoy using the app's features. Subscription allows Producers/Directors to find you....")
                    .setListener(R.id.SubscribeButton, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Main2Activity.this,PurchaseCoins.class);
                            startActivity(intent);
                        }
                    })
                    .setListener(R.id.ContinueButton, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(new ContextThemeWrapper(Main2Activity.this, R.style.AlertDialog));
                            builder.setMessage("Free Account only gives you access to the DIRECTORY. Producers/Directors will not be able to contact you...")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things
                                            dialog.cancel();
                                        }
                                    });

                            android.app.AlertDialog alert = builder.create();
                            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alert.show();
                        }
                    })
                    .show();
        }



        navigationView.setNavigationItemSelectedListener(this);
        final ImageView aud_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.aud_handy);
        final ImageView fav_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.fav_handy);
        final ImageView inbox_handy = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.inbox_handy);

        if(User.getInstance().navbarpos == 1)
      {
          aud_handy.setBackgroundColor(Color.parseColor("#123456"));
          fav_handy.setBackgroundColor(Color.parseColor("#000000"));
          inbox_handy.setBackgroundColor(Color.parseColor("#000000"));
      }

      else if(User.getInstance().navbarpos == 2)
      {
          fav_handy.setBackgroundColor(Color.parseColor("#123456"));
          aud_handy.setBackgroundColor(Color.parseColor("#000000"));
          inbox_handy.setBackgroundColor(Color.parseColor("#000000"));

      }

      else if(User.getInstance().navbarpos == 3)
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

        //to alter the display size on scroll
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_craft);
        final FrameLayout contentLayout = (FrameLayout) findViewById(R.id.content_frame_crafts);

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
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();



        if(savedInstanceState==null){
            navigationView.getMenu().performIdentifierAction(R.id.dashboard,0);
        }

        ImageView purchase_coins = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.purchase_coins);
        purchase_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, PurchaseCoins.class);
                startActivity(i);
            }
        });

        RelativeLayout nav_header_crafts = (RelativeLayout) navigationView.getHeaderView(0).findViewById(R.id.nav_header_crafts);
        nav_header_crafts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Main2Activity.this, ProfileView.class)
                        .putExtra("thisistogetback", "do nothing")
                        .putExtra("fromwhom", "do nothing");
                startActivity(i);
            }
        });


        final Bundle data = new Bundle();//Use bundle to pass data

        aud_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aud_handy.setBackgroundColor(Color.parseColor("#123456"));
                fav_handy.setBackgroundColor(Color.parseColor("#000000"));
                inbox_handy.setBackgroundColor(Color.parseColor("#000000"));

                Fragment fragment = new CraftsmenDashboard();//Get Fragment Instance
                data.putString("tab", "audition");//put string, int, etc in bundle with a key value
                fragment.setArguments(data);//Finally set argument bundle to fragment
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, fragment).commit();

                DrawerLayout mDrawerLayout;
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();

            }
        });




        fav_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fav_handy.setBackgroundColor(Color.parseColor("#123456"));
                aud_handy.setBackgroundColor(Color.parseColor("#000000"));
                inbox_handy.setBackgroundColor(Color.parseColor("#000000"));

                Fragment fragment = new CraftsmenDashboard();//Get Fragment Instance
                data.putString("tab", "fav");//put string, int, etc in bundle with a key value
                fragment.setArguments(data);//Finally set argument bundle to fragment
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, fragment).commit();

                DrawerLayout mDrawerLayout;
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();
            }
        });




        inbox_handy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 inbox_handy.setBackgroundColor(Color.parseColor("#123456"));
                fav_handy.setBackgroundColor(Color.parseColor("#000000"));
                aud_handy.setBackgroundColor(Color.parseColor("#000000"));


                Fragment fragment = new CraftsmenDashboard();//Get Fragment Instance
                data.putString("tab", "message");//put string, int, etc in bundle with a key value
                fragment.setArguments(data);//Finally set argument bundle to fragment
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, fragment).commit();

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

            AlertDialog alert = new AlertDialog.Builder(new ContextThemeWrapper(Main2Activity.this,R.style.AlertDialog))
                    .setMessage("Do you really want to exit?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.finishAffinity(Main2Activity.this);
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

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

       /*if (id == R.id.inbox) {
           Fragment fragment = new InboxTab();
            fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, fragment).commit();
           appBarLayout.setTargetElevation(0);

       } else if (id == R.id.auditions) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new AuditionsTab()).commit();
           appBarLayout.setTargetElevation(0);

        } else */

        if(id == R.id.dashboard) {

            Bundle data = new Bundle();
            Fragment fragment = new CraftsmenDashboard();
            data.putString("tab", "audition"); //put string, int, etc in bundle with a key value
            fragment.setArguments(data);
            fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, fragment).commit();
            appBarLayout.setTargetElevation(0);

        } else if (id == R.id.newsfeed) {

           fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new NewsfeedFragment()).commit();
           if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.nearby) {
          // fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new MapsFragment()).commit();

           if(subscribed.equals("true"))
           {
               Intent i = new Intent(getApplicationContext(), MapsActivity.class);
               startActivity(i);
           } else {
               // grey out the option
               mDrawerLayout.closeDrawers();
               Toast.makeText(this, "Access Denied. Please Subscribe.", Toast.LENGTH_SHORT).show();
           }

           if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.encounters) {
           if(subscribed.equals("true")) {
           fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new EncountersMain()).commit();
           } else {
               // grey out the option
               mDrawerLayout.closeDrawers();
               Toast.makeText(this, "Access Denied. Please Subscribe.", Toast.LENGTH_SHORT).show();
           }

           if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

       } else if (id == R.id.directory) {

           fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new DirectoryFragment()).commit();
           if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.promote) {
           if(subscribed.equals("true")) {
           Intent i = new Intent(getApplicationContext(),PromoteProfilePopUp.class);
           startActivity(i);
           } else {
               // grey out the option
               mDrawerLayout.closeDrawers();
               Toast.makeText(this, "Access Denied. Please Subscribe.", Toast.LENGTH_SHORT).show();
           }

           //fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new SecondFragment()).commit();

        } else if (id == R.id.subscribe) {
           /*Intent i = new Intent(getApplicationContext(),SubscribePopUp.class);
           startActivity(i);*/


           final LovelyCustomDialog lovelyCustomDialog = new LovelyCustomDialog(this);

           lovelyCustomDialog.setView(R.layout.activity_subscribe_pop_up)
                   .setTopColorRes(R.color.indigo_500)
                   .setIcon(R.mipmap.ic_launcher)
                   .setTitle("Subscribe")
                   .setMessage("Subscribe to 24Crafts to enjoy using the app's features. Subscription allows Producers/Directors to find you....")
                   .setListener(R.id.SubscribeButton, new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent intent = new Intent(Main2Activity.this,PurchaseCoins.class);
                           startActivity(intent);
                       }
                   })
                   .setListener(R.id.ContinueButton, new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                           android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(new ContextThemeWrapper(Main2Activity.this, R.style.AlertDialog));
                           builder.setMessage("Free Account only gives you access to the DIRECTORY. Producers/Directors will not be able to contact you...")
                                   .setCancelable(false)
                                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                       public void onClick(DialogInterface dialog, int id) {
                                           //do things
                                           dialog.cancel();
                                       }
                                   });

                           android.app.AlertDialog alert = builder.create();
                           alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                           alert.show();
                       }
                   })
                   .show();

          /* new PanterDialog(Main2Activity.this)
                   .setHeaderBackground(R.drawable.bg_gradient)
                   .setHeaderLogo(R.drawable.logo)
                   .setPositive("SUBSCRIBE")
                   .setNegative("CONTINUE FREE")
                   .withAnimation(Animation.POP)
                   .setMessage("Subscribe to 24Crafts to enjoy using the app's features. Subscription allows Producers/Directors to find you")
                   .isCancelable(false)
                   .show();*/
           // fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new SecondFragment()).commit();

        } else if (id == R.id.settings) {
           Intent i = new Intent(this,Settings.class).putExtra("type","crafts");
           startActivity(i);

       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
