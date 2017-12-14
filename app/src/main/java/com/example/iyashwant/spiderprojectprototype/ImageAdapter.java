package com.example.iyashwant.spiderprojectprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by rakesh on 25/11/17.
 */

public class ImageAdapter extends PagerAdapter {

    Context context;


    private int[] GalImages = new int[] {


            R.drawable.audition_sample,  //Here first,second,third... are the name of the jpeg files placed in drawable folder
            R.drawable.taigamod1,
            R.drawable.taigamod2,
            R.drawable.taigamod3,
            R.drawable.taigamod4


    };

    ImageAdapter(Context context){

        this.context=context;

    }

    @Override

    public int getCount() {

        return GalImages.length;

    }


    @Override

    public boolean isViewFromObject(View view, Object object) {

        return view == ((ImageView) object);

    }


    @Override

    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(GalImages[position]);
        ((ViewPager) container).addView(imageView, 0);

        return imageView;

    }


    @Override

    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ImageView) object);

    }

}