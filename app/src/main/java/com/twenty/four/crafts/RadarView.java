package com.twenty.four.crafts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RadarView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_view);
        initView();
    }


    private void initView(){
        RadarViewClass mRadarView = (RadarViewClass) findViewById(R.id.radar_view);
        mRadarView.setSearching(true);

        for(int i=0;i<15;i++)
        mRadarView.addPoint();

    }
}
