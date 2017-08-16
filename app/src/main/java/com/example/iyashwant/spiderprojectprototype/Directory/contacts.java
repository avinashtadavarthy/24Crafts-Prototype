package com.example.iyashwant.spiderprojectprototype.Directory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.R;

public class contacts extends AppCompatActivity {
    RecyclerView recycler;
    LinearLayoutManager manager;
    Recycler_adapter adapter;
    String name[]={"name1","name2","name3"};
    String address[]={"address1","address2","address3"};
    String website[]={"website1","website2","website3"};
    String phone[] = {"phoen1","phone2","phone3"};
    String email[] = {"email1","email2","email3"};
    String name1[]={"Rajiv","Rakesh","Ramnath","Jason","Cine corporation"};
    String address1[]={"5/2,South Avenue,Saidapet Chennai","8/2,South Street, Thiruvanmiyur,Chennai","8B Vasanth apts. Postal colony,Guindy,Chennai","Door no 6 2nd floor ,Jacob residency, Gandhi road, Mumbai-22","8B Vasanth apts. Postal colony,Guindy,Chennai"};
    String website1[]={"","","","","www.sinesale.com"};
    String phone1[]={"9882945673","9890021345","8798231795","9967502856","044-24356673/9786534867"};
    String email1[]={"raj23@gmail.com","raksvw23@yahoo.com","rram55@gmail.com","jasonjolly98@hotmail.com","sinesale@gmail.com"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Intent intent = getIntent();
        String occupation = intent.getExtras().getString("craft");

        recycler = (RecyclerView)findViewById(R.id.rv3);
        manager=new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(manager);
        if(occupation.equals("Ad and Corporate Filmmakers")) {
            adapter = new Recycler_adapter(this,name1,phone1,email1,address1,website1,5);
            recycler.setAdapter(adapter);//Add items to the arrays above as and when retrieved from the database
            TextView heading = (TextView) findViewById(R.id.heading);
            heading.setText(occupation);

        }

        TextView heading = (TextView) findViewById(R.id.heading);
        heading.setText(occupation);

    }
}
