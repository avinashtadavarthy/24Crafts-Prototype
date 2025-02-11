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
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
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
            "Beard","Moustache", "Beard & moustache", "Stubble / goatie", "None", "Other"};


    String skinToneSelector = "null";

    LinearLayout facialhairlayout;

    TextInputLayout input_bodytype, input_haircolor, input_hairlength, input_eyecolor, input_skintone, input_facialhair, input_height, input_weight, input_hipsize, input_chestsize, input_waistsize;
    EditText bodytype, haircolor, hairlength, eyecolor, skintone, facialhair, height, weight, hipsize, chestsize, waistsize;

    //for verification sake
    public int vbodytype = 0, vhaircolor = 0, vhairlength = 0, veyecolor = 0, vskintone = 0, vfacialhair = 0, vheight = 0, vweight = 0, vhipsize = 0, vchestsize = 0, vwaistsize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        final Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        final String craft = bundle.getString("craft");
        final String type = bundle.getString("type");

        TextView textView =(TextView)findViewById(R.id.welcome);
        textView.setText("Hello, " + getSPData("firstname") + " " + getSPData("lastname"));


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


        bodytype.setShowSoftInputOnFocus(false);
        haircolor.setShowSoftInputOnFocus(false);
        hairlength.setShowSoftInputOnFocus(false);
        eyecolor.setShowSoftInputOnFocus(false);
        skintone.setShowSoftInputOnFocus(false);
        facialhair.setShowSoftInputOnFocus(false);


        bodytype.setInputType(InputType.TYPE_NULL);
        haircolor.setInputType(InputType.TYPE_NULL);
        hairlength.setInputType(InputType.TYPE_NULL);
        eyecolor.setInputType(InputType.TYPE_NULL);
        skintone.setInputType(InputType.TYPE_NULL);
        facialhair.setInputType(InputType.TYPE_NULL);



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

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("Choose Body Type");
                    int position;
                    if (bodytype.getText().toString().equals("Athletic")){
                        position = 0;
                    } else if (bodytype.getText().toString().equals("Average")){
                        position = 1;
                    } else if (bodytype.getText().toString().equals("Petite")){
                        position = 2;
                    }else if (bodytype.getText().toString().equals("Thin")){
                        position = 3;
                    }else if (bodytype.getText().toString().equals("Heavy")){
                        position = 4;
                    }else if (bodytype.getText().toString().equals("Other")){
                        position = 5;
                    }
                    else {
                        position = -1;
                    }
                    alertDialogBuilder
                            .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedbodyType = checkedItem.toString();
                                    bodytype.setText(selectedbodyType);
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

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("Choose Body Type");
                int position;
                    if (bodytype.getText().toString().equals("Athletic")){
                        position = 0;
                    } else if (bodytype.getText().toString().equals("Average")){
                        position = 1;
                    } else if (bodytype.getText().toString().equals("Petite")){
                        position = 2;
                    }else if (bodytype.getText().toString().equals("Thin")){
                        position = 3;
                    }else if (bodytype.getText().toString().equals("Heavy")){
                        position = 4;
                    }else if (bodytype.getText().toString().equals("Other")){
                        position = 5;
                    }
                    else {
                        position = -1;
                    }
                alertDialogBuilder
                        .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedbodyType = checkedItem.toString();
                                bodytype.setText(selectedbodyType);
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
                if(vbodytype != 0) {
                    vbodytype = 0;
                    input_bodytype.setError(null);
                    input_bodytype.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vbodytype != 0) {
                    vbodytype = 0;
                    input_bodytype.setError(null);
                    input_bodytype.setErrorEnabled(false);
                }
            }
        });




        haircolor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = { "Black", "Brown", "White","Red", "Blonde", "Burgundy", "Ginger", "Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("Choose Hair Colour");
                    int position;
                    if (haircolor.getText().toString().equals("Black")){
                        position = 0;
                    } else if (haircolor.getText().toString().equals("Brown")){
                        position = 1;
                    } else if (haircolor.getText().toString().equals("White")){
                        position = 2;
                    }else if (haircolor.getText().toString().equals("Red")){
                        position = 3;
                    }else if (haircolor.getText().toString().equals("Blonde")){
                        position = 4;
                    }else if (haircolor.getText().toString().equals("Burgundy")){
                        position = 5;
                    }else if (haircolor.getText().toString().equals("Ginger")){
                        position = 6;
                    }else if (haircolor.getText().toString().equals("Other")){
                        position = 7;
                    }
                    else {
                        position = -1;
                    }
                    alertDialogBuilder
                            .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedHairColor = checkedItem.toString();
                                    haircolor.setText(selectedHairColor);
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

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("Choose Hair Colour");
                int position;
                    if (haircolor.getText().toString().equals("Black")){
                        position = 0;
                    } else if (haircolor.getText().toString().equals("Brown")){
                        position = 1;
                    } else if (haircolor.getText().toString().equals("White")){
                        position = 2;
                    }else if (haircolor.getText().toString().equals("Red")){
                        position = 3;
                    }else if (haircolor.getText().toString().equals("Blonde")){
                        position = 4;
                    }else if (haircolor.getText().toString().equals("Burgundy")){
                        position = 5;
                    }else if (haircolor.getText().toString().equals("Ginger")){
                        position = 6;
                    }else if (haircolor.getText().toString().equals("Other")){
                        position = 7;
                    }
                    else {
                        position = -1;
                    }
                alertDialogBuilder
                        .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedHairColor = checkedItem.toString();
                                haircolor.setText(selectedHairColor);
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
                if(vhaircolor != 0) {
                    vhaircolor = 0;
                    input_haircolor.setError(null);
                    input_haircolor.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vhaircolor != 0) {
                    vhaircolor = 0;
                    input_haircolor.setError(null);
                    input_haircolor.setErrorEnabled(false);
                }
            }
        });

        hairlength.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Short", "Medium", "Long", "Bald", "Shaved", "Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("Choose Hair Length");
                    int position;
                    if (hairlength.getText().toString().equals("Short")){
                        position = 0;
                    } else if (hairlength.getText().toString().equals("Medium")){
                        position = 1;
                    } else if (hairlength.getText().toString().equals("Long")){
                        position = 2;
                    }else if (hairlength.getText().toString().equals("Bald")){
                        position = 3;
                    }else if (hairlength.getText().toString().equals("Shaved")){
                        position = 4;
                    }else if (hairlength.getText().toString().equals("Other")){
                        position = 5;
                    }
                    else {
                        position = -1;
                    }
                    alertDialogBuilder
                            .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedHairLength = checkedItem.toString();
                                    hairlength.setText(selectedHairLength);
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

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("Choose Hair Length");
                int position;
                    if (hairlength.getText().toString().equals("Short")){
                        position = 0;
                    } else if (hairlength.getText().toString().equals("Medium")){
                        position = 1;
                    } else if (hairlength.getText().toString().equals("Long")){
                        position = 2;
                    }else if (hairlength.getText().toString().equals("Bald")){
                        position = 3;
                    }else if (hairlength.getText().toString().equals("Shaved")){
                        position = 4;
                    }else if (hairlength.getText().toString().equals("Other")){
                        position = 5;
                    }
                    else {
                        position = -1;
                    }
                alertDialogBuilder
                        .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedHairLength = checkedItem.toString();
                                hairlength.setText(selectedHairLength);
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
                if(vhairlength!= 0) {
                    vhairlength = 0;
                    input_hairlength.setError(null);
                    input_hairlength.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vhairlength!= 0) {
                    vhairlength = 0;
                    input_hairlength.setError(null);
                    input_hairlength.setErrorEnabled(false);
                }
            }
        });




        eyecolor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Black", "Brown", "Blue", "Amber", "Grey", "Green", "Hazel", "Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("Choose Eye Color");
                    int position;
                    if (eyecolor.getText().toString().equals("Black")){
                        position = 0;
                    } else if (eyecolor.getText().toString().equals("Brown")){
                        position = 1;
                    } else if (eyecolor.getText().toString().equals("Blue")){
                        position = 2;
                    }else if (eyecolor.getText().toString().equals("Amber")){
                        position = 3;
                    }else if (eyecolor.getText().toString().equals("Grey")){
                        position = 4;
                    }else if (eyecolor.getText().toString().equals("Green")){
                        position = 5;
                    }else if (eyecolor.getText().toString().equals("Hazel")){
                        position = 6;
                    }else if (eyecolor.getText().toString().equals("Other")){
                        position = 7;
                    }
                    else {
                        position = -1;
                    }
                    alertDialogBuilder
                            .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedEyeColor = checkedItem.toString();
                                    eyecolor.setText(selectedEyeColor);
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

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("Choose Eye Color");
                int position;
                    if (eyecolor.getText().toString().equals("Black")){
                        position = 0;
                    } else if (eyecolor.getText().toString().equals("Brown")){
                        position = 1;
                    } else if (eyecolor.getText().toString().equals("Blue")){
                        position = 2;
                    }else if (eyecolor.getText().toString().equals("Amber")){
                        position = 3;
                    }else if (eyecolor.getText().toString().equals("Grey")){
                        position = 4;
                    }else if (eyecolor.getText().toString().equals("Green")){
                        position = 5;
                    }else if (eyecolor.getText().toString().equals("Hazel")){
                        position = 6;
                    }else if (eyecolor.getText().toString().equals("Other")){
                        position = 7;
                    }
                    else {
                        position = -1;
                    }
                alertDialogBuilder
                        .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedEyeColor = checkedItem.toString();
                                eyecolor.setText(selectedEyeColor);
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
                if(veyecolor!= 0) {
                    veyecolor = 0;
                    input_eyecolor.setError(null);
                    input_eyecolor.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(veyecolor!= 0) {
                    veyecolor = 0;
                    input_eyecolor.setError(null);
                    input_eyecolor.setErrorEnabled(false);
                }
            }
        });



        skintone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Very Fair", "Fair", "Medium", "Olive", "Brown", "Dark"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("Choose Skin Tone");
                    int position;
                    if (skintone.getText().toString().equals("Very Fair")){
                        position = 0;
                    } else if (skintone.getText().toString().equals("Fair")){
                        position = 1;
                    } else if (skintone.getText().toString().equals("Medium")){
                        position = 2;
                    }else if (skintone.getText().toString().equals("Olive")){
                        position = 3;
                    }else if (skintone.getText().toString().equals("Brown")){
                        position = 4;
                    }else if (skintone.getText().toString().equals("Dark")){
                        position = 5;
                    }
                    else {
                        position = -1;
                    }
                    alertDialogBuilder
                            .setSingleChoiceItems(items,position, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedSkinTone = checkedItem.toString();
                                    skintone.setText(selectedSkinTone);
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

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("Choose Skin Tone");
                int position;
                    if (skintone.getText().toString().equals("Very Fair")){
                        position = 0;
                    } else if (skintone.getText().toString().equals("Fair")){
                        position = 1;
                    } else if (skintone.getText().toString().equals("Medium")){
                        position = 2;
                    }else if (skintone.getText().toString().equals("Olive")){
                        position = 3;
                    }else if (skintone.getText().toString().equals("Brown")){
                        position = 4;
                    }else if (skintone.getText().toString().equals("Dark")){
                        position = 5;
                    }
                    else {
                        position = -1;
                    }
                alertDialogBuilder
                        .setSingleChoiceItems(items,position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedSkinTone = checkedItem.toString();
                                skintone.setText(selectedSkinTone);
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
                if(vskintone!= 0) {
                    vskintone = 0;
                    input_skintone.setError(null);
                    input_skintone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vskintone!= 0) {
                    vskintone = 0;
                    input_skintone.setError(null);
                    input_skintone.setErrorEnabled(false);
                }
            }
        });




        facialhair.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    final CharSequence[] items = {"Beard","Moustache", "Beard & moustache", "Stubble / goatie", "None", "Other"};

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("Choose Facial Hair");
                    int position;
                    if (facialhair.getText().toString().equals("Beard")){
                        position = 0;
                    } else if (facialhair.getText().toString().equals("Moustache")){
                        position = 1;
                    } else if (facialhair.getText().toString().equals("Beard & moustache")){
                        position = 2;
                    }else if (facialhair.getText().toString().equals("Stubble / goatie")){
                        position = 3;
                    }else if (facialhair.getText().toString().equals("None")){
                        position = 4;
                    }else if (facialhair.getText().toString().equals("Other")){
                        position = 5;
                    }
                    else {
                        position = -1;
                    }
                    alertDialogBuilder
                            .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedFacialHair = checkedItem.toString();
                                    facialhair.setText(selectedFacialHair);
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

                final CharSequence[] items = {"Beard","Moustache", "Beard & moustache", "Stubble / goatie", "None", "Other"};

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup2.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("Choose Facial Hair");
                int position;
                    if (facialhair.getText().toString().equals("Beard")){
                        position = 0;
                    } else if (facialhair.getText().toString().equals("Moustache")){
                        position = 1;
                    } else if (facialhair.getText().toString().equals("Beard & moustache")){
                        position = 2;
                    }else if (facialhair.getText().toString().equals("Stubble / goatie")){
                        position = 3;
                    }else if (facialhair.getText().toString().equals("None")){
                        position = 4;
                    }else if (facialhair.getText().toString().equals("Other")){
                        position = 5;
                    }
                    else {
                        position = -1;
                    }
                alertDialogBuilder
                        .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedFacialHair = checkedItem.toString();
                                facialhair.setText(selectedFacialHair);
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
                if(vfacialhair!= 0) {
                    vfacialhair = 0;
                    input_facialhair.setError(null);
                    input_facialhair.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vfacialhair!= 0) {
                    vfacialhair = 0;
                    input_facialhair.setError(null);
                    input_facialhair.setErrorEnabled(false);
                }
            }
        });





        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vheight!= 0) {
                    vheight = 0;
                    input_height.setError(null);
                    input_height.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vheight!= 0) {
                    vheight = 0;
                    input_height.setError(null);
                    input_height.setErrorEnabled(false);
                }
            }
        });




        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vweight!= 0) {
                    vweight = 0;
                    input_weight.setError(null);
                    input_weight.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vweight!= 0) {
                    vweight = 0;
                    input_weight.setError(null);
                    input_weight.setErrorEnabled(false);
                }
            }
        });





        hipsize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vhipsize!= 0) {
                    vhipsize = 0;
                    input_hipsize.setError(null);
                    input_hipsize.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vhipsize!= 0) {
                    vhipsize = 0;
                    input_hipsize.setError(null);
                    input_hipsize.setErrorEnabled(false);
                }
            }
        });






        chestsize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vchestsize!= 0) {
                    vchestsize = 0;
                    input_chestsize.setError(null);
                    input_chestsize.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vchestsize!= 0) {
                    vchestsize = 0;
                    input_chestsize.setError(null);
                    input_chestsize.setErrorEnabled(false);
                }
            }
        });





        waistsize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vwaistsize!= 0) {
                    vwaistsize = 0;
                    input_waistsize.setError(null);
                    input_waistsize.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vwaistsize!= 0) {
                    vwaistsize = 0;
                    input_waistsize.setError(null);
                    input_waistsize.setErrorEnabled(false);
                }
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



                // EditText bodytype, haircolor, hairlength, eyecolor, skintone, facialhair, height, weight, hipsize, chestsize, waistsize;
                //check for empty fields
                if(bodytype.getText().toString().equals("")) {
                    input_bodytype.setErrorEnabled(true);
                    input_bodytype.setError("Enter your body type.");
                    vbodytype = 1;
                } else {
                    input_bodytype.setError(null);
                    input_bodytype.setErrorEnabled(false);
                }

                if(haircolor.getText().toString().equals("")) {
                    input_haircolor.setErrorEnabled(true);
                    input_haircolor.setError("Enter your hair color.");
                    vhaircolor = 1;
                } else {
                    input_haircolor.setError(null);
                    input_haircolor.setErrorEnabled(false);
                }

                if(hairlength.getText().toString().equals("")) {
                    input_hairlength.setErrorEnabled(true);
                    input_hairlength.setError("Enter your hair length.");
                    vhairlength = 1;
                } else {
                    input_hairlength.setError(null);
                    input_hairlength.setErrorEnabled(false);
                }

                if(eyecolor.getText().toString().equals("")) {
                    input_eyecolor.setErrorEnabled(true);
                    input_eyecolor.setError("Enter your eye color.");
                    veyecolor = 1;
                } else {
                    input_eyecolor.setError(null);
                    input_eyecolor.setErrorEnabled(false);
                }

                if(skintone.getText().toString().equals("")) {
                    input_skintone.setErrorEnabled(true);
                    input_skintone.setError("Confirm the skin tone.");
                    vskintone = 1;
                } else {
                    input_skintone.setError(null);
                    input_skintone.setErrorEnabled(false);
                }

                if(facialhair.getText().toString().equals("")) {
                    input_facialhair.setErrorEnabled(true);
                    input_facialhair.setError("Select your facial hair.");
                    vfacialhair = 1;
                } else {
                    input_facialhair.setError(null);
                    input_facialhair.setErrorEnabled(false);
                }

                if(height.getText().toString().equals("")) {
                    input_height.setErrorEnabled(true);
                    input_height.setError("Select your height.");
                    vheight = 1;
                } else {
                    input_height.setError(null);
                    input_height.setErrorEnabled(false);
                }

                if(weight.getText().toString().equals("")) {
                    input_weight.setErrorEnabled(true);
                    input_weight.setError("Select your weight.");
                    vweight = 1;
                } else {
                    input_weight.setError(null);
                    input_weight.setErrorEnabled(false);
                }

                if(hipsize.getText().toString().equals("")) {
                    input_hipsize.setErrorEnabled(true);
                    input_hipsize.setError("Select your hip size.");
                    vhipsize = 1;
                } else {
                    input_hipsize.setError(null);
                    input_hipsize.setErrorEnabled(false);
                }

                if(chestsize.getText().toString().equals("")) {
                    input_chestsize.setErrorEnabled(true);
                    input_chestsize.setError("Select your chest size.");
                    vchestsize = 1;
                } else {
                    input_chestsize.setError(null);
                    input_chestsize.setErrorEnabled(false);
                }

                if(waistsize.getText().toString().equals("")) {
                    input_waistsize.setErrorEnabled(true);
                    input_waistsize.setError("Select your waist size.");
                    vwaistsize = 1;
                } else {
                    input_waistsize.setError(null);
                    input_waistsize.setErrorEnabled(false);
                }





                if(!bodytype.getText().toString().equals("") && !haircolor.getText().toString().equals("") && !hairlength.getText().toString().equals("") && !eyecolor.getText().toString().equals("") && !facialhair.getText().toString().equals("") && !skintone.getText().toString().equals("") && !height.getText().toString().equals("") && !weight.getText().toString().equals("") && !hipsize.getText().toString().equals("") && !chestsize.getText().toString().equals("") && !waistsize.getText().toString().equals(""))
              {
                  storeSPData("bodyType", bodytype.getText().toString());
                  storeSPData("hairColor", haircolor.getText().toString());
                  storeSPData("hairLength", hairlength.getText().toString());
                  storeSPData("eyeColor", eyecolor.getText().toString());
                  storeSPData("facialHair", facialhair.getText().toString());
                  storeSPData("skinTone", skintone.getText().toString());
                  storeSPData("height", height.getText().toString());
                  storeSPData("weight", weight.getText().toString());
                  storeSPData("hipsize", hipsize.getText().toString());
                  storeSPData("chestSize", chestsize.getText().toString());
                  storeSPData("waistSize", waistsize.getText().toString());



                  Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                  Bundle bundle = new Bundle();
                  bundle.putString("name", name);
                  bundle.putString("craft", craft);
                  bundle.putString("type", type);
                  goToNextActivity.putExtras(bundle);
                  startActivity(goToNextActivity);
              }


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
