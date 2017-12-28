package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twenty.four.crafts.CustomAdapterSpinner;
import com.twenty.four.crafts.Main2Activity;
import com.twenty.four.crafts.ProfileViewEdit;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;

import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity{

    //TODO: Add languages spoken "multi select spinner"

    String[] whoNcrafts = {"Who am I?",
            "Actor","Actress","Child Artist","Singer","Dancer",
            "Side Artists","Assistant Director","Lyric Writer / Lyricist",
            "Dialouge Writer","Script / Screenplay Writers", "Story Board Artist",
            "Choreographer","Director of Photography", "Still Photographer",
            "PRO", "Designer", "Production Manager",
            "Focus Puller", "Vehicle Driver", "Mic Department",
            "Music Director", "Makeup Man", "Hair Dresser",
            "Costumer", "Art Department", "Set Department",
            "Stuntman", "Editor", "Location Manager",
            "Production (Food)", "Dubbing Artists", "Sound Recording Engineers",
            "Sound Mixing Engineers", "Digital Intermediate", "VFX / CG",
            "SFX", "Pet Suppliers / Pet Doctors / AWBI Certifications"};

    String[] whoNclients = {"Who am I?",
            "Casting Agent","Co-Director","Co-Producer","Director","Director Assistant",
            "Director Audition","Executive Producer","Model Coordinator",
            "Producer","Production House Manager"};

    String[] genderString = {"Choose Gender",
            "Male","Female","Other"};

    String name, selectedcraft = "null", selectedgender = "null";


    //integrating logins
    String type;
    String firstname;
    String lastname;
    String email;
    String gender;
    String imgurl;

    EditText first_name1, last_name1, email1,
            password1, confirm_password1, residing1, hometown1;
    Spinner craft,genderspin;
    CircleImageView profile_image1;

    //datepicker
    TextView dob1;
    int year, month, day;

    //gallery access
    Button click_picture, import_from_gallery;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 1888;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //gallery access
        click_picture = (Button) findViewById(R.id.click_picture);
        import_from_gallery = (Button) findViewById(R.id.import_from_gallery);

        // TODO: send the images up to the server on submit
        import_from_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

        click_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });



        //receiving data
        Bundle bundle1 = getIntent().getExtras();
        type = bundle1.getString("type");
        firstname = bundle1.getString("firstname");
        lastname = bundle1.getString("lastname");
        email = bundle1.getString("email");
        gender = bundle1.getString("gender");
        imgurl = bundle1.getString("imgurl");


        first_name1 = (EditText) findViewById(R.id.first_name);
        last_name1 = (EditText) findViewById(R.id.last_name);
        email1 = (EditText) findViewById(R.id.email);
        password1 = (EditText) findViewById(R.id.password);
        confirm_password1 = (EditText) findViewById(R.id.confirm_password);
        residing1 = (EditText) findViewById(R.id.residing);
        hometown1 = (EditText) findViewById(R.id.hometown);
        profile_image1 = (CircleImageView) findViewById(R.id.profile_image);
        dob1 = (TextView) findViewById(R.id.dob);


        /*calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        date = day + "/" + month + "/" + year;*/

        password1.setTransformationMethod(new PasswordTransformationMethod());
        confirm_password1.setTransformationMethod(new PasswordTransformationMethod());

        craft = (Spinner) findViewById(R.id.spinner);
        if(type.equals("craftsman")) {

            CustomAdapterSpinner craftAdapter=new CustomAdapterSpinner(getApplicationContext(),whoNcrafts);
            craft.setAdapter(craftAdapter);
            craft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch(whoNcrafts[position])
                    {
                        case "Actor": selectedcraft = "Actor"; break;
                        case "Actress": selectedcraft = "Actress"; break;
                        case "Child Artist": selectedcraft = "Child Artist"; break;
                        case "Singer": selectedcraft = "Singer"; break;
                        case "Dancer": selectedcraft = "Dancer"; break;
                        case "Side Artists": selectedcraft = "Side Artists"; break;
                        case "Assistant Director": selectedcraft = "Assistant Director"; break;
                        case "Lyric Writer / Lyricist": selectedcraft = "Lyric Writer / Lyricist"; break;
                        case "Dialouge Writer": selectedcraft = "Dialouge Writer"; break;
                        case "Script / Screenplay Writers": selectedcraft = "Script / Screenplay Writers"; break;
                        case "Story Board Artist": selectedcraft = "Story Board Artist"; break;
                        case "Choreographer": selectedcraft = "Choreographer"; break;
                        case "Director of Photography": selectedcraft = "Director of Photography"; break;
                        case "Still Photographer": selectedcraft = "Still Photographer"; break;
                        case "PRO": selectedcraft = "PRO"; break;
                        case "Designer": selectedcraft = "Designer"; break;
                        case "Production Manager": selectedcraft = "Production Manager"; break;
                        case  "Focus Puller": selectedcraft =  "Focus Puller"; break;
                        case "Vehicle Driver": selectedcraft = "Vehicle Driver"; break;
                        case "Mic Department": selectedcraft = "Mic Department"; break;
                        case "Music Director": selectedcraft = "Music Director"; break;
                        case "Makeup Man": selectedcraft = "Makeup Man"; break;
                        case "Hair Dresser": selectedcraft = "Hair Dresser"; break;
                        case "Costumer": selectedcraft = "Costumer"; break;
                        case "Art Department": selectedcraft = "Art Department"; break;
                        case "Set Department": selectedcraft = "Set Department"; break;
                        case "Stuntman": selectedcraft = "Stuntman"; break;
                        case "Editor": selectedcraft = "Editor"; break;
                        case "Location Manager": selectedcraft = "Location Manager"; break;
                        case "Production (Food)": selectedcraft = "Production (Food)"; break;
                        case  "Dubbing Artists": selectedcraft =  "Dubbing Artists"; break;
                        case "Sound Recording Engineers": selectedcraft = "Sound Recording Engineers"; break;
                        case "Sound Mixing Engineers": selectedcraft = "Sound Mixing Engineers"; break;
                        case "Digital Intermediate": selectedcraft = "Digital Intermediate"; break;
                        case "VFX / CG": selectedcraft = "VFX / CG"; break;
                        case "SFX": selectedcraft = "SFX"; break;
                        case "Pet Suppliers / Pet Doctors / AWBI Certifications": selectedcraft = "Pet Suppliers / Pet Doctors / AWBI Certifications"; break;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } else if(type.equals("client")) {

            CustomAdapterSpinner craftAdapter=new CustomAdapterSpinner(getApplicationContext(),whoNclients);
            craft.setAdapter(craftAdapter);
            craft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch(whoNclients[position])
                    {
                        case "Casting Agent": selectedcraft = "Casting Agent"; break;
                        case "Co-Director": selectedcraft = "Co-Director"; break;
                        case "Co-Producer": selectedcraft = "Co-Producer"; break;
                        case "Director": selectedcraft = "Director"; break;
                        case "Director Assistant": selectedcraft = "Director Assistant"; break;
                        case "Director Audition": selectedcraft = "Director Audition"; break;
                        case "Executive Producer": selectedcraft = "Executive Producer"; break;
                        case "Model Coordinator": selectedcraft = "Model Coordinator"; break;
                        case "Producer": selectedcraft = "Producer"; break;
                        case "Production House Manager": selectedcraft = "Production House Manager"; break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        }


        genderspin = (Spinner) findViewById(R.id.gender);
        CustomAdapterSpinner genderAdapter=new CustomAdapterSpinner(getApplicationContext(),genderString);
        genderspin.setAdapter(genderAdapter);

        genderspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(genderString[position])
                {
                    case "Male": selectedgender = "Male"; break;
                    case "Female": selectedgender = "Female"; break;
                    case "Other": selectedgender = "Other"; break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        if(!firstname.equals("null")) {
            first_name1.setText(firstname);
            last_name1.setText(lastname);
            email1.setText(email);
            Picasso.with(getApplicationContext()).load(imgurl).into(profile_image1);
            if(gender.equals("male")) genderspin.setSelection(1);
            else if(gender.equals("female")) genderspin.setSelection(2);
        }


        //commenting out until we can display the text hint in the page
        //multiselect spinner
        /*ArrayList<String> options = new ArrayList<>();
        options.add("English");
        options.add("Hindi");
        options.add("Telugu");
        options.add("Tamil");
        options.add("Kannada");
        options.add("Malayalam");
        options.add("Punjabi");
        options.add("Bhojpuri");

        MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner) findViewById(R.id.languages);
        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, options);
        multiSelectSpinner.setListAdapter(adapter);
        multiSelectSpinner.setTitle("Select Languages Known");
*/

        //final EditText name1 =(EditText)findViewById(R.id.first_name);


        Button next = (Button)findViewById(R.id.button11);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("craftsman")) {



                    if(selectedcraft.equals("Actor") || selectedcraft.equals("Actress") || selectedcraft.equals("Child Artist") || selectedcraft.equals("Dancer") || selectedcraft.equals("Side Artists")){
                        name=first_name1.getText().toString();
                        Intent goToNextActivity = new Intent(getApplicationContext(), signup2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("craft",selectedcraft);
                        bundle.putString("type",type);
                        goToNextActivity.putExtras(bundle);
                        startActivity(goToNextActivity);
                    } else if(selectedcraft.equals("null"))
                    {
                        Toast.makeText(getApplicationContext(),"Please select appropriate Portfolio to continue",Toast.LENGTH_LONG).show();
                    } else if(selectedgender.equals("null")) {
                        Toast.makeText(getApplicationContext(),"Please select appropriate gender to continue",Toast.LENGTH_LONG).show();
                    } else if(selectedcraft.equals("null") && selectedgender.equals("null")) {
                        Toast.makeText(getApplicationContext(), "Select all mandatory fields to continue", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        User.getInstance().firstname = first_name1.getText().toString();
                        User.getInstance().lastname = last_name1.getText().toString();
                        User.getInstance().useremail = email1.getText().toString();
                        User.getInstance().password = password1.getText().toString();
                        User.getInstance().dob = dob1.getText().toString().trim();
                        User.getInstance().usergender = selectedgender;
                        User.getInstance().category = selectedcraft;
                        User.getInstance().residingin = residing1.getText().toString();
                        User.getInstance().hometown = hometown1.getText().toString();



                        name=first_name1.getText().toString();
                        Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("craft",selectedcraft);
                        bundle.putString("type",type);
                        goToNextActivity.putExtras(bundle);
                        startActivity(goToNextActivity);
                    }



                } else if(type.equals("client")) {



                    if(selectedcraft.equals("null")) {
                        Toast.makeText(getApplicationContext(),"Please select appropriate Portfolio to continue",Toast.LENGTH_LONG).show();
                    } else if(selectedgender.equals("null")) {
                        Toast.makeText(getApplicationContext(),"Please select appropriate gender to continue",Toast.LENGTH_LONG).show();
                    } else if(selectedcraft.equals("null") && selectedgender.equals("null")) {
                        Toast.makeText(getApplicationContext(), "Select all mandatory fields to continue", Toast.LENGTH_SHORT).show();
                    } else {

                        User.getInstance().firstname = first_name1.getText().toString();
                        User.getInstance().lastname = last_name1.getText().toString();
                        User.getInstance().useremail = email1.getText().toString();
                        User.getInstance().password = password1.getText().toString();
                        User.getInstance().dob = dob1.getText().toString().trim();
                        User.getInstance().usergender = selectedgender;
                        User.getInstance().category = selectedcraft;
                        User.getInstance().residingin = residing1.getText().toString();
                        User.getInstance().hometown = hometown1.getText().toString();



                        name = first_name1.getText().toString();
                        Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("craft", selectedcraft);
                        bundle.putString("type",type);
                        goToNextActivity.putExtras(bundle);
                        startActivity(goToNextActivity);
                    }



                }


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                profile_image1.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profile_image1.setImageBitmap(photo);
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


    //datepicker
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dob1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
