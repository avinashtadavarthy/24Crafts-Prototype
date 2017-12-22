package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.registration.StartingScreen;

public class ForgotPasswordPopUp extends AppCompatActivity {

    TextView forgotPasswordPopUpTextView;
    Button forgotPasswordPopUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_pop_up);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));


        forgotPasswordPopUpTextView = findViewById(R.id.forgot_password_popup_textview);
        forgotPasswordPopUpButton = findViewById(R.id.forgot_password_popup_button);


    }
}
