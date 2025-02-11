package com.twenty.four.crafts;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.steelkiwi.cropiwa.image.CropIwaResultReceiver;
import com.twenty.four.crafts.app_startup.Login2;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateAuditions extends AppCompatActivity implements IPickResult, CropIwaResultReceiver.Listener {

    static final int REQUEST_SHOOT_LOCATION = 1000, REQUEST_AUDITION_LOCATION = 2000, AUDITION_FROM=100, AUDITION_TO=200, AUDITION_DATE=10201;
    static final int TIME_DIALOG_ID = 1111;
    static final int REQUEST_IMAGE_LOAD = 9999;

    ProgressDialog progressbar;

    String itemid,projName,projDesc,projType,phoneNo,audLocation,audTime,audDate,applnFrom,applnTo,audImage;



    int mode;

    ImageView aud_image;
    CropIwaResultReceiver cropResultReceiver;
    ProgressBar loadimageprogress;
    CircleImageView edit_aud_image;
    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11;
    TextInputLayout input_1, input_2, input_3, input_4, input_5, input_6, input_7, input_8, input_9, input_10, input_11;
    LinearLayout project_type_layout;
    Button bn1, bn2, bn3, bn4, bn5, bn6, bn7, bn8;

    Bitmap auditionImage;


    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int hr = cal.get(Calendar.HOUR_OF_DAY);
    int min = cal.get(Calendar.MINUTE);
    DatePickerDialog.OnDateSetListener from_dateListener, to_dateListener, audition_dateListener;
    TimePickerDialog.OnTimeSetListener timePickerListener;

    TextView errmsg;

    public void err_msg_disp(int i)
    {
        if(i==1)
            errmsg.setVisibility(View.VISIBLE);
        else
            errmsg.setVisibility(View.GONE);
    }

    public void restore(EditText e) {
        e.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.box));
        err_msg_disp(-1);
    }




    private TextWatcher filterTextWatchere1 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e1);

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere2 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e2);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere3 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e3);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher filterTextWatchere6 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e6);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere7 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e7);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher filterTextWatchere8 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e8);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher filterTextWatchere9 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e9);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher filterTextWatchere10 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e10);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher filterTextWatchere11 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e11);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_auditions);

        final Button postorupdate = findViewById(R.id.postorupdate);

        getSupportActionBar().setTitle("Create an Audition");

        aud_image = (ImageView) findViewById(R.id.aud_image);

        progressbar = new ProgressDialog(CreateAuditions.this);

        Intent intent = getIntent();


        mode = intent.getIntExtra("createoredit",0);
        if(mode == 2) {
            itemid = intent.getStringExtra("id");
            projName = intent.getStringExtra("projName");
            projDesc = intent.getStringExtra("projDesc");
            projType = intent.getStringExtra("projType");
            phoneNo = intent.getStringExtra("phoneNo");
            audLocation = intent.getStringExtra("audLocation");
            audDate = intent.getStringExtra("audDate");
            audTime = intent.getStringExtra("audTime");
            applnFrom = intent.getStringExtra("applnFrom");
            applnTo = intent.getStringExtra("applnTo");
            audImage = intent.getStringExtra("audImage");

            Glide.with(getApplicationContext()).load("http://" + audImage).into(aud_image);
        }





        edit_aud_image = (CircleImageView) findViewById(R.id.edit_aud_image);

        loadimageprogress = (ProgressBar) findViewById(R.id.loadimageprogress);
        loadimageprogress.setVisibility(View.GONE);

        //import image
        edit_aud_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickSetup setup = new PickSetup()
                        .setTitle("Choose Image From")
                        .setFlip(true)
                        .setMaxSize(500)
                        .setPickTypes(EPickType.GALLERY, EPickType.CAMERA)
                        .setIconGravity(Gravity.CENTER)
                        .setButtonOrientation(LinearLayoutCompat.VERTICAL)
                        .setSystemDialog(true);

                PickImageDialog.build(setup).show(CreateAuditions.this);

            }
        });

        errmsg = (TextView) findViewById(R.id.errmsg);
        errmsg.setVisibility(View.GONE);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e6 = (EditText) findViewById(R.id.e6);
        e7 = (EditText) findViewById(R.id.e7);
        e8 = (EditText) findViewById(R.id.e8);
        e9 = (EditText) findViewById(R.id.e9);
        e10 = (EditText) findViewById(R.id.e10);
        e11 = (EditText) findViewById(R.id.e11);


        if(mode == 2)
        {
            postorupdate.setText("UPDATE");
            e1.setText(projName);
            e2.setText(projType);
            e3.setText(projDesc);
            e6.setText(audLocation);
            e7.setText(applnFrom);
            e8.setText(applnTo);
            e9.setText(phoneNo);
            e10.setText(audTime);
            e11.setText(audDate);
        }

        project_type_layout = (LinearLayout) findViewById(R.id.project_type_layout);

        auditionImage = BitmapFactory.decodeResource(getResources(),R.drawable.logo169);

        bn1 = (Button) findViewById(R.id.bn1);
        bn2 = (Button) findViewById(R.id.bn2);
        bn3 = (Button) findViewById(R.id.bn3);
        bn4 = (Button) findViewById(R.id.bn4);
        bn5 = (Button) findViewById(R.id.bn5);
        bn6 = (Button) findViewById(R.id.bn6);
        bn7 = (Button) findViewById(R.id.bn7);
        bn8 = (Button) findViewById(R.id.bn8);


        input_1 = (TextInputLayout) findViewById(R.id.input_1);
        input_2 = (TextInputLayout) findViewById(R.id.input_2);
        input_3 = (TextInputLayout) findViewById(R.id.input_3);
        input_6 = (TextInputLayout) findViewById(R.id.input_6);
        input_7 = (TextInputLayout) findViewById(R.id.input_7);
        input_8 = (TextInputLayout) findViewById(R.id.input_8);
        input_9 = (TextInputLayout) findViewById(R.id.input_9);
        input_10 = (TextInputLayout) findViewById(R.id.input_10);
        input_11 = (TextInputLayout) findViewById(R.id.input_11);


        e1.addTextChangedListener(filterTextWatchere1);
        e2.addTextChangedListener(filterTextWatchere2);
        e3.addTextChangedListener(filterTextWatchere3);
        e6.addTextChangedListener(filterTextWatchere6);
        e7.addTextChangedListener(filterTextWatchere7);
        e8.addTextChangedListener(filterTextWatchere8);
        e9.addTextChangedListener(filterTextWatchere9);
        e10.addTextChangedListener(filterTextWatchere10);
        e11.addTextChangedListener(filterTextWatchere11);




        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_1.setBackgroundResource(R.drawable.box);
                } else {
                    if(!e1.getText().toString().equals("")) input_1.setBackgroundResource(R.drawable.box);
                    else input_1.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_2.setBackgroundResource(R.drawable.box);
                    project_type_layout.setVisibility(View.VISIBLE);

                    bn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            e2.setText("Feature Films");
                        }
                    });

                    bn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            e2.setText("Tv Shows");
                        }
                    });

                    bn3.setOnClickListener(new View.OnClickListener() {
                         @Override
                        public void onClick(View v) {
                            e2.setText("Commercials (Ads)");
                        }
                    });

                    bn4.setOnClickListener(new View.OnClickListener() {
                         @Override
                        public void onClick(View v) {
                            e2.setText("Short Films");
                        }
                    });

                    bn5.setOnClickListener(new View.OnClickListener() {
                         @Override
                        public void onClick(View v) {
                            e2.setText("Web Series");
                        }
                    });

                    bn6.setOnClickListener(new View.OnClickListener() {
                         @Override
                        public void onClick(View v) {
                            e2.setText("Youtube Channel");
                        }
                    });

                    bn7.setOnClickListener(new View.OnClickListener() {
                         @Override
                        public void onClick(View v) {
                            e2.setText("Hosts");
                        }
                    });

                    bn8.setOnClickListener(new View.OnClickListener() {
                         @Override
                        public void onClick(View v) {
                            e2.setText("Anchor");
                        }
                    });

                } else {

                    project_type_layout.setVisibility(View.GONE);
                    if(!e2.getText().toString().equals("")) input_2.setBackgroundResource(R.drawable.box);
                    else input_2.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_3.setBackgroundResource(R.drawable.box);
                } else {
                    if(!e3.getText().toString().equals("")) input_3.setBackgroundResource(R.drawable.box);
                    else input_3.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_6.setBackgroundResource(R.drawable.box);


                    try {
                        Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(CreateAuditions.this);
                        startActivityForResult(intent, REQUEST_AUDITION_LOCATION);

                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }


                } else {
                    if(!e6.getText().toString().equals("")) input_6.setBackgroundResource(R.drawable.box);
                    else input_6.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e7.setShowSoftInputOnFocus(false);
        e7.setInputType(InputType.TYPE_NULL);
        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(AUDITION_FROM);
            }
        });
        e7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_7.setBackgroundResource(R.drawable.box);

                    showDialog(AUDITION_FROM);

                } else {
                    if(!e7.getText().toString().equals("")) input_7.setBackgroundResource(R.drawable.box);
                    else input_7.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e8.setShowSoftInputOnFocus(false);
        e8.setInputType(InputType.TYPE_NULL);
        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(AUDITION_FROM);
            }
        });
        e8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_8.setBackgroundResource(R.drawable.box);

                    showDialog(AUDITION_TO);

                } else {
                    if(!e8.getText().toString().equals("")) input_8.setBackgroundResource(R.drawable.box);
                    else input_8.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e9.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_9.setBackgroundResource(R.drawable.box);
                } else {
                    if(!e9.getText().toString().equals("")) input_9.setBackgroundResource(R.drawable.box);
                    else input_9.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e10.setShowSoftInputOnFocus(false);
        e10.setInputType(InputType.TYPE_NULL);
        e10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }
        });
        e10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_10.setBackgroundResource(R.drawable.box);

                    showDialog(TIME_DIALOG_ID);

                } else {
                    if(!e10.getText().toString().equals("")) input_10.setBackgroundResource(R.drawable.box);
                    else input_10.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e11.setShowSoftInputOnFocus(false);
        e11.setInputType(InputType.TYPE_NULL);
        e11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(AUDITION_DATE);
            }
        });
        e11.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_11.setBackgroundResource(R.drawable.box);

                    showDialog(AUDITION_DATE);

                } else {
                    if(!e11.getText().toString().equals("")) input_11.setBackgroundResource(R.drawable.box);
                    else input_11.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });



        postorupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == 2)
                    post("client/audition/update/" + itemid);

                else
                    post("client/audition/create");
            }
        });




        from_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                e7.setText(new StringBuilder().append(month+1).append("/").append(dayOfMonth).append("/").append(year));

            }
        };

        to_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                e8.setText(new StringBuilder().append(month+1).append("/").append(dayOfMonth).append("/").append(year));

            }
        };

        audition_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                e11.setText(new StringBuilder().append(month+1).append("/").append(dayOfMonth).append("/").append(year));

            }
        };

        timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

                String chr, cmin, ampm;

                if(hourOfDay > 12) { chr = String.valueOf(hourOfDay - 12); ampm = "pm"; } else { chr = String.valueOf(hourOfDay); ampm = "am"; }
                if(hourOfDay == 0) { chr = "12"; ampm = "am"; }
                if(minutes == 0) { cmin = "00"; } else { cmin = String.valueOf(minutes); }


               e10.setText(new StringBuilder().append(chr).append(":").append(cmin).append(" ").append(ampm));

            }
        };



        //image cropper
        cropResultReceiver = new CropIwaResultReceiver();
        cropResultReceiver.setListener(this);
        cropResultReceiver.register(this);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home: finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub

        switch(id){
            case AUDITION_FROM:
                return new DatePickerDialog(this, from_dateListener, year, month, day);
            case AUDITION_TO:
                return new DatePickerDialog(this, to_dateListener, year, month, day);
            case AUDITION_DATE:
                return new DatePickerDialog(this, audition_dateListener, year, month, day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener, hr, min, false);
        }

        return null;
    }



    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {

            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            //If you want the Bitmap.
            //profile_image1.setImageBitmap(r.getBitmap());

            Intent intent = new Intent(this, EditPictureforAuditions.class);
            intent.putExtra("RawImageUri", r.getUri().toString());
            //createImageFromBitmap(r.getBitmap(), "RawImage");
            loadimageprogress.setVisibility(View.VISIBLE);
            startActivityForResult(intent, REQUEST_IMAGE_LOAD);

            //Image path
            //r.getPath();

        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_LOAD) {
            if(resultCode == Activity.RESULT_OK) {
                loadimageprogress.setVisibility(View.GONE);
            }
        }


        if (requestCode == REQUEST_AUDITION_LOCATION) {
            if (resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);
                e6.setText(place.getName());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }

    }

    boolean check(EditText e) {

        if (e.getText().toString().equals(""))
        {
            e.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_box));
            return false;
        }
        else
        {
            restore(e);
            return true;
        }


    }

    public void post(String url) {
        Log.i("here","hi");
        boolean i1,i2,i3,i6,i7,i8,i9,i10,i11;
        i1=check(e1);
        i2=check(e2);
        i3=check(e3);
        i6=check(e6);
        i7=check(e7);
        i8=check(e8);
        i9=check(e9);
        i10=check(e10);
        i11=check(e11);
        if(i1&&i2&&i3&&i6&&i7&&i8&&i9&&i10&&i11)
        {


            progressbar.show();
            progressbar.setMessage("Creating audition...");
            progressbar.setCancelable(false);


            StringRequest postRequest = new StringRequest(Request.Method.POST, User.getInstance().BASE_URL + url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("audition created?", response);
                            Toast.makeText(CreateAuditions.this, response, Toast.LENGTH_SHORT).show();

                            String id = "";

                            if(mode!=2)
                             id = response;

                            else
                                id = itemid;

                            uploadImage(auditionImage,id);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("authorization", new SharedPref(getApplicationContext()).getSPData(getApplicationContext(),"jwtToken"));
                    return params;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("auditionLocation", e6.getText().toString());
                    params.put("auditionDate", e11.getText().toString());
                    params.put("auditionTime", e10.getText().toString());
                    params.put("title", e1.getText().toString());
                    params.put("projectType", e2.getText().toString());
                    params.put("description", e3.getText().toString());
                    params.put("contactNo", e9.getText().toString());
                    params.put("applicationFromDate", e7.getText().toString());
                    params.put("applicationToDate", e8.getText().toString());
                    params.put("auditionType","Audition");

                   /* try {
                       // params.put("senderName", new JSONObject(new SharedPref(getApplicationContext()).getSPData(getApplicationContext(), "userdatamain")).optString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                   /* params.put("auditionImageURL", );
                    params.put("senderProfileImage", );
                    params.put("senderName", );
                    does backend take care of this code? */

                    return params;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);

        }
        else
            err_msg_disp(1);


    }




    //cropped image

    @Override
    public void onCropSuccess(Uri croppedUri) {
        loadimageprogress.setVisibility(View.GONE);
        aud_image.setImageURI(croppedUri);

        try {
            if(croppedUri.equals("") || croppedUri.equals(null))
            {
                auditionImage = BitmapFactory.decodeResource(getResources(),R.drawable.logo169);
            }


            else
            auditionImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),croppedUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCropFailed(Throwable e) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cropResultReceiver.unregister(this);
    }








    private void uploadImage(Bitmap bitmap,String id) {

        File f = new File(getApplicationContext().getCacheDir(),"Image"+".png");

        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,byteArrayOutputStream);

        byte[] bitmapdata = byteArrayOutputStream.toByteArray();

        FileOutputStream fos;

        try {
            fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();


            postRequest(f,id);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void postRequest(File f,String id) {

        String url = User.getInstance().BASE_URL + "client/audition/uploadPhoto/" + id;
        AndroidNetworking.upload(url)
                .addMultipartFile("image",f)
                .setPriority(Priority.MEDIUM)
                .addHeaders("authorization",new SharedPref(getApplicationContext()).getSPData(getApplication(),"jwtToken"))
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {


                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(CreateAuditions.this, response, Toast.LENGTH_LONG).show();

                        if(response.equals("Successfully Uploaded Audition Image") || response.equals("Audition Successfully Updated!")) {

                            Intent intent = getIntent();
                            setResult(RESULT_OK,intent);

                            progressbar.dismiss();

                            finish();

                    }

                        //to update userdatamain in shared prefs
                        new SharedPref(getApplicationContext()).updateUserDataMain(getApplicationContext());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(CreateAuditions.this,"Error: " + anError.getErrorBody(),Toast.LENGTH_LONG).show();

                    }
                });
    }

}
