package com.twenty.four.crafts.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.twenty.four.crafts.R;

public class StartingScreen extends AppCompatActivity {

    ImageView craftsman_reg, client_reg;

    String firstname;
    String lastname;
    String email;
    String gender;
    String imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        craftsman_reg = (ImageView) findViewById(R.id.craftsman_reg);
        client_reg = (ImageView) findViewById(R.id.client_reg);

        //receiving data
        Bundle bundle1 = getIntent().getExtras();
        firstname = bundle1.getString("firstname");
        lastname = bundle1.getString("lastname");
        email = bundle1.getString("email");
        gender = bundle1.getString("gender");
        imgurl = bundle1.getString("imgurl");

        craftsman_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),signup.class).putExtra("type", "craftsman");
                i.putExtras(transferdata());
                startActivity(i);

            }
        });

        client_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),signupclient.class).putExtra("type", "client");
                i.putExtras(transferdata());
                startActivity(i);

            }
        });

    }

    public Bundle transferdata()
    {
        Bundle bundle = new Bundle();
        bundle.putString("firstname", firstname);
        bundle.putString("lastname", lastname);
        bundle.putString("email", email);
        bundle.putString("gender", gender);
        bundle.putString("imgurl", imgurl);

        return bundle;
    }

}
