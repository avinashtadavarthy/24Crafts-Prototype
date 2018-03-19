package com.twenty.four.crafts;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileViewOld extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {


    ImageButton edit_profile, profile_back;
    NestedScrollView nestedScrollView;
    ImageView video1, video2, video3;
    int i = 0;

    ImageView result1,result2,result3,result4;

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    String togetback = "Hello", fromwhom = "Hey";


    String dialogtextverifyemail = "Please verify your email to continue using the app";
    boolean arrowDownDS = true,arrowDownSP = true;


    int check = 0;
    String emailVerified = "",jwtToken = "";

    String userdatamain;
    String dob;


    TextView danceStyles,sportsPlayed;

    ImageView subresult1,subresult3;

    FloatingActionButton fav_profile, message_profile;
    Boolean isfav = false;



    //on the profileview page
    TextView profile_personname, profile_craft, profile_age, profile_bio, profile_introles, profile_hometown, profile_residingin, profile_languagesspoken, profile_height, profile_weight, profile_chest, profile_waist, profile_facialhair, profile_skintone;

    CoordinatorLayout mainlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        userdatamain = getSPData("userdatamain");
        AndroidNetworking.initialize(getApplicationContext());

        jwtToken = getSPData("jwtToken");
        mainlayout = findViewById(R.id.mainProfileViewLayout);

        fragmentManager = getSupportFragmentManager();
        edit_profile = (ImageButton) findViewById(R.id.edit_profile);
        profile_back = (ImageButton) findViewById(R.id.profile_back);

        //video1 = findViewById(R.id.profileVideo1);
        video2 = findViewById(R.id.profileVideo2);
        video3 = findViewById(R.id.profileVideo3);

        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);
        result4 = findViewById(R.id.result4);

        subresult1 = findViewById(R.id.subresult1);
        subresult3 = findViewById(R.id.subresult3);

        danceStyles = findViewById(R.id.danceStyles);
        sportsPlayed = findViewById(R.id.sportsPlayed);

        fav_profile = findViewById(R.id.fav_profile);
        message_profile = findViewById(R.id.message_profile);

        fav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation myAnim = AnimationUtils.loadAnimation(ProfileViewOld.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 20);
                myAnim.setInterpolator(interpolator);

                if(!isfav){
                    isfav = true;
                    fav_profile.setImageResource(R.drawable.star_full);
                    fav_profile.startAnimation(myAnim);
                    Toast.makeText(ProfileViewOld.this, "Added to favourites!", Toast.LENGTH_SHORT).show();
                } else {
                    isfav = false;
                    fav_profile.setImageResource(R.drawable.star);
                    fav_profile.startAnimation(myAnim);
                    Toast.makeText(ProfileViewOld.this, "Removed from favourites!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        message_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myAnim = AnimationUtils.loadAnimation(ProfileViewOld.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 20);
                myAnim.setInterpolator(interpolator);

                message_profile.startAnimation(myAnim);
                Toast.makeText(ProfileViewOld.this, "Redirect to messaging page", Toast.LENGTH_SHORT).show();

            }
        });


        //Picasso.with(getApplicationContext()).load("https://img.youtube.com/vi/eGCM444_mN0/mqdefault.jpg").into(video1);
        Picasso.with(getApplicationContext()).load("https://img.youtube.com/vi/eGCM444_mN0/mqdefault.jpg").into(video2);
        Picasso.with(getApplicationContext()).load("https://img.youtube.com/vi/eGCM444_mN0/mqdefault.jpg").into(video3);
        //DownloadThumbnailImageYoutubeVideo("https://www.youtube.com/watch?v=eGCM444_mN0");
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ProfileViewEditOld.class);
                startActivity(i);
            }
        });


        //ViewPager viewPager = (ViewPager) findViewById(R.id.coverphotoviewpager);
        //ImageAdapter adapter = new ImageAdapter(this); //Here we are defining the Imageadapter object
        //viewPager.setAdapter(adapter); // Here we are passing and setting the adapter for the images


        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    fav_profile.hide();
                    message_profile.hide();
                } else if (scrollY < oldScrollY) {
                    fav_profile.show();
                    message_profile.show();
                }

            }
        });

        //on the profileview page

        profile_personname = (TextView) findViewById(R.id.profile_personname);

        profile_craft = (TextView) findViewById(R.id.profile_craft);
        profile_age = (TextView) findViewById(R.id.profile_age);
        profile_bio = (TextView) findViewById(R.id.profile_bio);
        profile_introles = (TextView) findViewById(R.id.profile_introles);
        profile_hometown = (TextView) findViewById(R.id.profile_hometown);
        profile_residingin = (TextView) findViewById(R.id.profile_residingin);
        profile_languagesspoken = (TextView) findViewById(R.id.profile_languagesspoken);
        profile_height = (TextView) findViewById(R.id.profile_height);
        profile_weight = (TextView) findViewById(R.id.profile_weight);
        profile_chest = (TextView) findViewById(R.id.profile_chest);
        profile_waist = (TextView) findViewById(R.id.profile_waist);
        profile_facialhair = (TextView) findViewById(R.id.profile_facialhair);
        profile_skintone = (TextView) findViewById(R.id.profile_skintone);

        try {
             dob = new JSONObject(userdatamain).optString("dob");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int year = Integer.parseInt(dob.substring(0,4));
        int month = Integer.parseInt(dob.substring(5,7));
        int day = Integer.parseInt(dob.substring(8,10));

        String Age = User.getInstance().getAge(year,month,day);

        try {

                if(new JSONObject(userdatamain).optString("gender").equals("female"))
                    profile_facialhair.setVisibility(View.GONE);

                profile_personname.setText(new JSONObject(userdatamain).optString("name"));
                profile_craft.setText(new JSONObject(userdatamain).optString("category"));

                profile_age.setText(Age);

                profile_bio.setText(new JSONObject(userdatamain).optString("bio"));
                profile_introles.setText(new JSONObject(userdatamain).optString("interestedRoles"));;

                profile_hometown.setText(new JSONObject(userdatamain).optString("native"));
                profile_residingin.setText(new JSONObject(userdatamain).optString("residingIn"));
                profile_skintone.setText(new JSONObject(userdatamain).optString("skinTone"));
                profile_chest.setText(new JSONObject(userdatamain).optString("chestSize"));
                profile_waist.setText(new JSONObject(userdatamain).optString("waistSize"));
                profile_facialhair.setText(new JSONObject(userdatamain).optString("facialHair"));

                profile_languagesspoken.setText(new JSONObject(userdatamain).optString("languagesSpoken"));
                profile_height.setText(new JSONObject(userdatamain).optString("height"));
                profile_weight.setText(new JSONObject(userdatamain).optString("weight"));
                //profile_age.setText(new JSONObject(userdatamain).optString("age"));

                if(new JSONObject(userdatamain).optBoolean("canDance") == true)
                {
                    result1.setImageResource(R.drawable.icon_green_tick);
                    subresult1.setImageResource(R.drawable.arrowicon);
                }

                else
                    result1.setImageResource(R.drawable.icon_red_cross);


                if(new JSONObject(userdatamain).optBoolean("canSwim") == true)
                {
                    result2.setImageResource(R.drawable.icon_green_tick);
                }


                else
                    result2.setImageResource(R.drawable.icon_red_cross);



                if(new JSONObject(userdatamain).optBoolean("playsSports") == true)
                {
                    result3.setImageResource(R.drawable.icon_green_tick);
                    subresult3.setImageResource(R.drawable.arrowicon);

                }


                else
                    result3.setImageResource(R.drawable.icon_red_cross);


                if(new JSONObject(userdatamain).optBoolean("havePassport") == true)
                {
                    result4.setImageResource(R.drawable.icon_green_tick);
                }


                else
                    result4.setImageResource(R.drawable.icon_red_cross);


            } catch (JSONException e) {
                e.printStackTrace();
            }



    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("Send message");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("Like profile");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("Add to friends");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("Add to favorites");
        addFav.setResource(R.drawable.icn_4);

        MenuObject block = new MenuObject("Block user");
        block.setResource(R.drawable.icn_5);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
        return menuObjects;
    }
