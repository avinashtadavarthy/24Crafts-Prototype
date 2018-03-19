package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;

public class ChooseCraftOrClient extends AppCompatActivity {

    String[] whoNcrafts = {
            "Actor","Actress","Child Artist","Singer","Dancer",
            "Side Artist","Assistant Director","Lyric Writer / Lyricist",
            "Dialouge Writer","Script / Screenplay Writers", "Story Board Artist",
            "Choreographer","Director of Photography", "Still Photographer",
            "PRO", "Designer", "Production Manager",
            "Focus Puller", "Vehicle Driver", "Mic Department",
            "Music Director", "Make-up Man", "Hair Dresser",
            "Costumer", "Art Department", "Set Department",
            "Stuntman", "Editor", "Location Manager",
            "Production (Food)", "Dubbing Artist", "Sound Recording Engineer",
            "Sound Mixing Engineer", "Digital Intermediate", "VFX / CG",
            "SFX", "Pet Supplier / Pet Doctor / AWBI Certifications"};


    String[] whoNclients = {
            "Casting Agent","Co-Director","Co-Producer","Director","Director Assistant",
            "Executive Producer","Model Coordinator", "Producer","Production House Manager"};


    private EditText filterText;
    private ListView itemList;
    private ArrayAdapter<String> listAdapter;
    private String category;

    String selectedcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_craft_or_client);

        filterText = (EditText)findViewById(R.id.editText);
        itemList = (ListView)findViewById(R.id.listView);

        category = getIntent().getStringExtra("category");

        if(category.equals("craftsman")) {
            listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, whoNcrafts);
        } else {
            listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, whoNclients);
        }

        itemList.setAdapter(listAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                filterText.setText(listAdapter.getItem(position));

                Intent intent = new Intent();

                selectedcategory = User.getInstance().getTagFromCategory(listAdapter.getItem(position));

                storeSPData("category", selectedcategory);

                intent.putExtra("selectedcategory", selectedcategory);
                setResult(Activity.RESULT_OK, intent);

                finish();

            }
        });

        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ChooseCraftOrClient.this.listAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }




    //shared pref
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

}
