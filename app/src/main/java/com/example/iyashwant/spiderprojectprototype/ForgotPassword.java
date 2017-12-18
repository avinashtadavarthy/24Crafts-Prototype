package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ForgotPassword extends AppCompatActivity {

    Button submit;
    RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        submit = findViewById(R.id.forgot_password_button);
        parent = findViewById(R.id.forgotPasswordParentLayout);

        AnimationDrawable animationDrawable = (AnimationDrawable) parent.getBackground();

        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);



        animationDrawable.start();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ForgotPasswordPopUp.class);
                startActivity(i);
                finish();
            }
        });
    }
}
