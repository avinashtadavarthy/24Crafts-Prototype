package com.twenty.four.crafts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class PurchaseCoins extends AppCompatActivity {

    CardView twenty_coins,fifty_coins,onefifty_coins,threefifty_coins;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_coins);

        getSupportActionBar().setTitle("Purchase Coins");

        twenty_coins = findViewById(R.id.card_view_1);
        fifty_coins = findViewById(R.id.card_view_2);
        onefifty_coins = findViewById(R.id.card_view_3);
        threefifty_coins = findViewById(R.id.card_view_4);

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
}
