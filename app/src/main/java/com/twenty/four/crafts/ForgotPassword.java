package com.twenty.four.crafts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.twenty.four.crafts.registration.StartingScreen;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword extends AppCompatActivity {

    Button submit;
    RelativeLayout parent;
    int emailFound = 1;
    EditText regEmailId;
    String emailID = "";
    String dialogtext = "Please check your email for further instructions";
    String jwtToken = "";
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        submit = findViewById(R.id.forgot_password_button);
        parent = findViewById(R.id.forgotPasswordParentLayout);
        regEmailId = findViewById(R.id.forgot_password_edittext);
        regEmailId.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        AnimationDrawable animationDrawable = (AnimationDrawable) parent.getBackground();

        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);



        animationDrawable.start();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent i = new Intent(getApplicationContext(),ForgotPasswordPopUp.class);
                //startActivity(i);

                emailID = regEmailId.getText().toString();

                if(emailID.equals(""))
                    Toast.makeText(ForgotPassword.this, "Enter a valid email ID", Toast.LENGTH_SHORT).show();

                else
                    routeToForgotPassword(emailID);
            }
        });
    }


    public void alertDialog()
    {
        final AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(ForgotPassword.this,R.style.AlertDialog)).setMessage(dialogtext).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.cancel();
                    }
                })
                .show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setGravity(Gravity.LEFT);
        textView.setTextSize(15);

    }

    private void routeToForgotPassword(final String emailID) {

        text = emailID;

        jwtToken = getSPData("jwtToken");

        String newurl = User.getInstance().BASE_URL + "user/update/reset_password";

        StringRequest getRequest = new StringRequest(Request.Method.POST, newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("forgotPassword",response);

               // Toast.makeText(ForgotPassword.this, response, Toast.LENGTH_SHORT).show();

                checkForResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("email", text);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(getRequest);




    }

    private void checkForResponse(String response) {

        if(response.equals("successfully updated features."))
            alertDialog();


        else
        {
            Toast.makeText(ForgotPassword.this, "Email not found. Kindly Register", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ForgotPassword.this, StartingScreen.class);
            startActivity(intent);
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


