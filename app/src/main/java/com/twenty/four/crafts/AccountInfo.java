package com.twenty.four.crafts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.twenty.four.crafts.app_startup.Login;
import com.twenty.four.crafts.registration.StartingScreen;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountInfo extends AppCompatActivity {

    TextView nameText,birthdayText,mobiletext, emailText;
    TextView forgotPassword;
    TextView signout;
    Toolbar toolbar;

    String response = null;
    JSONObject object = null;



    String dialogtext,dialogbuttontext;
    int emailFound = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);



        toolbar = findViewById(R.id.toolbar);

        response = getSPData("userdatamain");

        try {
            object = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Account Info");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = findViewById(R.id.AccInfoNameText);
        nameText.setText(object.optString("name"));

        birthdayText = findViewById(R.id.AccInfoBirthdayText);
        birthdayText.setText(getDateAsString(object.optString("dob").substring(0,10)));

        mobiletext = findViewById(R.id.AccInfoMobileText);
        mobiletext.setText(object.optString("mobileNumber"));

        emailText = findViewById(R.id.AccInfoEmailText);
        emailText.setText(object.optString("email"));

        forgotPassword = findViewById(R.id.ForgotPassButton);
        signout = findViewById(R.id.SignOutButton);







        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: if user has logged in via social login, do the respective signout

                /*//For Facebook
                add this before onCreate FacebookSdk.sdkInitialize(getApplicationContext());
                LoginManager.getInstance().logOut();*/

                /*//For Google
                private void signOut() {
                    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            //do something
                        }
                    });
                }*/


                AlertDialog.Builder a_builder = new AlertDialog.Builder(new ContextThemeWrapper(AccountInfo.this, R.style.AlertDialog));
                a_builder.setMessage("Do You Want to Sign Out of 24 Crafts?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                storeSPData("uname", "");
                                storeSPData("pword", "");
                                storeSPData("userdatamain", "");
                                storeSPData("jwtToken","");

                                Intent intent = new Intent(getApplicationContext(), Login.class).putExtra("status", "logout");
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = a_builder.create();
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                alert.show();
            }
        });



        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(intent);*/


                if(emailFound == 0)
                {
                    dialogtext = getString(R.string.unreg_email_text);
                    dialogbuttontext = "REGISTER";
                }

                else
                {
                    dialogtext = getString(R.string.reg_email_text);
                    dialogbuttontext = "OK";
                }

                AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(AccountInfo.this, R.style.AlertDialog)).setMessage(dialogtext).
                        setPositiveButton(dialogbuttontext, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(emailFound == 0)
                                {
                                    Intent intent = new Intent(AccountInfo.this, StartingScreen.class);
                                    startActivity(intent);
                                }

                                else
                                    dialogInterface.cancel();
                            }
                        })
                        .show();
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setGravity(Gravity.LEFT);
                textView.setTextSize(15);
            }
        });

    }

    private String getDateAsString(String dob) {
        String day, month, year, dateAsString;

        year = dob.substring(0,4);
        month = dob.substring(5,7);
        day = dob.substring(8,10);

        switch(month) {
            case "01": month = "January"; break;
            case "02": month = "February"; break;
            case "03": month = "March"; break;
            case "04": month = "April"; break;
            case "05": month = "May"; break;
            case "06": month = "June"; break;
            case "07": month = "July"; break;
            case "08": month = "August"; break;
            case "09": month = "September"; break;
            case "10": month = "October"; break;
            case "11": month = "November"; break;
            case "12": month = "December"; break;
        }

        dateAsString = day + " " + month + ", " + year;

        return dateAsString;
    }


    public void goBack(View view)
    {
        onBackPressed();
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
