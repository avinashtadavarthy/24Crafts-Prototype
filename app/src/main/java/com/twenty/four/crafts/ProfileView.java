package com.twenty.four.crafts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.twenty.four.crafts.registration.signup3;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class ProfileView extends AppCompatActivity{

    SharedPref sharedPref;
    RecyclerView recyclerView;
    String userdatamain,jwtToken;

    CoordinatorLayout mainlayout;
    String responsePhotos;

    String togetback = "Hello", fromwhom = "Hey";

    final ArrayList<String> videoIDs = new ArrayList<>();


    boolean arrowDownDS = true, arrowDownSP = true;
    int check = 0;
    String tag;
    TextView danceStyles,sportsPlayed;

    ImageView subresult1,subresult3;

    ImageView result1,result2,result3,result4;

    TextView profile_personnameTitle, profile_personname, profile_craftnage, profile_bio, profile_introles, profile_hometown, profile_residingin, profile_languagesspoken, profile_height, profile_weight, profile_chest, profile_waist, profile_facialhair, profile_skintone;

    NestedScrollView nestedScrollView;

    LinearLayout featuredphotoslayout, featuredvideoslayout, photosandvideoslayout;
    RecyclerView featuredPhotos,featuredVideos;
    FeaturedPhotosHorizontalAdapter featuredPhotosHorizontalAdapter;
    FeaturedVideosHorizontalAdapter featuredVideosHorizontalAdapter;

    ImageView video;

    TextView text;
    View cellImage,cellVideo;
    int[] images;

    String dob, emailVerified;

    FloatingActionButton fav_profile, message_profile;
    Boolean isfav = false;

    String viewingmyprofile="false";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view_new);
        AndroidNetworking.initialize(getApplicationContext());


        sharedPref = new SharedPref(getApplicationContext());
        mainlayout = findViewById(R.id.mainNewProfileView);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        fav_profile = findViewById(R.id.fav_profile);
        message_profile = findViewById(R.id.message_profile);

        featuredphotoslayout = (LinearLayout) findViewById(R.id.featuredphotoslayout);
        featuredvideoslayout = (LinearLayout) findViewById(R.id.featuredvideoslayout);
        photosandvideoslayout = findViewById(R.id.photosandvideoslayout);
        featuredPhotos = (RecyclerView) findViewById(R.id.featuredPhotos);
        featuredVideos = (RecyclerView) findViewById(R.id.featuredVideos);

        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);
        result4 = findViewById(R.id.result4);

        subresult1 = findViewById(R.id.subresult1);
        subresult3 = findViewById(R.id.subresult3);

        danceStyles = findViewById(R.id.danceStyles);
        sportsPlayed = findViewById(R.id.sportsPlayed);

        profile_personnameTitle = findViewById(R.id.tv_title);
        profile_craftnage = findViewById(R.id.tv_info);
        profile_personname = findViewById(R.id.tv_name);
        profile_bio = findViewById(R.id.tv_status);
        profile_hometown = (TextView) findViewById(R.id.profile_hometown);
        profile_residingin = (TextView) findViewById(R.id.profile_residingin);
        profile_languagesspoken = (TextView) findViewById(R.id.profile_languagesspoken);
        profile_height = (TextView) findViewById(R.id.profile_height);
        profile_weight = (TextView) findViewById(R.id.profile_weight);
        profile_chest = (TextView) findViewById(R.id.profile_chest);
        profile_waist = (TextView) findViewById(R.id.profile_waist);
        profile_facialhair = (TextView) findViewById(R.id.profile_facialhair);
        profile_skintone = (TextView) findViewById(R.id.profile_skintone);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //userdatamain = getSPData("userdatamain");
        jwtToken = getSPData("jwtToken");

        viewingmyprofile = getIntent().getStringExtra("viewingmyprofile");

        if(viewingmyprofile.equals("true")) {
            fav_profile.setVisibility(View.GONE);
            message_profile.setVisibility(View.GONE);

            AndroidNetworking.get(User.getInstance().BASE_URL + "user")
                    .setTag(this)
                    .addHeaders("authorization", jwtToken)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            storeSPData("userdatamain", response.toString());
                            populateFields(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            anError.printStackTrace();
                        }
                    });
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    User.getInstance().BASE_URL + "user/getDetails",
                    new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("otherresponse", response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        populateFields(jsonArray.getJSONObject(0));
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
                public Map<String,String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("authorization", jwtToken);
                    return params;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("idArray", getIntent().getStringExtra("userid"));
                    return params;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        }



        fav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation myAnim = AnimationUtils.loadAnimation(ProfileView.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 20);
                myAnim.setInterpolator(interpolator);

                if(!isfav){
                    isfav = true;
                    fav_profile.setImageResource(R.drawable.star_full);
                    fav_profile.startAnimation(myAnim);

                    AndroidNetworking.get(User.getInstance().BASE_URL + "user/favs/add/" + getIntent().getStringExtra("userid"))
                            .addHeaders("authorization", getSPData("jwtToken"))
                            .setPriority(Priority.LOW)
                            .build()
                            .getAsString(new StringRequestListener() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(ProfileView.this, response, Toast.LENGTH_SHORT).show();
                                    sharedPref.updateUserDataMain(getApplicationContext());
                                }

                                @Override
                                public void onError(ANError anError) {
                                    anError.printStackTrace();
                                }
                            });

                } else {
                    isfav = false;
                    fav_profile.setImageResource(R.drawable.star);
                    fav_profile.startAnimation(myAnim);

                    AndroidNetworking.get(User.getInstance().BASE_URL + "user/favs/remove/" + getIntent().getStringExtra("userid"))
                            .addHeaders("authorization", getSPData("jwtToken"))
                            .setPriority(Priority.LOW)
                            .build()
                            .getAsString(new StringRequestListener() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(ProfileView.this, response, Toast.LENGTH_SHORT).show();
                                    sharedPref.updateUserDataMain(getApplicationContext());
                                }

                                @Override
                                public void onError(ANError anError) {
                                    anError.printStackTrace();
                                }
                            });
                }
            }
        });


        message_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myAnim = AnimationUtils.loadAnimation(ProfileView.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 20);
                myAnim.setInterpolator(interpolator);

                message_profile.startAnimation(myAnim);
                Toast.makeText(ProfileView.this, "Redirect to messaging page", Toast.LENGTH_SHORT).show();

            }
        });


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


        featuredPhotos.addOnItemTouchListener(new RecyclerItemClickListener(ProfileView.this, featuredPhotos ,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(ProfileView.this, "Make a gallery like view to display the images", Toast.LENGTH_SHORT).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );


        featuredVideos.addOnItemTouchListener(new RecyclerItemClickListener(ProfileView.this, featuredVideos,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        playVideo(position);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));


        final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            final View headerImage = findViewById(R.id.header_image);
            final View headerInfo = findViewById(R.id.header_info);
            final View avatar = findViewById(R.id.avatar_border);
            final LinearLayout texts = (LinearLayout) findViewById(R.id.texts);

            final int avatarHOffset = getResources().getDimensionPixelSize(R.dimen.profile_avatar_h_offset);
            final int avatarVOffset = getResources().getDimensionPixelSize(R.dimen.profile_avatar_v_offset);
            final int avatarSize = getResources().getDimensionPixelSize(R.dimen.profile_avatar_size);
            final int textHOffset = getResources().getDimensionPixelSize(R.dimen.profile_texts_h_offset);
            final int textVMinOffset = getResources().getDimensionPixelSize(R.dimen.profile_texts_v_min_offset);
            final int textVMaxOffset = getResources().getDimensionPixelSize(R.dimen.profile_texts_v_max_offset);
            final int textVDiff = textVMaxOffset - textVMinOffset;
            final int header160 = getResources().getDimensionPixelSize(R.dimen.dp160);
            final int toolBarHeight;

            {
                final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
                        new int[] { android.R.attr.actionBarSize });
                toolBarHeight = (int) styledAttributes.getDimension(0, 0) + getStatusBarHeight();
                styledAttributes.recycle();

                avatar.setPivotX(0);
                avatar.setPivotY(0);
                texts.setPivotX(0);
                texts.setPivotY(0);
            }

            final ArrayList<Float> textStart = new ArrayList<>();

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                final int diff = toolBarHeight + verticalOffset;
                final int y = diff < 0 ? header160 - diff : header160;
                headerInfo.setTop(y);

                final FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) headerImage.getLayoutParams();
                lp.height = y;
                headerImage.setLayoutParams(lp);

                final int totalScrollRange = appBarLayout.getTotalScrollRange();
                final float ratio = ((float)totalScrollRange + verticalOffset) / totalScrollRange;

                final int avatarHalf = avatar.getMeasuredHeight() / 2;
                final int avatarRightest = appBarLayout.getMeasuredWidth() / 2 - avatarHalf - avatarHOffset;
                final int avatarTopest = avatarHalf + avatarVOffset;

                avatar.setX(avatarHOffset + avatarRightest * ratio);
                avatar.setY(avatarVOffset - avatarTopest * ratio);
                avatar.setScaleX(0.5f + 0.5f * ratio);
                avatar.setScaleY(0.5f + 0.5f * ratio);

                if (textStart.isEmpty() && verticalOffset == 0) {
                    for (int i = 0; i < texts.getChildCount(); i++) {
                        textStart.add(texts.getChildAt(i).getX());
                    }
                }

                texts.setX(textHOffset + (avatarSize * 0.5f - avatarVOffset) * (1f - ratio));
                texts.setY(textVMinOffset + textVDiff * ratio);
                texts.setScaleX(0.8f + 0.2f * ratio);
                texts.setScaleY(0.8f + 0.2f * ratio);
                for (int i = 0; i < textStart.size(); i++) {
                    texts.getChildAt(i).setX(textStart.get(i) * ratio);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {

                boolean isStarting = true;

                @Override
                public void onTransitionStart(Transition transition) {
                    if (isStarting) {
                        isStarting = false;

                        ViewCompat.setTransitionName(findViewById(R.id.header_image), null);
                        ViewCompat.setTransitionName(findViewById(R.id.recycler_view), null);
                    }
                }

                @Override public void onTransitionEnd(Transition transition) {}
                @Override public void onTransitionCancel(Transition transition) {}
                @Override public void onTransitionPause(Transition transition) {}
                @Override public void onTransitionResume(Transition transition) {}
            });
        }

    }



    void populateFields(JSONObject jsonObject) {

        try {

            if(jsonObject.optString("gender").equals("female"))
                profile_facialhair.setVisibility(View.GONE);

            Glide.with(ProfileView.this)
                    .load("http://" + jsonObject.optString("profileImageURL"))
                    .placeholder(R.drawable.avatar_placeholder)
                    .bitmapTransform(new CropCircleTransformation(ProfileView.this))
                    .into((ImageView) findViewById(R.id.avatar));

            profile_personnameTitle.setText(jsonObject.optString("name").split(" ")[0] + "'s Profile");

            profile_personname.setText(jsonObject.optString("name"));

            dob = jsonObject.optString("dob");
            int year = Integer.parseInt(dob.substring(0,4));
            int month = Integer.parseInt(dob.substring(5,7));
            int day = Integer.parseInt(dob.substring(8,10));
            String Age = User.getInstance().getAge(year,month,day);

            profile_craftnage.setText(User.getInstance().getCategoryFromTag(jsonObject.optString("category")) + ", " + Age);

            profile_bio.setText(jsonObject.optString("bio"));
            //profile_introles.setText(jsonObject.optString("interestedRoles"));;

            profile_hometown.setText(jsonObject.optString("native"));
            profile_residingin.setText(jsonObject.optString("residingIn"));
            profile_skintone.setText(jsonObject.optString("skinTone"));
            profile_chest.setText(jsonObject.optString("chestSize"));
            profile_waist.setText(jsonObject.optString("waistSize"));
            profile_facialhair.setText(jsonObject.optString("facialHair"));
            profile_height.setText(jsonObject.optString("height"));
            profile_weight.setText(jsonObject.optString("weight"));

            String strlangs = jsonObject.optString("languagesSpoken");
            profile_languagesspoken.setText(strlangs.substring(2, strlangs.length()-4));


            int photosUploaded = Integer.parseInt(jsonObject.optString("photosUploaded"));
            if(photosUploaded == 0) {
                featuredphotoslayout.setVisibility(View.GONE);
            } else {

                /*String[] photourls = new String[photosUploaded];

                for(int i = 1; i<=photosUploaded; i++) {
                   photourls[i-1] = User.getInstance().BASE_URL + "users/" + id + "/photos/Image" + i + ".png";
                }*/

                JSONArray photoUrlsjson = jsonObject.optJSONArray("photoURLS");
                ArrayList<String> photoUrlslist = new ArrayList<>();

                for(int j = 0; j<photoUrlsjson.length(); j++) {
                    photoUrlslist.add(j,photoUrlsjson.getString(j));
                }

                String[] photoUrls = photoUrlslist.toArray(new String[0]);

                for (String photoUrl : photoUrls) {
                    Log.e("array", photoUrl + '\n');
                }

                featuredPhotosHorizontalAdapter = new FeaturedPhotosHorizontalAdapter(getApplicationContext(), photoUrls);
                LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                featuredPhotos.setLayoutManager(horizontalLayoutManager1);
                featuredPhotos.setAdapter(featuredPhotosHorizontalAdapter);
            }


            int videosUploaded = Integer.parseInt(jsonObject.optString("videosUploaded"));
            if(videosUploaded == 0) {
                featuredvideoslayout.setVisibility(View.GONE);
            } else {

                JSONArray jsonArray = jsonObject.optJSONArray("videoYoutubeURLS");

                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject current = (JSONObject) jsonArray.get(i);
                    Log.e("vidID", current.optString("id"));
                    videoIDs.add(current.optString("id"));
                }

                featuredVideosHorizontalAdapter = new FeaturedVideosHorizontalAdapter(getApplicationContext(), videoIDs);
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                featuredVideos.setLayoutManager(horizontalLayoutManager);
                featuredVideos.setAdapter(featuredVideosHorizontalAdapter);

            }


            if(photosUploaded==0 && videosUploaded==0) {
                photosandvideoslayout.setVisibility(View.GONE);
            }



            if(jsonObject.optBoolean("canDance"))
            {
                result1.setImageResource(R.drawable.icon_green_tick);
                subresult1.setImageResource(R.drawable.arrowicon);
            }

            else
                result1.setImageResource(R.drawable.icon_grey_tick);


            if(jsonObject.optBoolean("canSwim"))
            {
                result2.setImageResource(R.drawable.icon_green_tick);
            }


            else
                result2.setImageResource(R.drawable.icon_grey_tick);



            if(jsonObject.optBoolean("playsSports"))
            {
                result3.setImageResource(R.drawable.icon_green_tick);
                subresult3.setImageResource(R.drawable.arrowicon);

            }

            else
                result3.setImageResource(R.drawable.icon_grey_tick);


            if(jsonObject.optBoolean("havePassport"))
            {
                result4.setImageResource(R.drawable.icon_green_tick);
            }


            else
                result4.setImageResource(R.drawable.icon_grey_tick);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }





    public void displayPhotos()
    {

    }

   /* private void selectImage() {

        PickSetup setup = new PickSetup()
                .setTitle("Choose Image from: ")
                .setFlip(true)
                .setMaxSize(500)
                .setPickTypes(EPickType.CAMERA,EPickType.GALLERY)
                .setIconGravity(Gravity.CENTER)
                .setButtonOrientation(LinearLayoutCompat.VERTICAL)
                .setSystemDialog(true);

        PickImageDialog.build(setup).show(ProfileView.this);

    }
*/

    public void playVideo(int position)
    {
        Intent i = new Intent(getApplicationContext(),YoutubePlayerActivity.class);
        i.putExtra("videoID",videoIDs.get(position));
        startActivity(i);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
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


    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }







  /*  @Override
    public void onPickResult(PickResult pickResult) {

        if(pickResult.getError() == null)
        {
            Bitmap bitmap = pickResult.getBitmap();

            setImageinCorrectLocation(bitmap);
            uploadImage(bitmap);
        }
    }
*/


   /* private void setImageinCorrectLocation(Bitmap bitmap) {

        mainLayoutImage.removeAllViews();

        for (int i = 0; i < 3; i++) {

            cellImage = getLayoutInflater().inflate(R.layout.imageviewcellnewprofview, null);

            final RoundedImageView imageView =  cellImage.findViewById(R.id._image);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // do whatever you want ...
                    Toast.makeText(ProfileView.this, (CharSequence) imageView.getTag(), Toast.LENGTH_SHORT).show();

                }
            });

            imageView.setTag("Image"+(i+1));


            if(imageView.getTag().equals(tag))
            imageView.setImageBitmap(bitmap);

            else
                imageView.setImageResource(images[i]);

            mainLayoutImage.addView(cellImage);
        }
    }*/

    private void uploadImage(Bitmap bitmap) {

        File f = new File(getApplicationContext().getCacheDir(),tag + ".png");

        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,byteArrayOutputStream);

        byte[] bitmapdata = byteArrayOutputStream.toByteArray();

        FileOutputStream fos;

        try {
            fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();


            postRequest(f);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void postRequest(File f) {

        String url = User.getInstance().BASE_URL + "user/upload/photo";
        AndroidNetworking.upload(url)
                .addMultipartFile("image",f)
                .setPriority(Priority.MEDIUM)
                .addHeaders("authorization",getSPData("jwtToken"))
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {

                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ProfileView.this,response,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ProfileView.this,"Error: " + anError.getErrorBody(),Toast.LENGTH_LONG).show();

                    }
                });

    }



    @Override
    public void onBackPressed() {

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



    //options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(viewingmyprofile.equals("true")) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_profileview, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case  R.id.action_edit:
                Intent intent = new Intent(getApplicationContext(), ProfileViewEdit.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }




    //shared Preferences
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







       /* if(getSPData("gender").equals("male"))
            images = new int[]{R.drawable.femaleleft, R.drawable.female, R.drawable.femaleright};
        else
            images = new int[]{R.drawable.maleleft, R.drawable.male_front, R.drawable.maleright};
*/




               /* AndroidNetworking.get(User.getInstance().BASE_URL + "user")
                        .setTag(this)
                        .addHeaders("authorization",jwtToken)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });*/

               /* Glide.with(this)
                        .load("http://" + new JSONObject(userdatamain).optString("profileImageURL"))
                        .placeholder(R.drawable.avatar_placeholder)
                        .bitmapTransform(new CropCircleTransformation(this))
                        .into((ImageView) findViewById(R.id.avatar));

                JSONArray photoUrlsjson = new JSONObject(userdatamain).optJSONArray("photoURLS");
                ArrayList<String> photoUrlslist = new ArrayList<>();

                for(int i = 0; i<photoUrlsjson.length(); i++) {
                    photoUrlslist.add(i,photoUrlsjson.getString(i));
                }

                String[] photoUrls = photoUrlslist.toArray(new String[0]);

                for (String photoUrl : photoUrls) {
                    Log.e("array", photoUrl + '\n');
                }*/

                /*featuredVideosHorizontalAdapter = new FeaturedVideosHorizontalAdapter(getApplicationContext(), photoUrls);
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                featuredVideos.setLayoutManager(horizontalLayoutManager);
                featuredVideos.setAdapter(featuredPhotosHorizontalAdapter);

*/




       /* for (int i = 0; i < images.length; i++) {

            cellImage = getLayoutInflater().inflate(R.layout.imageviewcellnewprofview, null);

            final RoundedImageView imageView =  cellImage.findViewById(R.id._image);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // do whatever you want ...
                    Toast.makeText(ProfileView.this, (CharSequence) imageView.getTag(), Toast.LENGTH_SHORT).show();
                    tag = imageView.getTag().toString();

                    selectImage();
                }

            });

            imageView.setTag("Image"+(i+1));

            text = (TextView) cellImage.findViewById(R.id._imageName);

            imageView.setImageResource(images[i]);
            text.setText("Image"+(i+1));

            mainLayoutImage.addView(cellImage);
        }
*/



      /*  for (int i = 0; i < 4; i++) {

            cellVideo = getLayoutInflater().inflate(R.layout.videocellnewprofview, null);

            video =  cellVideo.findViewById(R.id._video);
            video.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // do whatever you want ...
                  playVideo();
                }
            });



            video.setTag("Video"+(i+1));
            Picasso.with(getApplicationContext()).load("https://img.youtube.com/vi/eGCM444_mN0/mqdefault.jpg").into(video);


            mainLayoutVideo.addView(cellVideo);
        }*/


