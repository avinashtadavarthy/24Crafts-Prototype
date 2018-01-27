package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.twenty.four.crafts.CustomAdapterSpinner;
import com.twenty.four.crafts.R;

import org.json.JSONException;
import org.json.JSONObject;

public class signup2 extends AppCompatActivity {

    String[] bodytypeString={"Choose Body Type",
            "Athletic","Average","Petite","Thin","Heavy","Other"};

    String[] haircolorString={"Choose Hair Color",
            "Black", "Brown", "White","Red", "Blonde", "Burgundy", "Ginger", "Other"};

    String[] hairlengthString={"Choose Hair Length",
            "Short", "Medium", "Long", "Bald", "Shaved", "Other"};

    String[] eyecolorString={"Choose Eye Color",
            "Black", "Brown", "Blue", "Amber", "Grey", "Green", "Hazel", "Other"};

    Integer[] imageArraymale = {
            R.drawable.ic_person, R.drawable.veryfair, R.drawable.fair,  R.drawable.medium,  R.drawable.olive,  R.drawable.brown, R.drawable.dark};

    Integer[] imageArrayfemale = {
            R.drawable.ic_person, R.drawable.veryfairf, R.drawable.fairf, R.drawable.mediumf, R.drawable.olivef, R.drawable.brownf, R.drawable.darkf};

    String[] skintoneString = { "Choose Skin Tone", "Very Fair", "Fair", "Medium", "Olive", "Brown", "Dark" };

    String[] facialhairString={"Choose Facial Hair",
            "Beard","Moustache", "Beard & Moustache", "Stubble / Goatie", "None", "Other"};


    String bodyTypeSelector = "null";
    String hairColorSelector = "null";
    String hairLengthSelector = "null";
    String eyeColorSelector = "null";
    String facialHairSelector = "null";
    String skinToneSelector = "null";

    TextView bodytype_floatingtext, haircolor_floatingtext, hairlength_floatingtext, eyecolor_floatingtext, skintone_floatingtext, facialhair_floatingtext;
    Spinner bodytype, haircolor, hairlength, eyecolor, skintone, facialhair;
    LinearLayout facialhairlayout;
    TextInputLayout input_height, input_weight, input_hipsize, input_chestsize, input_waistsize ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        final Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        final String craft = bundle.getString("craft");
        final String type = bundle.getString("type");

        TextView textView =(TextView)findViewById(R.id.welcome);
        textView.setText("Hello, " + name);


        final EditText height = (EditText) findViewById(R.id.height);
        final EditText weight = (EditText) findViewById(R.id.weight);
        final EditText hipsize = (EditText) findViewById(R.id.hip_size);
        final EditText waistsize = (EditText) findViewById(R.id.waist_size);
        final EditText chestsize = (EditText) findViewById(R.id.chest_size);



        bodytype_floatingtext = (TextView) findViewById(R.id.bodytype_floatingtext);
        haircolor_floatingtext = (TextView) findViewById(R.id.haircolor_floatingtext);
        hairlength_floatingtext = (TextView) findViewById(R.id.hairlength_floatingtext);
        eyecolor_floatingtext = (TextView) findViewById(R.id.eyecolor_floatingtext);
        skintone_floatingtext = (TextView) findViewById(R.id.skintone_floatingtext);
        facialhair_floatingtext = (TextView) findViewById(R.id.facialhair_floatingtext);

        input_height = (TextInputLayout) findViewById(R.id.input_height);
        input_weight = (TextInputLayout) findViewById(R.id.input_weight);
        input_hipsize = (TextInputLayout) findViewById(R.id.input_hipsize);
        input_chestsize = (TextInputLayout) findViewById(R.id.input_chestsize);
        input_waistsize = (TextInputLayout) findViewById(R.id.input_waistsize);



        bodytype = (Spinner) findViewById(R.id.body_type);
        haircolor = (Spinner) findViewById(R.id.hair_color);
        hairlength = (Spinner) findViewById(R.id.hair_length);
        eyecolor = (Spinner) findViewById(R.id.eye_color);
        skintone = (Spinner) findViewById(R.id.skin_tone);
        facialhair = (Spinner) findViewById(R.id.facial_hair);



