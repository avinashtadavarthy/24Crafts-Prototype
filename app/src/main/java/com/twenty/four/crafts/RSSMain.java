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
        setContentView(R.layout.activity_rssmain);

        final Button firstListDemoButton = findViewById(R.id.list_demo_button);
        final Button firstGridDemoButton = findViewById(R.id.grid_demo_button);

        createClickListener(firstListDemoButton, BaseUtils.TYPE_LIST);
        createClickListener(firstGridDemoButton, BaseUtils.TYPE_GRID);

        final Button secondListDemoButton = findViewById(R.id.list_second_demo_button);
        final Button secondGridDemoButton = findViewById(R.id.grid_second_demo_button);

        createClickListener(secondListDemoButton, BaseUtils.TYPE_SECOND_LIST);
        createClickListener(secondGridDemoButton, BaseUtils.TYPE_SECOND_GRID);
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