package com.twenty.four.crafts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.twenty.four.crafts.app_startup.Login;
import com.twenty.four.crafts.registration.StartingScreen;

public class AccountInfo extends AppCompatActivity {

    TextView nameText,birthdayText,mobiletext, emailText;
    TextView forgotPassword;
    TextView signout;
    Toolbar toolbar;

    String dialogtext,dialogbuttontext;
    int emailFound = 1;

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
        emailText = findViewById(R.id.AccInfoEmailText);
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

                AlertDialog dialog = new AlertDialog.Builder(AccountInfo.this).setMessage(dialogtext).
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
                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setGravity(Gravity.LEFT);
                textView.setTextSize(15);
            }
        });


        nameText.setText("Ragav");
        birthdayText.setText("25/02/1992");
        mobiletext.setText("9876543210");
        emailText.setText("Ragav@gmail.com");
    }


    public void goBack(View view)
    {
        onBackPressed();
    }
}