        CustomAdapterSpinner bodytypeAdapter=new CustomAdapterSpinner(getApplicationContext(),bodytypeString);
        bodytype.setAdapter(bodytypeAdapter);
        bodytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 1: bodyTypeSelector = "Athletic"; break;
                    case 2: bodyTypeSelector = "Average"; break;
                    case 3: bodyTypeSelector = "Petite"; break;
                    case 4: bodyTypeSelector = "Thin"; break;
                    case 5: bodyTypeSelector = "Heavy"; break;
                    case 6: bodyTypeSelector = "Other"; break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        CustomAdapterSpinner haircolorAdapter=new CustomAdapterSpinner(getApplicationContext(),haircolorString);
        haircolor.setAdapter(haircolorAdapter);
        haircolor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 1: hairColorSelector = "Black"; break;
                    case 2: hairColorSelector = "Brown"; break;
                    case 3: hairColorSelector = "White"; break;
                    case 4: hairColorSelector = "Red"; break;
                    case 5: hairColorSelector = "Blonde"; break;
                    case 6: hairColorSelector = "Burgundy"; break;
                    case 7: hairColorSelector = "Ginger"; break;
                    case 8: hairColorSelector = "Other"; break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        CustomAdapterSpinner hairlengthAdapter=new CustomAdapterSpinner(getApplicationContext(),hairlengthString);
        hairlength.setAdapter(hairlengthAdapter);
        hairlength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 1: hairLengthSelector = "Short"; break;
                    case 2: hairLengthSelector = "Medium"; break;
                    case 3: hairLengthSelector = "Long"; break;
                    case 4: hairLengthSelector = "Bald"; break;
                    case 5: hairLengthSelector = "Shaved"; break;
                    case 6: hairLengthSelector = "Other"; break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        CustomAdapterSpinner eyecolorAdapter=new CustomAdapterSpinner(getApplicationContext(),eyecolorString);
        eyecolor.setAdapter(eyecolorAdapter);
        eyecolor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 1: eyeColorSelector = "Black"; break;
                    case 2: eyeColorSelector = "Brown"; break;
                    case 3: eyeColorSelector = "Blue"; break;
                    case 4: eyeColorSelector = "Amber"; break;
                    case 5: eyeColorSelector = "Grey"; break;
                    case 6: eyeColorSelector = "Green"; break;
                    case 7: eyeColorSelector = "Hazel"; break;
                    case 8: eyeColorSelector = "Other"; break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        if(getSPData("gender").equals("Male")){
            SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.custom, skintoneString, imageArraymale);
            skintone.setAdapter(adapter);
        } else if(getSPData("gender").equals("Female") || getSPData("gender").equals("Other")) {
            SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.custom, skintoneString, imageArrayfemale);
            skintone.setAdapter(adapter);
        }
        skintone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 1: skinToneSelector = "Veryfair"; break;
                    case 2: skinToneSelector = "Fair"; break;
                    case 3: skinToneSelector = "Medium"; break;
                    case 4: skinToneSelector = "Olive"; break;
                    case 5: skinToneSelector = "Brown"; break;
                    case 6: skinToneSelector = "Dark"; break;
                    case 7: skinToneSelector = "Other"; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        CustomAdapterSpinner facialhairAdapter=new CustomAdapterSpinner(getApplicationContext(),facialhairString);
        facialhair.setAdapter(facialhairAdapter);
        facialhair.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 1: facialHairSelector = "Beard"; break;
                    case 2: facialHairSelector = "Moustache"; break;
                    case 3: facialHairSelector = "Beard & moustache"; break;
                    case 4: facialHairSelector = "Stubble / goatie"; break;
                    case 5: facialHairSelector = "None"; break;
                    case 6: facialHairSelector = "Other";  break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!bodyTypeSelector.equals("null")) storeSPData("bodyType", bodyTypeSelector);

                if(!hairColorSelector.equals("null")) storeSPData("hairColor", hairColorSelector);

                if(!hairLengthSelector.equals("null")) storeSPData("hairLength", hairLengthSelector);

                if(!eyeColorSelector.equals("null")) storeSPData("eyeColor", eyeColorSelector);

                if(!facialHairSelector.equals("null")) storeSPData("facialHair", facialHairSelector);

                if(!skinToneSelector.equals("null")) storeSPData("skinTone", skinToneSelector);

                if(!facialHairSelector.equals("null")) storeSPData("facialHair", facialHairSelector);


                if(!height.getText().toString().equals("")) {
                    storeSPData("height", height.getText().toString());
                }

                if(!weight.getText().toString().equals("")) {
                    storeSPData("weight", weight.getText().toString());
                }

                if(!hipsize.getText().toString().equals("")) {
                    storeSPData("hipsize", hipsize.getText().toString());
                }

                if(!chestsize.getText().toString().equals("")) {
                    storeSPData("chestSize", chestsize.getText().toString());
                }

                if(!waistsize.getText().toString().equals("")) {
                    storeSPData("waistSize", waistsize.getText().toString());
                }


                Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("craft", craft);
                bundle.putString("type", type);
                goToNextActivity.putExtras(bundle);
                startActivity(goToNextActivity);

            }

        });





        switch(craft)
        {
            case "Child Artist":

                facialhairlayout = (LinearLayout) findViewById(R.id.facialhairlayout);
                facialhairlayout.setVisibility(View.GONE);
                input_hipsize.setVisibility(View.GONE);
                input_chestsize.setVisibility(View.GONE);
                input_waistsize.setVisibility(View.GONE);
                hipsize.setVisibility(View.GONE);
                waistsize.setVisibility(View.GONE);
                chestsize.setVisibility(View.GONE);

                break;

           }

    }

    //keyboard disappears when you click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }





    //Shared Preferences
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(String key) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }

}
