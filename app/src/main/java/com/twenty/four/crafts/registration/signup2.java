package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.twenty.four.crafts.CustomAdapterSpinner;
import com.twenty.four.crafts.R;

import org.json.JSONException;
import org.json.JSONObject;

public class signup2 extends AppCompatActivity {

    Integer[] imageArraymale = {
            R.drawable.ic_person, R.drawable.veryfair, R.drawable.fair,  R.drawable.medium,  R.drawable.olive,  R.drawable.brown, R.drawable.dark};

    Integer[] imageArrayfemale = {
            R.drawable.ic_person, R.drawable.veryfairf, R.drawable.fairf, R.drawable.mediumf, R.drawable.olivef, R.drawable.brownf, R.drawable.darkf};

    String[] skintoneString = { "Choose Skin Tone", "Very Fair", "Fair", "Medium", "Olive", "Brown", "Dark" };

    String[] facialhairString={"Choose Facial Hair",
            "Beard","Moustache", "Beard & Moustache", "Stubble / Goatie", "None", "Other"};


    String skinToneSelector = "null";

    LinearLayout facialhairlayout;

    TextInputLayout input_bodytype, input_haircolor, input_hairlength, input_eyecolor, input_skintone, input_facialhair, input_height, input_weight, input_hipsize, input_chestsize, input_waistsize;
    EditText bodytype, haircolor, hairlength, eyecolor, skintone, facialhair, height, weight, hipsize, chestsize, waistsize;

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


        bodytype = (EditText) findViewById(R.id.bodytype);
        haircolor = (EditText) findViewById(R.id.haircolor);
        hairlength = (EditText) findViewById(R.id.hairlength);
        eyecolor = (EditText) findViewById(R.id.eyecolor);
        skintone = (EditText) findViewById(R.id.skintone);
        facialhair = (EditText) findViewById(R.id.facialhair);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        hipsize = (EditText) findViewById(R.id.hip_size);
        chestsize = (EditText) findViewById(R.id.chest_size);
        waistsize = (EditText) findViewById(R.id.waist_size);



        input_bodytype = (TextInputLayout) findViewById(R.id.input_bodytype);
        input_haircolor = (TextInputLayout) findViewById(R.id.input_haircolor);
        input_hairlength = (TextInputLayout) findViewById(R.id.input_hairlength);
        input_eyecolor = (TextInputLayout) findViewById(R.id.input_eyecolor);
        input_skintone = (TextInputLayout) findViewById(R.id.input_skintone);
        input_facialhair = (TextInputLayout) findViewById(R.id.input_facialhair);
        input_height = (TextInputLayout) findViewById(R.id.input_height);
        input_weight = (TextInputLayout) findViewById(R.id.input_weight);
        input_hipsize = (TextInputLayout) findViewById(R.id.input_hipsize);
        input_chestsize = (TextInputLayout) findViewById(R.id.input_chestsize);
        input_waistsize = (TextInputLayout) findViewById(R.id.input_waistsize);




