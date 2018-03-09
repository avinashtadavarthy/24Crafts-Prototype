package com.twenty.four.crafts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileViewEdit extends AppCompatActivity {

    ImageButton profile_back, edit_done;

    CoordinatorLayout mainLayout;

    String userdatamain;

    String dialogtextverifyemail = "Please verify your email to continue using the app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view_edit);

        profile_back = (ImageButton) findViewById(R.id.profile_back);
        edit_done = (ImageButton) findViewById(R.id.edit_done);

        mainLayout = findViewById(R.id.mainProfileViewEditLayout);

        userdatamain = getSPData("userdatamain");

        profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(new ContextThemeWrapper(ProfileViewEdit.this, R.style.AlertDialog));
                a_builder.setMessage("Discard your changes and quit editing?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                onBackPressed();
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
        });

        edit_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(new ContextThemeWrapper(ProfileViewEdit.this, R.style.AlertDialog));
                a_builder.setMessage("Save your changes and quit editing?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                onBackPressed();
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
        });

    }


    @Override
    protected void onResume() {
        super.onResume();



        try {
            if(new JSONObject(userdatamain).optString("emailVerification").equals("false"))
            {

                Snackbar snackbar = Snackbar.make(mainLayout,"Unverified Email", Snackbar.LENGTH_INDEFINITE);

                snackbar.setAction("REFRESH", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        recreate();
                    }
                });

                View snackbarView = snackbar.getView();

                snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbarBackground));

                snackbar.show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //keyboard disappears when you click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
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
