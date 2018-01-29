package com.twenty.four.crafts;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.steelkiwi.cropiwa.image.CropIwaResultReceiver;
import com.twenty.four.crafts.auditions.Audition;
import com.twenty.four.crafts.registration.EditPictureActivity;
import com.twenty.four.crafts.registration.signup;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateAuditions extends AppCompatActivity implements IPickResult, CropIwaResultReceiver.Listener {

    static final int REQUEST_SHOOT_LOCATION = 1000, REQUEST_AUDITION_LOCATION = 2000, AUDITION_FROM=100, AUDITION_TO=200;
    static final int TIME_DIALOG_ID = 1111;
    static final int REQUEST_IMAGE_LOAD = 9999;

    ImageView aud_image;
    CropIwaResultReceiver cropResultReceiver;
    ProgressBar loadimageprogress;
    CircleImageView edit_aud_image;
    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10;
    TextInputLayout input_1, input_2, input_3, input_4, input_5, input_6, input_7, input_8, input_9, input_10;
    LinearLayout project_type_layout;
    Button bn1, bn2, bn3, bn4, bn5, bn6, bn7, bn8;


    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int hr = cal.get(Calendar.HOUR_OF_DAY);
    int min = cal.get(Calendar.MINUTE);
    DatePickerDialog.OnDateSetListener from_dateListener, to_dateListener;
    TimePickerDialog.OnTimeSetListener timePickerListener;

    TextView errmsg;
    Audition c = new Audition();
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
    private TextWatcher filterTextWatchere4 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e4);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere5 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e5);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_auditions);


        getSupportActionBar().setTitle("Create an Audition");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        aud_image = (ImageView) findViewById(R.id.aud_image);
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
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        e6 = (EditText) findViewById(R.id.e6);
        e7 = (EditText) findViewById(R.id.e7);
        e8 = (EditText) findViewById(R.id.e8);
        e9 = (EditText) findViewById(R.id.e9);
        e10 = (EditText) findViewById(R.id.e10);

        project_type_layout = (LinearLayout) findViewById(R.id.project_type_layout);

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
        input_4 = (TextInputLayout) findViewById(R.id.input_4);
        input_5 = (TextInputLayout) findViewById(R.id.input_5);
        input_6 = (TextInputLayout) findViewById(R.id.input_6);
        input_7 = (TextInputLayout) findViewById(R.id.input_7);
        input_8 = (TextInputLayout) findViewById(R.id.input_8);
        input_9 = (TextInputLayout) findViewById(R.id.input_9);
        input_10 = (TextInputLayout) findViewById(R.id.input_10);


        e1.addTextChangedListener(filterTextWatchere1);
        e2.addTextChangedListener(filterTextWatchere2);
        e3.addTextChangedListener(filterTextWatchere3);
        e4.addTextChangedListener(filterTextWatchere4);
        e5.addTextChangedListener(filterTextWatchere5);
        e6.addTextChangedListener(filterTextWatchere6);
        e7.addTextChangedListener(filterTextWatchere7);
        e8.addTextChangedListener(filterTextWatchere8);
        e9.addTextChangedListener(filterTextWatchere9);




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


        e4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_4.setBackgroundResource(R.drawable.box);
                } else {
                    if(!e4.getText().toString().equals("")) input_4.setBackgroundResource(R.drawable.box);
                    else input_4.setBackgroundColor(Color.parseColor("#00ffffff"));
                }
            }
        });


        e5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    input_5.setBackgroundResource(R.drawable.box);

                    try {
                        Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(CreateAuditions.this);
                        startActivityForResult(intent, REQUEST_SHOOT_LOCATION);

                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }


                } else {
                    if(!e5.getText().toString().equals("")) input_5.setBackgroundResource(R.drawable.box);
                    else input_5.setBackgroundColor(Color.parseColor("#00ffffff"));
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




        from_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                e7.setText(new StringBuilder().append(dayOfMonth).append("/").append(month+1).append("/").append(year));

            }
        };

        to_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                e8.setText(new StringBuilder().append(dayOfMonth).append("/").append(month+1).append("/").append(year));

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
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub

        switch(id){
            case AUDITION_FROM:
                return new DatePickerDialog(this, from_dateListener, year, month, day);
            case AUDITION_TO:
                return new DatePickerDialog(this, to_dateListener, year, month, day);
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

        if (requestCode == REQUEST_SHOOT_LOCATION) {
            if (resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);
                e5.setText(place.getName());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }


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

    public void post(View view) {
        Log.i("here","hi");
        boolean i1,i2,i3,i4,i5,i6,i7,i8,i9;
        i1=check(e1);
        i2=check(e2);
        i3=check(e3);
        i4=check(e4);
        i5=check(e5);
        i6=check(e6);
        i7=check(e7);
        i8=check(e8);
        i9=check(e9);
        if(i1&&i2&&i3&&i4&&i5&&i6&&i7&&i8&&i9)
        {
            Toast.makeText(getApplicationContext(), "Posted!",
                    Toast.LENGTH_SHORT).show();//post it
            c.setProj_name(e1.getText().toString());
            c.setProj_type(e2.getText().toString());
            c.setProj_desc(e3.getText().toString());
            c.setProj_features(e4.getText().toString());
            c.setProj_shotloc(e5.getText().toString());
            c.setProj_audiloc(e6.getText().toString());
            c.setProj_validfrom(e7.getText().toString());
            c.setProj_validto(e8.getText().toString());
            c.setProj_contact(e9.getText().toString());
            Log.i("Details:",c.getProj_audiloc()+c.getProj_contact()+c.getProj_desc()+c.getProj_features());
            finish();
        }
        else
            err_msg_disp(1);


    }




    //cropped image

    @Override
    public void onCropSuccess(Uri croppedUri) {
        loadimageprogress.setVisibility(View.GONE);
        aud_image.setImageURI(croppedUri);
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
}
