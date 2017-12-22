package com.twenty.four.crafts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class DailyCheckIn extends AppCompatActivity {

    TextView random1,random2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check_in);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.9));

        random1 = findViewById(R.id.random1);
        random2 = findViewById(R.id.random2);

    }

    public void checkinintent(View view)
    {
        Intent i = new Intent(getApplicationContext(),DailyCheckIn2.class);
        startActivity(i);
        finish();
    }
}
