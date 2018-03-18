package com.twenty.four.crafts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.twenty.four.crafts.app_startup.Login;
import com.twenty.four.crafts.registration.StartingScreen;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePassword extends AppCompatActivity {


    TextInputEditText currPass,newPass,newverifypass;
    TextView errorMessage,forgotPass;
    Button changePass;

    SharedPref sharedPref;
    String cPass = "",nPass = "",nvPass = "";
    String text = "";
    String jwtToken = "";
    String sharedPrefPass = "";

    String dialogtext = "Proceeding further will resend a new password to your email and log you out of 24Crafts. Do you want to continue? (Y/N)";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        AndroidNetworking.initialize(getApplicationContext());


        sharedPref = new SharedPref(getApplicationContext());
        currPass = findViewById(R.id.CPassEdittext);
        newPass = findViewById(R.id.NPassEdittext);
        newverifypass = findViewById(R.id.NewVerifyEdittext);


        changePass = findViewById(R.id.changeCurrPass);
        errorMessage = findViewById(R.id.ErrorTextView);

        forgotPass = findViewById(R.id.ForgotPass);


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();

            }
        });


        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPrefPass = getSPData("pword");

                cPass = currPass.getText().toString();
                nPass = newPass.getText().toString();
                nvPass = newverifypass.getText().toString();

                Log.e("Passwords",cPass + " " + nPass + " " + nvPass);
                if(cPass.equals("") || nPass.equals("") || nvPass.equals(""))
                    errorMessage.setText("Empty fields found !");

                else
                {
                    if(!cPass.equals(sharedPrefPass))
                        errorMessage.setText("Current Password Incorrect");

                    else if(!nPass.equals(nvPass))
                        errorMessage.setText("New Passwords don't match");


                    else
                        routeToChangepassword(nPass);
                }


            }
        });

    }

    private void updateEmailVerification() {


        jwtToken = getSPData("jwtToken");

        String newurl = User.getInstance().BASE_URL + "user/update";


        AndroidNetworking.post(newurl)
                .addBodyParameter("emailVerification","true")
                .setPriority(Priority.MEDIUM)
                .addHeaders("authorization",jwtToken)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("emailVerification",response);


                        routeToForgotPassword(getSPData("uname"));
                        // Toast.makeText(ForgotPassword.this, response, Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }


    public void routeToForgotPassword(String emailID)
    {
        text = emailID;

        jwtToken = getSPData("jwtToken");

        String newurl = User.getInstance().BASE_URL + "user/update/reset_password";


        AndroidNetworking.post(newurl)
                .addBodyParameter("email",text)
                .setPriority(Priority.MEDIUM)
                .addHeaders("authorization",jwtToken)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("forgotPassword",response);

                        // Toast.makeText(ForgotPassword.this, response, Toast.LENGTH_SHORT).show();

                        checkForResponseForgotPassword(response);

                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(ChangePassword.this, "Please contact support: support@geass.technology", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void checkForResponseForgotPassword(String response) {

        if(response.equals("successfully updated features."))
        {
            sharedPref.clearAllSharedPrefs(getApplicationContext());

            Intent intent = new Intent(getApplicationContext(), Login.class).putExtra("status", "logout");
            startActivity(intent);
        }


        else if(response.equals("User not found, try again"))
        {

        }

        else
        {

        }
    }


    public void alertDialog()
    {

        /*Proceeding further will resend a new password to your email and log you out of 24Crafts. Do you want to continue? (Y)(N)*/

        final AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(ChangePassword.this,R.style.AlertDialog)).setMessage(dialogtext).
                setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        updateEmailVerification();

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                        finish();
                    }
                })
                .show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setGravity(Gravity.LEFT);
        textView.setTextSize(15);
    }


    private void routeToChangepassword(String newpass) {

        jwtToken = getSPData("jwtToken");

        String newurl = User.getInstance().BASE_URL + "user/update";


        AndroidNetworking.post(newurl)
                .addBodyParameter("password",newpass)
                .setPriority(Priority.MEDIUM)
                .addHeaders("authorization",jwtToken)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("changePassword",response);

                        // Toast.makeText(ForgotPassword.this, response, Toast.LENGTH_SHORT).show();

                        checkForResponse(response);

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    private void checkForResponse(String response) {

        if(response.equals("successfully updated features."))
        {
            storeSPData("pword",nPass);
            Toast.makeText(ChangePassword.this, "Successfully Changed Password", Toast.LENGTH_LONG).show();
            getNewJWT();

        }


        else if(response.equals("No updating required"))
        {
            Toast.makeText(ChangePassword.this, "No changes made", Toast.LENGTH_SHORT).show();
            finish();
        }

        else
            Toast.makeText(ChangePassword.this, "Please contact support: support@geass.technology", Toast.LENGTH_SHORT).show();





    }

    private void getNewJWT() {

        String url = User.getInstance().BASE_URL + "login";
        AndroidNetworking.post(url)
                .addBodyParameter("username", getSPData("uname"))
                .addBodyParameter("password", getSPData("pword"))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        storeSPData("jwtToken",response.optString("token"));
                        Log.e("newJWT",getSPData("jwtToken"));

                        finish();
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.e("error","ERROR");
                    }
                });


    }


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
