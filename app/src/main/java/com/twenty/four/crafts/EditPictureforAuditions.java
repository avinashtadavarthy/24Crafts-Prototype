package com.twenty.four.crafts;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.steelkiwi.cropiwa.CropIwaView;
import com.steelkiwi.cropiwa.config.CropIwaSaveConfig;

public class EditPictureforAuditions extends AppCompatActivity {

    Bitmap rawimage;
    private CropIwaView cropView;
    Button cropandsave, cancelthecrop;

    String rawimageuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_picture_aud);

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

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, System.currentTimeMillis());
                values.put(MediaStore.Images.Media.DISPLAY_NAME, System.currentTimeMillis());
                values.put(MediaStore.Images.Media.DESCRIPTION, "_profile.jpg");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                // Add the date meta data to ensure the image is added at the front of the gallery
                values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
                values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());

                Uri url = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


                cropView.crop(new CropIwaSaveConfig.Builder(url)
                        .setCompressFormat(Bitmap.CompressFormat.PNG)
                        .setQuality(100)
                        .build());

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
