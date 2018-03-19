package com.twenty.four.crafts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.view.ContextThemeWrapper;
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
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
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

public class ProfileViewEdit extends AppCompatActivity implements IPickResult{

    SharedPref sharedPref;
    RecyclerView recyclerView;
    String userdatamain,jwtToken;

    CoordinatorLayout mainlayout;

    String togetback = "Hello", fromwhom = "Hey";


    boolean arrowDownDS = true, arrowDownSP = true;
    int check = 0;
    String tag;
    TextView danceStyles,sportsPlayed;

    ImageView subresult1,subresult3;

    ImageView result1,result2,result3,result4;

    TextView profile_personnameTitle, profile_personname, profile_craftnage, profile_bio, profile_introles, profile_hometown, profile_residingin, profile_languagesspoken, profile_height, profile_weight, profile_chest, profile_waist, profile_facialhair, profile_skintone;

    NestedScrollView nestedScrollView;


    ImageView video;

    LinearLayout mainLayoutImage,mainLayoutVideo;
    TextView text;
    View cellImage,cellVideo;
    int[] images;

    String dob,emailVerified;

    RecyclerView featuredPhotos;
    EditFeatPhotosHorizontalAdapter editFeatPhotosHorizontalAdapter;
    public int num;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view_edit_new);

        sharedPref = new SharedPref(getApplicationContext());

        mainlayout = findViewById(R.id.mainNewProfileView);

        mainLayoutImage = findViewById(R.id.linlayoutimagesprofview);
        mainLayoutVideo = findViewById(R.id.linlayoutvideosprofview);

        userdatamain = getSPData("userdatamain");
        jwtToken = getSPData("jwtToken");

        AndroidNetworking.initialize(getApplicationContext());

        nestedScrollView = findViewById(R.id.nestedScrollView);


        if(getSPData("gender").equals("male"))
            images = new int[]{R.drawable.femaleleft, R.drawable.female, R.drawable.femaleright};

        else
            images = new int[]{R.drawable.maleleft, R.drawable.male_front, R.drawable.maleright};




        featuredPhotos = (RecyclerView) findViewById(R.id.featuredPhotos);

        try {

            int photosUploaded = Integer.parseInt(new JSONObject(userdatamain).optString("photosUploaded"));
            int photoCount = Integer.parseInt(new JSONObject(userdatamain).optString("photoCount"));

                /*String[] photourls = new String[photosUploaded];
                String id = new JSONObject(userdatamain).optString("_id");

                for(int i = 1; i<=photosUploaded; i++) {
                   photourls[i-1] = User.getInstance().BASE_URL + "users/" + id + "/photos/Image" + i + ".png";
                }*/

            JSONArray photoUrlsjson = new JSONObject(userdatamain).optJSONArray("photoURLS");
            ArrayList<String> photoUrlslist = new ArrayList<>();

            for(int i = 0; i<photoUrlsjson.length(); i++) {
                photoUrlslist.add(i,photoUrlsjson.getString(i));
            }

            final String[] photoUrls = photoUrlslist.toArray(new String[0]);

            for (String photoUrl : photoUrls) {
                Log.e("array", photoUrl + '\n');
            }


            editFeatPhotosHorizontalAdapter = new EditFeatPhotosHorizontalAdapter(getApplicationContext(), photoUrls, photoCount);
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                featuredPhotos.setLayoutManager(horizontalLayoutManager);
                featuredPhotos.setAdapter(editFeatPhotosHorizontalAdapter);


            featuredPhotos.addOnItemTouchListener(new RecyclerItemClickListener(ProfileViewEdit.this, featuredPhotos,
                    new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            int ct = 0;

                            for(int i = 0; i<photoUrls.length; i++) {
                                String imageandext = photoUrls[i].substring(photoUrls[i].lastIndexOf('/') + 1);
                                int reqdno = Integer.parseInt(imageandext.substring(5, imageandext.length() - 4));

                                if (reqdno == position) {
                                    ct++; break;
                                }
                            }


                                if (ct > 0) {
                                    Toast.makeText(ProfileViewEdit.this, "delete image in url", Toast.LENGTH_SHORT).show();
                                } else {
                                    num = position;
                                    selectImage();
                                }

                        }

                        @Override public void onLongItemClick(View view, int position) {

                        }
                    })
            );



        } catch (JSONException e){
            e.printStackTrace();
        }






       /* for (int i = 0; i < 3; i++) {

            cellImage = getLayoutInflater().inflate(R.layout.imageviewcellnewprofview, null);

            final RoundedImageView imageView =  cellImage.findViewById(R.id._image);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // do whatever you want ...
                    Toast.makeText(ProfileViewEdit.this, (CharSequence) imageView.getTag(), Toast.LENGTH_SHORT).show();
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



        for (int i = 0; i < 4; i++) {

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
        }


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final String fullName = sharedPref.getSPData(getApplicationContext(), "name");
        final String title = fullName.split(" ")[0] + "'s Profile";


        profile_personnameTitle = findViewById(R.id.tv_title); profile_personnameTitle.setText(title);

        profile_craftnage = findViewById(R.id.tv_info); profile_craftnage.setText("craft and age");

        profile_personname = findViewById(R.id.tv_name); profile_personname.setText(fullName);


        profile_bio = findViewById(R.id.tv_status); profile_bio.setText("The bio goes here");

        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);
        result4 = findViewById(R.id.result4);

        subresult1 = findViewById(R.id.subresult1);
        subresult3 = findViewById(R.id.subresult3);

        danceStyles = findViewById(R.id.danceStyles);
        sportsPlayed = findViewById(R.id.sportsPlayed);

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

            profile_personnameTitle.setText(new JSONObject(userdatamain).optString("name").split(" ")[0] + "'s Profile");

            profile_personname.setText(new JSONObject(userdatamain).optString("name"));
            profile_craftnage.setText(User.getInstance().getCategoryFromTag(new JSONObject(userdatamain).optString("category")) + ", " + Age);

            profile_bio.setText(new JSONObject(userdatamain).optString("bio"));
            //profile_introles.setText(new JSONObject(userdatamain).optString("interestedRoles"));;

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

            if(new JSONObject(userdatamain).optBoolean("canDance"))
            {
                result1.setImageResource(R.drawable.icon_green_tick);
                subresult1.setImageResource(R.drawable.arrowicon);
            }

            else
                result1.setImageResource(R.drawable.icon_grey_tick);


            if(new JSONObject(userdatamain).optBoolean("canSwim"))
            {
                result2.setImageResource(R.drawable.icon_green_tick);
            }


            else
                result2.setImageResource(R.drawable.icon_grey_tick);



            if(new JSONObject(userdatamain).optBoolean("playsSports"))
            {
                result3.setImageResource(R.drawable.icon_green_tick);
                subresult3.setImageResource(R.drawable.arrowicon);

            }


            else
                result3.setImageResource(R.drawable.icon_grey_tick);


            if(new JSONObject(userdatamain).optBoolean("havePassport"))
            {
                result4.setImageResource(R.drawable.icon_green_tick);
            }


            else
                result4.setImageResource(R.drawable.icon_grey_tick);


        } catch (JSONException e) {
            e.printStackTrace();
        }



        Glide.with(this)
                .load("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
                .placeholder(R.drawable.avatar_placeholder)
                .bitmapTransform(new CropCircleTransformation(this))
                .into((ImageView) findViewById(R.id.avatar));



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

    private void selectImage() {

        PickSetup setup = new PickSetup()
                .setTitle("Choose Image from: ")
                .setFlip(true)
                .setMaxSize(500)
                .setPickTypes(EPickType.CAMERA,EPickType.GALLERY)
                .setIconGravity(Gravity.CENTER)
                .setButtonOrientation(LinearLayoutCompat.VERTICAL)
                .setSystemDialog(true);

        PickImageDialog.build(setup).show(ProfileViewEdit.this);

    }


    public void playVideo()
    {
        Intent i = new Intent(getApplicationContext(),YoutubePlayerActivity.class);
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







    @Override
    public void onPickResult(PickResult pickResult) {

        if(pickResult.getError() == null)
        {
            Bitmap bitmap = pickResult.getBitmap();

            //setImageinCorrectLocation(bitmap);
            uploadImage(bitmap);
        }
    }



    private void setImageinCorrectLocation(Bitmap bitmap) {

        mainLayoutImage.removeAllViews();

        for (int i = 0; i < 3; i++) {

            cellImage = getLayoutInflater().inflate(R.layout.imageviewcellnewprofview, null);

            final RoundedImageView imageView =  cellImage.findViewById(R.id._image);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // do whatever you want ...
                    Toast.makeText(ProfileViewEdit.this, (CharSequence) imageView.getTag(), Toast.LENGTH_SHORT).show();

                }
            });

            imageView.setTag("Image"+(i+1));


            if(imageView.getTag().equals(tag))
                imageView.setImageBitmap(bitmap);

            else
                imageView.setImageResource(images[i]);

            mainLayoutImage.addView(cellImage);
        }
    }

    private void uploadImage(Bitmap bitmap) {

        File f = new File(getApplicationContext().getCacheDir(),"Image"+(num) + ".png");

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

                        Toast.makeText(ProfileViewEdit.this,response,Toast.LENGTH_LONG).show();

                        //to update userdatamain in shared prefs
                        sharedPref.updateUserDataMain(getApplicationContext());

                        editFeatPhotosHorizontalAdapter.notifyDataSetChanged();

                        recreate();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ProfileViewEdit.this,"Error: " + anError.getErrorBody(),Toast.LENGTH_LONG).show();

                    }
                });
    }


    //options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profileview_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case  R.id.action_edit_done:

                AlertDialog.Builder a_builder1 = new AlertDialog.Builder(new ContextThemeWrapper(ProfileViewEdit.this, R.style.AlertDialog));
                a_builder1.setMessage("Save your changes and quit editing?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert1 = a_builder1.create();
                alert1.show();
                alert1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {

        AlertDialog.Builder a_builder = new AlertDialog.Builder(new ContextThemeWrapper(ProfileViewEdit.this, R.style.AlertDialog));
        a_builder.setMessage("Discard your changes and quit editing?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = a_builder.create();
        alert.show();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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
