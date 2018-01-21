package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.steelkiwi.cropiwa.AspectRatio;
import com.steelkiwi.cropiwa.CropIwaView;
import com.steelkiwi.cropiwa.config.CropIwaSaveConfig;
import com.steelkiwi.cropiwa.image.CropIwaResultReceiver;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditPictureActivity extends AppCompatActivity {

    Bitmap rawimage;
    private CropIwaView cropView;
    Button cropandsave, cancelthecrop;

    String rawimageuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_picture);

        rawimageuri = getIntent().getStringExtra("RawImageUri");

        Toast.makeText(this, rawimageuri, Toast.LENGTH_SHORT).show();

        cropView = (CropIwaView) findViewById(R.id.crop_view);
        cropView.setImageUri(Uri.parse(rawimageuri));


        cropandsave = (Button) findViewById(R.id.cropandsave);
        cancelthecrop = (Button) findViewById(R.id.cancelthecrop);

        cropandsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //file:///sdcard/Video0006.mp4

                cropView.crop(new CropIwaSaveConfig.Builder(Uri.parse("file:///sdcard/pic" + User.getInstance().ct + ".png"))
                        .setCompressFormat(Bitmap.CompressFormat.PNG)
                        .setQuality(100)
                        .build());

                User.getInstance().ct++;

                finish();

            }
        });

        cancelthecrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
