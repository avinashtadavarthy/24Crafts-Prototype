package com.twenty.four.crafts;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfileView extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {


    ImageButton edit_profile, profile_back, fav_profile;
    NestedScrollView nestedScrollView;
    ImageView video1, video2, video3;
    int i = 0;
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    String togetback = "Hello", fromwhom = "Hey";




    //on the profileview page
    TextView profile_personname, profile_craft, profile_age, profile_bio, profile_introles, profile_hometown, profile_residingin, profile_languagesspoken, profile_height, profile_weight, profile_chest, profile_waist, profile_facialhair, profile_skintone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        fragmentManager = getSupportFragmentManager();
        edit_profile = (ImageButton) findViewById(R.id.edit_profile);
        profile_back = (ImageButton) findViewById(R.id.profile_back);
        fav_profile = (ImageButton) findViewById(R.id.fav_profile);
        //video1 = findViewById(R.id.profileVideo1);
        video2 = findViewById(R.id.profileVideo2);
        video3 = findViewById(R.id.profileVideo3);

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
                Intent i = new Intent(getApplicationContext(), ProfileViewEdit.class);
                startActivity(i);
            }
        });

        fav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i % 2 != 0) {
                    ObjectAnimator animY = ObjectAnimator.ofFloat(fav_profile, "translationY", -100f, 0f);
                    animY.setDuration(800);
                    animY.setInterpolator(new BounceInterpolator());
                    animY.start();
                    fav_profile.setImageResource(R.drawable.heart_full);
                } else {
                    ObjectAnimator animY = ObjectAnimator.ofFloat(fav_profile, "translationY", -100f, 0f);
                    animY.setDuration(800);
                    animY.setInterpolator(new BounceInterpolator());
                    animY.start();
                    fav_profile.setImageResource(R.drawable.heart_outline);
                }
            }
        });


        //ViewPager viewPager = (ViewPager) findViewById(R.id.coverphotoviewpager);
        //ImageAdapter adapter = new ImageAdapter(this); //Here we are defining the Imageadapter object
        //viewPager.setAdapter(adapter); // Here we are passing and setting the adapter for the images



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

            JSONObject allTheUserDataJson = new JSONObject(getSPData("allTheUserData"));



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
        inflater.inflate(R.menu.menu_main, menu);
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
        super.onBackPressed();

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
