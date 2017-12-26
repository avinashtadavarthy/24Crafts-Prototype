package com.twenty.four.crafts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.twenty.four.crafts.registration.StartingScreen;

public class ForgotPassword extends AppCompatActivity {

    Button submit;
    RelativeLayout parent;
    int emailFound = 1;
    EditText regEmailId;
    String dialogtext,dialogbuttontext;

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

                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
                builder.setMessage(dialogtext)
                        .setCancelable(false)
                        .setPositiveButton(dialogbuttontext, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                if(emailFound == 0)
                                {
                                    Intent i = new Intent(getApplicationContext(), StartingScreen.class);
                                    startActivity(i);
                                }

                                else
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
