package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.steelkiwi.cropiwa.CropIwaView;
import com.steelkiwi.cropiwa.config.CropIwaSaveConfig;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;
import com.twenty.four.crafts.registration.filter.FilterActivity;
import com.twenty.four.crafts.registration.filter.utils.BitmapUtils;

public class EditPictureActivity extends AppCompatActivity {

    final static int REQUEST_IMAGE_FILTER = 2121;

    Bitmap rawimage;
    private CropIwaView cropView;
    Button cropandsave, cancelthecrop;

    String rawimageuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_picture);

       getSupportActionBar().setTitle("Crop The Image");

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

                //Uri.parse("file:///sdcard/pic" + User.getInstance().ct + ".png")

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
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .setQuality(100)
                        .build());

                Intent i = new Intent(EditPictureActivity.this, FilterActivity.class);
                startActivityForResult(i,REQUEST_IMAGE_FILTER);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_FILTER) {
            if(resultCode == Activity.RESULT_OK) {

                String path = data.getStringExtra("saved_path");

                //return to signup activity
                Intent intent = new Intent();
                intent.putExtra("saved_path", path);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        }

    }
}
