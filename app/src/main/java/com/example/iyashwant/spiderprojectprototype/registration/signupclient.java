package com.example.iyashwant.spiderprojectprototype.registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import com.example.iyashwant.spiderprojectprototype.CustomAdapterSpinner;
import com.example.iyashwant.spiderprojectprototype.R;

import java.util.Calendar;

public class signupclient extends AppCompatActivity{

    String[] whoN={"Who am I?",
            "Casting Agent","Co-Director","Co-Producer","Director","Director Assistant",
            "Director Audition","Executive Producer","Model Coordinator",
            "Producer","Production House Manager"};

    String[] genderString={"Choose Gender",
            "Male","Female","Other"};

    String name,selectedcraft = "null";

    //datepicker
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    EditText password, confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupclient);
        password = (EditText) findViewById(R.id.password);
        password.setTransformationMethod(new PasswordTransformationMethod());
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        confirm_password.setTransformationMethod(new PasswordTransformationMethod());
        Spinner craft = (Spinner) findViewById(R.id.spinner);
        CustomAdapterSpinner craftAdapter=new CustomAdapterSpinner(getApplicationContext(),whoN);
        craft.setAdapter(craftAdapter);

        Spinner gender = (Spinner) findViewById(R.id.gender);
        CustomAdapterSpinner genderAdapter=new CustomAdapterSpinner(getApplicationContext(),genderString);
        gender.setAdapter(genderAdapter);
        craft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(whoN[position])
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

        final EditText name1 =(EditText)findViewById(R.id.first_name);


        Button next = (Button)findViewById(R.id.button11);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedcraft.equals("null"))
                {
                    Toast.makeText(getApplicationContext(),"Please select appropriate Portfolio to continue",Toast.LENGTH_LONG).show();
                }

                else
                {
                    name = name1.getText().toString();
                    Intent goToNextActivity = new Intent(getApplicationContext(), signup3client.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("craft", selectedcraft);
                    goToNextActivity.putExtras(bundle);
                    startActivity(goToNextActivity);
                }

            }
        });

        //datepicker
        dateView = (TextView) findViewById(R.id.dob);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

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
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}