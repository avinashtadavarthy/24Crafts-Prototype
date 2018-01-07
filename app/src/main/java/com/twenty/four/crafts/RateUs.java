package com.twenty.four.crafts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.kila.apprater_dialog.lars.AppRater;

public class RateUs extends AppCompatActivity {

    RatingBar rb;
    ImageView rateusImage;
    EditText description;
    Button submit;
    float ratingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       setContentView(R.layout.activity_rate_us);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        /*rb = (RatingBar) findViewById(R.id.ratingbar);
        rateusImage = (ImageView) findViewById(R.id.rateusimage);
        description = (EditText) findViewById(R.id.descriptionEdittext);
        submit = (Button) findViewById(R.id.submitButton);*/


      /*  rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                ratingValue = v;
                Log.d("rateus",ratingValue + "");
            }
        });*/
    }
}
