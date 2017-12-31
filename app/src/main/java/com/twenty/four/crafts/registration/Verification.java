package com.twenty.four.crafts.registration;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.twenty.four.crafts.ProfileView;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;

public class Verification extends AppCompatActivity {

    ImageView fb,insta,twitter,phone,google;
    TextView fb_text,insta_text,google_text,twitter_text,phone_text;
    Button verification_done, verification_skip;

    LinearLayout phone_layout, fb_layout, google_layout, insta_layout, twitter_layout;

    String fromhere = "Hello";
    String fromwhom = "hey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* * * */
        /* */ FacebookSdk.sdkInitialize(getApplicationContext());
        /* * * */
        setContentView(R.layout.activity_verification);

        phone_layout = (LinearLayout) findViewById(R.id.phone_layout);
        fb_layout = (LinearLayout) findViewById(R.id.fb_layout);
        google_layout = (LinearLayout) findViewById(R.id.google_layout);
        insta_layout = (LinearLayout) findViewById(R.id.insta_layout);
        twitter_layout = (LinearLayout) findViewById(R.id.twitter_layout);

        verification_done = (Button) findViewById(R.id.verification_done);
        verification_skip = (Button) findViewById(R.id.verification_skip);

        fromhere = getIntent().getStringExtra("fromhere");
        fromwhom = getIntent().getStringExtra("fromwhom");

        if(fromhere.equals("PhoneVerified")) {
            verification_done.setVisibility(View.GONE);
        } else if(fromhere.equals("FromSettings")) {
            verification_skip.setVisibility(View.GONE);
        }

        fb = (ImageView)findViewById(R.id.fb);
        insta=(ImageView) findViewById(R.id.insta);
        twitter = (ImageView) findViewById(R.id.twitter);
        phone = (ImageView) findViewById(R.id.phone);
        google = (ImageView) findViewById(R.id.google);



        fb_text = (TextView)findViewById(R.id.fb_text_veri);
        insta_text = (TextView)findViewById(R.id.insta_text_veri);
        twitter_text = (TextView)findViewById(R.id.twitter_text_veri);
        phone_text = (TextView)findViewById(R.id.phone_text_veri);
        google_text = (TextView)findViewById(R.id.google_text_veri);


        if(User.getInstance().facebook_verified) {
            fb.setImageResource(R.drawable.facebook);
            fb_text.setTextColor(Color.parseColor("#ff99cc00")); //#82ad00
            fb_text.setText("Verified!");
        }

        if(User.getInstance().google_verified) {
            google.setImageResource(R.drawable.googleg_standard_color_18);
            google_text.setTextColor(Color.parseColor("#ff99cc00"));
            google_text.setText("Verified!");
        }

        if(User.getInstance().instagram_verified) {
            insta.setImageResource(R.drawable.instagram_icon);
            insta_text.setTextColor(Color.parseColor("#ff99cc00"));
            insta_text.setText("Verified!");
        }

        if(User.getInstance().twitter_verified) {
            twitter.setImageResource(R.drawable.twitter);
            twitter_text.setTextColor(Color.parseColor("#ff99cc00"));
            twitter_text.setText("Verified!");
        }


        phone_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Verification.this)
                        .setTitle("Phone Verification Done!")
                        .setMessage("Do you want to change the number?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(Verification.this, "Have to connect account kit here", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });


        fb_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(User.getInstance().facebook_verified) {

                    new AlertDialog.Builder(Verification.this)
                            .setTitle("Facebook Verification Done!")
                            .setMessage("Do you want to change the account?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(Verification.this, "Log out account and enable change user", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                } else {

                    Toast.makeText(Verification.this, "Perform fb login to connect account", Toast.LENGTH_SHORT).show();

                }

            }
        });


        google_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(User.getInstance().google_verified) {

                    new AlertDialog.Builder(Verification.this)
                            .setTitle("Google Verification Done!")
                            .setMessage("Do you want to change the account?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(Verification.this, "Log out account and enable change user", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                } else {

                    Toast.makeText(Verification.this, "Perform google login to connect account", Toast.LENGTH_SHORT).show();

                }

            }
        });


        insta_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(User.getInstance().instagram_verified) {

                    new AlertDialog.Builder(Verification.this)
                            .setTitle("Instagram Verification Done!")
                            .setMessage("Do you want to change the account?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(Verification.this, "Log out account and enable change user", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                } else {

                    Toast.makeText(Verification.this, "Perform instagram login to connect account", Toast.LENGTH_SHORT).show();

                }

            }
        });


        twitter_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(User.getInstance().twitter_verified) {

                    new AlertDialog.Builder(Verification.this)
                            .setTitle("Twitter Verification Done!")
                            .setMessage("Do you want to change the account?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(Verification.this, "Log out account and enable change user", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                } else {

                    Toast.makeText(Verification.this, "Perform twitter login to connect account", Toast.LENGTH_SHORT).show();

                }

            }
        });


        verification_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        verification_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), ProfileView.class)
                        .putExtra("thisistogetback", "getback")
                        .putExtra("fromwhom", fromwhom);
                startActivity(next);
            }
        });

    }
}

























/*fb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                fb.setImageResource(R.drawable.facebook);
                fb_text.setTextColor(Color.parseColor("#ff99cc00"));
                fb_text.setText("Verified!");
                return false;

            }
        });

        insta.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                insta.setImageResource(R.drawable.insta);
                insta_text.setTextColor(Color.parseColor("#ff99cc00"));
                insta_text.setText("Verified!");
                return false;
            }
        });


        twitter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                twitter.setImageResource(R.drawable.twitter);
                twitter_text.setTextColor(Color.parseColor("#ff99cc00"));
                twitter_text.setText("Verified!");
                return false;
            }

        });

        google.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                google.setImageResource(R.drawable.google);
                google_text.setTextColor(Color.parseColor("#ff99cc00"));
                google_text.setText("verified!");

                return false;
            }

        });
*/

