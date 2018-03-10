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

                switch(listAdapter.getItem(position)) {
                    //craftsmen
                    case "Actor": selectedcategory = "Actor"; break;
                    case "Actress": selectedcategory = "Actress"; break;
                    case "Child Artist": selectedcategory = "Childartist"; break;
                    case "Singer": selectedcategory = "Singer"; break;
                    case "Dancer": selectedcategory = "Dancer"; break;
                    case "Side Artist": selectedcategory = "Sideartist"; break;
                    case "Assistant Director": selectedcategory = "Assistantdirector"; break;
                    case "Lyric Writer / Lyricist": selectedcategory = "Lyricwriter"; break;
                    case "Dialouge Writer": selectedcategory = "Dialoguewriter"; break;
                    case "Script / Screenplay Writers": selectedcategory = "Scriptwriter"; break;
                    case "Story Board Artist": selectedcategory = "Storyboardartist"; break;
                    case "Choreographer": selectedcategory = "Choreographer"; break;
                    case "Director of Photography": selectedcategory = "Directorofphotography"; break;
                    case "Still Photographer": selectedcategory = "Stillphotographer"; break;
                    case "PRO": selectedcategory = "Pro"; break;
                    case "Designer": selectedcategory = "Designer"; break;
                    case "Production Manager": selectedcategory = "Productionmanager"; break;
                    case "Focus Puller": selectedcategory = "Focuspuller"; break;
                    case "Vehicle Driver": selectedcategory = "Vehicledriver"; break;
                    case "Mic Department": selectedcategory = "Micdepartment"; break;
                    case "Music Director": selectedcategory = "Musicdirector"; break;
                    case "Make-up Man": selectedcategory = "Makeupman"; break;
                    case "Hair Dresser": selectedcategory = "Hairdresser"; break;
                    case "Costumer": selectedcategory = "Costumer"; break;
                    case "Art Department": selectedcategory = "Artdepartment"; break;
                    case "Set Department": selectedcategory = "Setdepartment"; break;
                    case "Stuntman": selectedcategory = "Stuntman"; break;
                    case "Editor": selectedcategory = "Editor"; break;
                    case "Location Manager": selectedcategory = "Locationmanager"; break;
                    case "Production (Food)": selectedcategory = "Productionfood"; break;
                    case "Dubbing Artist": selectedcategory = "Dubbingartist"; break;
                    case "Sound Recording Engineer": selectedcategory = "Soundrecordingengineer"; break;
                    case "Sound Mixing Engineer": selectedcategory = "Soundmixingengineer"; break;
                    case "Digital Intermediate": selectedcategory = "Di"; break;
                    case "VFX / CG": selectedcategory = "Vfx"; break;
                    case "SFX": selectedcategory = "Sfx"; break;
                    case "Pet Supplier / Pet Doctor / AWBI Certifications": selectedcategory = "Petsupplier"; break;

                    //clients
                    case "Casting Agent": selectedcategory = "Castingagent"; break;
                    case "Co-Director": selectedcategory = "Codirector"; break;
                    case "Co-Producer": selectedcategory = "Coproducer"; break;
                    case "Director": selectedcategory = "Director"; break;
                    case "Director Assistant": selectedcategory = "Directorassistant"; break;
                    case "Executive Producer": selectedcategory = "Executiveproducer"; break;
                    case "Model Coordinator": selectedcategory = "Modelcoordinator"; break;
                    case "Producer": selectedcategory = "Producer"; break;
                    case "Production House Manager": selectedcategory = "Productionhousemanager"; break;
                }

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