//f26b3a

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

        final Snackbar snackbar = Snackbar.make(mainlayout,"Unverified Email",Snackbar.LENGTH_INDEFINITE);

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

    /*private void resendEmailRequest() {

        jwtToken = getSPData("jwtToken");
        String url = User.getInstance().BASE_URL + "resendVerificationMail";

        AndroidNetworking.get(url)
                .addHeaders("authorization",jwtToken)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("resendEmail",response);

                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }*/


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


    public void makeDSVisible(View view)
    {
        if(arrowDownDS == true)
        {
            arrowDownDS = false;
            danceStyles.setVisibility(View.VISIBLE);
            subresult1.setImageResource(R.drawable.arrowiconinverted); //180
            try {
                danceStyles.setText(new JSONObject(userdatamain).optString("danceStyles"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        else
        {
            arrowDownDS = true;
            danceStyles.setVisibility(View.GONE);
            subresult1.setImageResource(R.drawable.arrowicon);
        }
    }

    public void makeSPVisible(View view)
    {
        if(arrowDownSP == true)
        {
            arrowDownSP = false;
            sportsPlayed.setVisibility(View.VISIBLE);
            subresult3.setImageResource(R.drawable.arrowiconinverted);
            try {
                sportsPlayed.setText(new JSONObject(userdatamain).optString("sportsPlayed"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        else
        {
            arrowDownSP = true;
            sportsPlayed.setVisibility(View.GONE);
            subresult3.setImageResource(R.drawable.arrowicon);
        }
    }


    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        //menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    /*  public void DownloadThumbnailImageYoutubeVideo(final String imgurl) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imgurl).getContent());
                    video1.post(new Runnable() {
                        @Override
                        public void run() {
                            if (bitmap != null) {
                                video1.setImageBitmap(bitmap);
                                video2.setImageBitmap(bitmap);
                                video3.setImageBitmap(bitmap);
                            }

                        }
                    });
                } catch (Exception e) {
                }
            }
        }).start();
*/


    public void playVideo(View view)
    {
        Intent i = new Intent(getApplicationContext(),YoutubePlayerActivity.class);
        startActivity(i);
    }




    @Override
    public void onBackPressed() {

        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        }

        togetback = getIntent().getStringExtra("thisistogetback");
        fromwhom = getIntent().getStringExtra("fromwhom");

        if(togetback.equals("getback") && fromwhom.equals("Crafts")) {

            Intent i = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(i);

        } else if(togetback.equals("getback") && fromwhom.equals("Clients")) {

            Intent i = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(i);

        } else {
            finish();
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
