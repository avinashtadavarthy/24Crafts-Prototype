package com.twenty.four.crafts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.View;

import com.twenty.four.crafts.registration.StartingScreen;

public class SubscribePopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_pop_up);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

    }

    public void purchasecoinsIntent(View view)
    {
        Intent intent = new Intent(getApplicationContext(),PurchaseCoins.class);
        startActivity(intent);
        finish();
    }
    public void launchIntent(View view)
    {
        final int[] count = {0};
        /*Intent intent = new Intent(getApplicationContext(),SubscribePopUp2.class);
        startActivity(intent);*/
        AlertDialog.Builder builder = new AlertDialog.Builder(SubscribePopUp.this);
        builder.setMessage("Free Account only gives you access to the DIRECTORY. Producers/Directors will not be able to contact you...")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.cancel();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
