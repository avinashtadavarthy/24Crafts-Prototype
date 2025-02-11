package com.twenty.four.crafts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class RSSMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_main);


    }

    private void createClickListener(Button button, final int demoType) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDemo(BaseUtils.TYPE_LIST);
            }
        });
    }

    private void startDemo(int demoType) {
        Intent intent = new Intent(this, DemoActivity.class);
        intent.putExtra(DemoActivity.EXTRA_TYPE, demoType);
        startActivity(intent);
    }
}