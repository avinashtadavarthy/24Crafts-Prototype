package com.twenty.four.crafts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PurchaseCoins extends AppCompatActivity {

    CardView twenty_coins,fifty_coins,onefifty_coins,threefifty_coins;

    TextView usersname, craft, coincount;

    JSONObject jsonObject;

    RelativeLayout mainLayout;

    String dialogtextverifyemail = "Please verify your email to continue using the app";

    String userdatamain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_coins);

        getSupportActionBar().setTitle("Purchase Coins");

        mainLayout = findViewById(R.id.mainPurchaseCoins);
        twenty_coins = findViewById(R.id.card_view_1);
        fifty_coins = findViewById(R.id.card_view_2);
        onefifty_coins = findViewById(R.id.card_view_3);
        threefifty_coins = findViewById(R.id.card_view_4);

        usersname = findViewById(R.id.usersname);
        craft = findViewById(R.id.craft);
        coincount = findViewById(R.id.coincount);

        try {
            jsonObject = new JSONObject(getSPData("userdatamain"));

            usersname.setText(jsonObject.optString("name"));
            craft.setText(jsonObject.optString("category"));
            coincount.setText(jsonObject.optString("coinCount"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        twenty_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });


        fifty_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });


        onefifty_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });


        threefifty_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();




        if(jsonObject.optString("emailVerification").equals("false"))
        {
            Snackbar snackbar = Snackbar.make(mainLayout,"Unverified Email", Snackbar.LENGTH_INDEFINITE);

            snackbar.setAction("REFRESH", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    recreate();
                }
            });

            View snackbarView = snackbar.getView();

            snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbarBackground));

            snackbar.show();
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
