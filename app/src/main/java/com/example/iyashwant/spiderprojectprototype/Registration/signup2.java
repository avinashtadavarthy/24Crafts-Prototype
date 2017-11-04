package com.example.iyashwant.spiderprojectprototype.Registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iyashwant.spiderprojectprototype.CustomAdapterSpinner;
import com.example.iyashwant.spiderprojectprototype.R;

public class signup2 extends AppCompatActivity {

    String[] bodytypeString={"Choose Body Type",
            "Athletic","Average","Petite","Thin","Heavy","Other"};

    String[] haircolorString={"Choose Hair Color",
            "Black", "Brown", "White","Red", "Blonde", "Burgundy", "Ginger", "Other"};

    String[] hairlengthString={"Choose Hair Length",
            "Short", "Medium", "Long", "Bald", "Shaved", "Other"};

    String[] eyecolorString={"Choose Eye Color",
            "Black", "Brown", "Blue", "Amber", "Grey", "Green", "Hazel", "Other"};

    String[] skintoneString={"Choose Skin Tone",
            "Very Fair", "Fair", "Medium", "Olive", "Brown", "Dark", "Other"};

    String[] facialhairString={"Choose Facial Hair",
            "Beard","Moustache", "Beard & Moustache", "Stubble", "None", "Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        final Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        final String craft = bundle.getString("craft");

        TextView textView =(TextView)findViewById(R.id.welcome);
        textView.setText("Welcome, "+name);

        final TextView other_bodytype = (TextView) findViewById(R.id.other_bodytype);
        other_bodytype.setVisibility(View.GONE);
        final TextView other_haircolor = (TextView) findViewById(R.id.other_haircolor);
        other_haircolor.setVisibility(View.GONE);
        final TextView other_hairlength = (TextView) findViewById(R.id.other_hairlength);
        other_hairlength.setVisibility(View.GONE);
        final TextView other_eyecolor = (TextView) findViewById(R.id.other_eyecolor);
        other_eyecolor.setVisibility(View.GONE);
        final TextView other_skintone = (TextView) findViewById(R.id.other_skintone);
        other_skintone.setVisibility(View.GONE);
        final TextView other_facialhair = (TextView) findViewById(R.id.other_facialhair);
        other_facialhair.setVisibility(View.GONE);

        final TextView hipsize = (TextView) findViewById(R.id.hip_size);
        final TextView waistsize = (TextView) findViewById(R.id.waist_size);
        final TextView chestsize = (TextView) findViewById(R.id.chest_size);


        final Spinner bodytype = (Spinner) findViewById(R.id.body_type);
        CustomAdapterSpinner bodytypeAdapter=new CustomAdapterSpinner(getApplicationContext(),bodytypeString);
        bodytype.setAdapter(bodytypeAdapter);
        bodytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(bodytypeString[position].equals("Other")) {
                    bodytype.setBackgroundResource(R.drawable.box3);
                    other_bodytype.setVisibility(View.VISIBLE);
                }
                else {
                    bodytype.setBackgroundResource(R.drawable.box);
                    other_bodytype.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        final Spinner haircolor = (Spinner) findViewById(R.id.hair_color);
        CustomAdapterSpinner haircolorAdapter=new CustomAdapterSpinner(getApplicationContext(),haircolorString);
        haircolor.setAdapter(haircolorAdapter);
        haircolor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(haircolorString[position].equals("Other")) {
                    haircolor.setBackgroundResource(R.drawable.box3);
                    other_haircolor.setVisibility(View.VISIBLE);
                }
                else {
                    haircolor.setBackgroundResource(R.drawable.box);
                    other_haircolor.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final Spinner hairlength = (Spinner) findViewById(R.id.hair_length);
        CustomAdapterSpinner hairlengthAdapter=new CustomAdapterSpinner(getApplicationContext(),hairlengthString);
        hairlength.setAdapter(hairlengthAdapter);
        hairlength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(hairlengthString[position].equals("Other")) {
                    hairlength.setBackgroundResource(R.drawable.box3);
                    other_hairlength.setVisibility(View.VISIBLE);
                }
                else {
                    hairlength.setBackgroundResource(R.drawable.box);
                    other_hairlength.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final Spinner eyecolor = (Spinner) findViewById(R.id.eye_color);
        CustomAdapterSpinner eyecolorAdapter=new CustomAdapterSpinner(getApplicationContext(),eyecolorString);
        eyecolor.setAdapter(eyecolorAdapter);
        eyecolor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(eyecolorString[position].equals("Other")) {
                    eyecolor.setBackgroundResource(R.drawable.box3);
                    other_eyecolor.setVisibility(View.VISIBLE);
                }
                else {
                    eyecolor.setBackgroundResource(R.drawable.box);
                    other_eyecolor.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final Spinner skintone = (Spinner) findViewById(R.id.skin_tone);
        CustomAdapterSpinner skintoneAdapter=new CustomAdapterSpinner(getApplicationContext(),skintoneString);
        skintone.setAdapter(skintoneAdapter);
        skintone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(skintoneString[position].equals("Other"))
                    other_skintone.setVisibility(View.VISIBLE);
                else
                    other_skintone.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final Spinner facialhair = (Spinner) findViewById(R.id.facial_hair);
        CustomAdapterSpinner facialhairAdapter=new CustomAdapterSpinner(getApplicationContext(),facialhairString);
        facialhair.setAdapter(facialhairAdapter);
        facialhair.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(facialhairString[position].equals("Other"))
                    other_facialhair.setVisibility(View.VISIBLE);
                else
                    other_facialhair.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("craft",craft);
                goToNextActivity.putExtras(bundle);
                startActivity(goToNextActivity);
            }
        });





        switch(craft)
        {
            case "Child Artist":
                bodytype.setVisibility(View.GONE);
                facialhair.setVisibility(View.GONE);
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

}
