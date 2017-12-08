package com.example.iyashwant.spiderprojectprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountInfo extends AppCompatActivity {

    TextView nameText,birthdayText,mobiletext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        getSupportActionBar().setTitle("Account Info");

        nameText = findViewById(R.id.AccInfoNameText);
        birthdayText = findViewById(R.id.AccInfoBirthdayText);
        mobiletext = findViewById(R.id.AccInfoMobileText);


        nameText.setText("Ragav");
        birthdayText.setText("25/02/1992");
        mobiletext.setText("9876543210");
    }
}
