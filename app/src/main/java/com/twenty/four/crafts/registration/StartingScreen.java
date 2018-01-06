package com.twenty.four.crafts.registration;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;

public class StartingScreen extends AppCompatActivity {

    ImageView craftsman_reg, client_reg;

    String firstname;
    String lastname;
    String email;
    String gender;
    String imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        storeSPData("languagesspoken_neat", "");

        craftsman_reg = (ImageView) findViewById(R.id.craftsman_reg);
        client_reg = (ImageView) findViewById(R.id.client_reg);

        //receiving data
        Bundle bundle1 = getIntent().getExtras();
        firstname = bundle1.getString("firstname");
        lastname = bundle1.getString("lastname");
        email = bundle1.getString("email");
        gender = bundle1.getString("gender");
        imgurl = bundle1.getString("imgurl");

        craftsman_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storeSPData("isClient", "false");

                Intent i = new Intent(getApplicationContext(),signup.class);
                i.putExtras(transferdata("craftsman"));
                startActivity(i);

            }
        });



        client_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storeSPData("isClient", "true");

                Intent i = new Intent(getApplicationContext(),signup.class);
                i.putExtras(transferdata("client"));
                startActivity(i);

            }
        });

    }

    public Bundle transferdata(String type)
    {
        Bundle bundle = new Bundle();
        bundle.putString("firstname", firstname);
        bundle.putString("lastname", lastname);
        bundle.putString("email", email);
        bundle.putString("gender", gender);
        bundle.putString("imgurl", imgurl);
        bundle.putString("type", type);

        return bundle;
    }


    @Override
    public void onBackPressed() {

        new android.support.v7.app.AlertDialog.Builder(StartingScreen.this)
                .setMessage("Do you want to exit the registration process?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

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
