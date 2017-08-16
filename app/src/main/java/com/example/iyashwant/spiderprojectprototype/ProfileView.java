package com.example.iyashwant.spiderprojectprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ProfileView extends AppCompatActivity {

    Target aTarget, bTarget, cTarget, dTarget, eTarget, fTarget, gTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        final ImageView mainImg = (ImageView) findViewById(R.id.MainImage);
        final ImageView sideImg1 = (ImageView) findViewById(R.id.SideImage1);
        final ImageView sideImg2 = (ImageView) findViewById(R.id.SideImage2);
        final ImageView bottomImg1 = (ImageView) findViewById(R.id.BottomImage1);
        final ImageView bottomImg2 = (ImageView) findViewById(R.id.BottomImage2);
        final ImageView bottomImg3 = (ImageView) findViewById(R.id.BottomImage3);
        final ImageView bottomImg4 = (ImageView) findViewById(R.id.BottomImage4);

        aTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap hello = getRoundedCornerBitmap(bitmap,150);
                mainImg.setImageBitmap(hello);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        bTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap hello = getRoundedCornerBitmap(bitmap,150);
                sideImg1.setImageBitmap(hello);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        cTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap hello = getRoundedCornerBitmap(bitmap,150);
                sideImg2.setImageBitmap(hello);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        dTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap hello = getRoundedCornerBitmap(bitmap,150);
                bottomImg1.setImageBitmap(hello);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        eTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap hello = getRoundedCornerBitmap(bitmap,150);
                bottomImg2.setImageBitmap(hello);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        fTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap hello = getRoundedCornerBitmap(bitmap,150);
                bottomImg3.setImageBitmap(hello);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        gTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap hello = getRoundedCornerBitmap(bitmap,150);
                bottomImg4.setImageBitmap(hello);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };


        Picasso.with(this).load("http://i.imgur.com/DoA1JJF.jpg").into(aTarget);
        Picasso.with(this).load("http://i.imgur.com/CUSEWr8.jpg").into(bTarget);
        Picasso.with(this).load("http://i.imgur.com/9aOoyS1.jpg").into(cTarget);
        Picasso.with(this).load("http://i.imgur.com/P7aM0RD.jpg").into(dTarget);
        Picasso.with(this).load("http://i.imgur.com/KNiOOEm.jpg").into(eTarget);
        Picasso.with(this).load("http://i.imgur.com/qpccd8j.jpg").into(fTarget);
        Picasso.with(this).load("http://i.imgur.com/1mVoFZS.jpg").into(gTarget);

        //Bitmap bm = ((BitmapDrawable)mainImg.getDrawable()).getBitmap();
        //getRoundedCornerBitmap(bm, 5);
        //mainImg.setImageBitmap(bm);


    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