        bodytype.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Athletic","Average","Petite","Thin","Heavy","Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                    alertDialogBuilder.setTitle("Choose Body Type");
                    alertDialogBuilder
                            .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedgend = checkedItem.toString();
                                    bodytype.setText(selectedgend);
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });

        bodytype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items = {"Athletic","Average","Petite","Thin","Heavy","Other"};

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                alertDialogBuilder.setTitle("Choose Body Type");
                alertDialogBuilder
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedgend = checkedItem.toString();
                                bodytype.setText(selectedgend);
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        bodytype.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        haircolor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = { "Black", "Brown", "White","Red", "Blonde", "Burgundy", "Ginger", "Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                    alertDialogBuilder.setTitle("Choose Hair Colour");
                    alertDialogBuilder
                            .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedgend = checkedItem.toString();
                                    haircolor.setText(selectedgend);
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });

        haircolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items = { "Black", "Brown", "White","Red", "Blonde", "Burgundy", "Ginger", "Other"};

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                alertDialogBuilder.setTitle("Choose Hair Colour");
                alertDialogBuilder
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedgend = checkedItem.toString();
                                haircolor.setText(selectedgend);
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        haircolor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        hairlength.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Short", "Medium", "Long", "Bald", "Shaved", "Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                    alertDialogBuilder.setTitle("Choose Hair Length");
                    alertDialogBuilder
                            .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedgend = checkedItem.toString();
                                    hairlength.setText(selectedgend);
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });

        hairlength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items = {"Short", "Medium", "Long", "Bald", "Shaved", "Other"};

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                alertDialogBuilder.setTitle("Choose Hair Length");
                alertDialogBuilder
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedgend = checkedItem.toString();
                                hairlength.setText(selectedgend);
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        hairlength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        eyecolor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Black", "Brown", "Blue", "Amber", "Grey", "Green", "Hazel", "Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                    alertDialogBuilder.setTitle("Choose Eye Color");
                    alertDialogBuilder
                            .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedgend = checkedItem.toString();
                                    eyecolor.setText(selectedgend);
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });

        eyecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items = {"Black", "Brown", "Blue", "Amber", "Grey", "Green", "Hazel", "Other"};

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                alertDialogBuilder.setTitle("Choose Eye Color");
                alertDialogBuilder
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedgend = checkedItem.toString();
                                eyecolor.setText(selectedgend);
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        eyecolor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        skintone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Very Fair", "Fair", "Medium", "Olive", "Brown", "Dark"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                    alertDialogBuilder.setTitle("Choose Skin Tone");
                    alertDialogBuilder
                            .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedgend = checkedItem.toString();
                                    skintone.setText(selectedgend);
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });

        skintone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items = {"Very Fair", "Fair", "Medium", "Olive", "Brown", "Dark"};

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                alertDialogBuilder.setTitle("Choose Skin Tone");
                alertDialogBuilder
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedgend = checkedItem.toString();
                                skintone.setText(selectedgend);
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        skintone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        facialhair.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Beard","Moustache", "Beard & Moustache", "Stubble / Goatie", "None", "Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                    alertDialogBuilder.setTitle("Choose Facial Hair");
                    alertDialogBuilder
                            .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedgend = checkedItem.toString();
                                    facialhair.setText(selectedgend);
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });

        facialhair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items = {"Beard","Moustache", "Beard & Moustache", "Stubble / Goatie", "None", "Other"};

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup2.this);
                alertDialogBuilder.setTitle("Choose Facial Hair");
                alertDialogBuilder
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedgend = checkedItem.toString();
                                facialhair.setText(selectedgend);
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        facialhair.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        hipsize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });






        chestsize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        waistsize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



       /* if(getSPData("gender").equals("Male")){
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
        });*/


        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!bodytype.getText().toString().equals("")) storeSPData("bodyType", bodytype.getText().toString());

                if(!haircolor.getText().toString().equals("")) storeSPData("hairColor", haircolor.getText().toString());

                if(!hairlength.getText().toString().equals("")) storeSPData("hairLength", hairlength.getText().toString());

                if(!eyecolor.getText().toString().equals("")) storeSPData("eyeColor", eyecolor.getText().toString());

                if(!facialhair.getText().toString().equals("")) storeSPData("facialHair", facialhair.getText().toString());

                if(!skinToneSelector.equals("null")) storeSPData("skinTone", skinToneSelector);

                if(!height.getText().toString().equals("")) storeSPData("height", height.getText().toString());

                if(!weight.getText().toString().equals("")) storeSPData("weight", weight.getText().toString());

                if(!hipsize.getText().toString().equals("")) storeSPData("hipsize", hipsize.getText().toString());

                if(!chestsize.getText().toString().equals("")) storeSPData("chestSize", chestsize.getText().toString());

                if(!waistsize.getText().toString().equals("")) storeSPData("waistSize", waistsize.getText().toString());



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

                input_facialhair.setVisibility(View.GONE);
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
