package com.example.iyashwant.spiderprojectprototype;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.app_startup.Login;

public class AccountInfo extends AppCompatActivity {

    TextView nameText,birthdayText,mobiletext;
    TextView forgotPassword;
    TextView signout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);



        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Account Info");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = findViewById(R.id.AccInfoNameText);
        birthdayText = findViewById(R.id.AccInfoBirthdayText);
        mobiletext = findViewById(R.id.AccInfoMobileText);
        forgotPassword = findViewById(R.id.ForgotPassButton);
        signout = findViewById(R.id.SignOutButton);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(AccountInfo.this);
                a_builder.setMessage("Do You Want to Sign Out of 24 Crafts?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
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
                alert.show();
            }
        });



        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(intent);
            }
        });


        nameText.setText("Ragav");
        birthdayText.setText("25/02/1992");
        mobiletext.setText("9876543210");
    }


    public void goBack(View view)
    {
        onBackPressed();
    }
}
