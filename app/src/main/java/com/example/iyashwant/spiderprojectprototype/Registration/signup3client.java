package com.example.iyashwant.spiderprojectprototype.Registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.iyashwant.spiderprojectprototype.ProfileView;
import com.example.iyashwant.spiderprojectprototype.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup3client extends AppCompatActivity {

    String userResponseToken;
    String SITE_KEY="6LdycDsUAAAAAFXNFTd5ra6Kt16ws6xGWqSg6Kes";
    String SECRET_KEY="6LdycDsUAAAAABovRryWV3ACDefaICVwxC68s9EG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3client);

        final LinearLayout castingagent = (LinearLayout) findViewById(R.id.casting_agent);
        castingagent.setVisibility(View.GONE);
        RadioGroup castingagent_1radio = (RadioGroup) findViewById(R.id.casting_agent_1radio);
        RadioButton castingagent_1radioyes = (RadioButton) findViewById(R.id.casting_agent_1radioyes);
        final EditText castingagent_11 = (EditText) findViewById(R.id.casting_agent_11);
        castingagent_11.setVisibility(View.GONE);



        final LinearLayout codirector = (LinearLayout) findViewById(R.id.codirector);
        codirector.setVisibility(View.GONE);
        RadioGroup codirector_1radio = (RadioGroup) findViewById(R.id.codirector_1radio);
        RadioButton codirector_1radioyes = (RadioButton) findViewById(R.id.codirector_1radioyes);
        final EditText codirector_11 = (EditText) findViewById(R.id.codirector_11);
        codirector_11.setVisibility(View.GONE);



        final LinearLayout coproducer = (LinearLayout) findViewById(R.id.coproducer);
        coproducer.setVisibility(View.GONE);
        EditText coproducer_2 = (EditText) findViewById(R.id.coproducer_2);
        RadioGroup coproducer_1radio = (RadioGroup) findViewById(R.id.coproducer_1radio);
        RadioButton coproducer_1radioyes = (RadioButton) findViewById(R.id.coproducer_1radioyes);
        final EditText coproducer_11 = (EditText) findViewById(R.id.coproducer_11);
        coproducer_11.setVisibility(View.GONE);




        final LinearLayout director = (LinearLayout) findViewById(R.id.director);
        director.setVisibility(View.GONE);
        RadioGroup director_1radio = (RadioGroup) findViewById(R.id.director_1radio);
        RadioButton director_1radioyes = (RadioButton) findViewById(R.id.director_1radioyes);
        final EditText director_11 = (EditText) findViewById(R.id.director_11);
        director_11.setVisibility(View.GONE);




        final LinearLayout asstdirector = (LinearLayout) findViewById(R.id.asstdirector);
        asstdirector.setVisibility(View.GONE);
        RadioGroup asstdirector_1radio = (RadioGroup) findViewById(R.id.asstdirector_1radio);
        RadioButton asstdirector_1radioyes = (RadioButton) findViewById(R.id.asstdirector_1radioyes);
        final EditText asstdirector_11 = (EditText) findViewById(R.id.asstdirector_11);
        asstdirector_11.setVisibility(View.GONE);




        final LinearLayout directoraudit = (LinearLayout) findViewById(R.id.directoraudit);
        directoraudit.setVisibility(View.GONE);
        RadioGroup directoraudit_1radio = (RadioGroup) findViewById(R.id.directoraudit_1radio);
        RadioButton directoraudit_1radioyes = (RadioButton) findViewById(R.id.directoraudit_1radioyes);
        final EditText directoraudit_11 = (EditText) findViewById(R.id.directoraudit_11);
        directoraudit_11.setVisibility(View.GONE);




        final LinearLayout execproducer = (LinearLayout) findViewById(R.id.execproducer);
        execproducer.setVisibility(View.GONE);
        EditText execproducer_2 = (EditText) findViewById(R.id.execproducer_2);
        RadioGroup execproducer_1radio = (RadioGroup) findViewById(R.id.execproducer_1radio);
        RadioButton execproducer_1radioyes = (RadioButton) findViewById(R.id.execproducer_1radioyes);
        final EditText execproducer_11 = (EditText) findViewById(R.id.execproducer_11);
        execproducer_11.setVisibility(View.GONE);




        final LinearLayout modelcoor = (LinearLayout) findViewById(R.id.modelcoor);
        modelcoor.setVisibility(View.GONE);
        RadioGroup modelcoor_1radio = (RadioGroup) findViewById(R.id.modelcoor_1radio);
        RadioButton moedlcoor_1radioyes = (RadioButton) findViewById(R.id.modelcoor_1radioyes);
        final EditText modelcoor_11 = (EditText) findViewById(R.id.modelcoor_11);
        modelcoor_11.setVisibility(View.GONE);



        final LinearLayout producer = (LinearLayout) findViewById(R.id.producer);
        producer.setVisibility(View.GONE);
        EditText producer_2 = (EditText) findViewById(R.id.producer_2);
        RadioGroup producer_1radio = (RadioGroup) findViewById(R.id.producer_1radio);
        RadioButton producer_1radioyes = (RadioButton) findViewById(R.id.producer_1radioyes);
        final EditText producer_11 = (EditText) findViewById(R.id.producer_11);
        producer_11.setVisibility(View.GONE);




        final LinearLayout prodhouseman = (LinearLayout) findViewById(R.id.prodhouseman);
        prodhouseman.setVisibility(View.GONE);
        EditText prodhouseman_2 = (EditText) findViewById(R.id.prodhouseman_2);
        RadioGroup prodhouseman_1radio = (RadioGroup) findViewById(R.id.prodhouseman_1radio);
        RadioButton prodhouseman_1radioyes = (RadioButton) findViewById(R.id.prodhouseman_1radioyes);
        final EditText prodhouseman_11 = (EditText) findViewById(R.id.prodhouseman_11);
        prodhouseman_11.setVisibility(View.GONE);







        final Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        final String craft = bundle.getString("craft");

        switch(craft)
        {
            case "Casting Agent": castingagent.setVisibility(View.VISIBLE); break;
            case "Co-Director": codirector.setVisibility(View.VISIBLE); break;
            case "Co-Producer": coproducer.setVisibility(View.VISIBLE); break;
            case "Director": director.setVisibility(View.VISIBLE); break;
            case "Director Assistant": asstdirector.setVisibility(View.VISIBLE); break;
            case "Director Audition": directoraudit.setVisibility(View.VISIBLE); break;
            case "Executive Producer": execproducer.setVisibility(View.VISIBLE); break;
            case "Model Coordinator": modelcoor.setVisibility(View.VISIBLE); break;
            case "Producer": producer.setVisibility(View.VISIBLE); break;
            case "Production House Manager": prodhouseman.setVisibility(View.VISIBLE); break;
        }


        //castingagent
        castingagent_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.casting_agent_1radioyes)
                    castingagent_11.setVisibility(View.VISIBLE);
                else
                    castingagent_11.setVisibility(View.GONE);
            }
        });


        //codirector
        codirector_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.codirector_1radioyes)
                    codirector_11.setVisibility(View.VISIBLE);
                else
                    codirector_11.setVisibility(View.GONE);
            }
        });


        //coproducer
        coproducer_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.coproducer_1radioyes)
                    coproducer_11.setVisibility(View.VISIBLE);
                else
                    coproducer_11.setVisibility(View.GONE);
            }
        });


        //director
        director_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.director_1radioyes)
                    director_11.setVisibility(View.VISIBLE);
                else
                    director_11.setVisibility(View.GONE);
            }
        });


        //director assistant
        asstdirector_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.asstdirector_1radioyes)
                    asstdirector_11.setVisibility(View.VISIBLE);
                else
                    asstdirector_11.setVisibility(View.GONE);
            }
        });


        //director audition
        directoraudit_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.directoraudit_1radioyes)
                    directoraudit_11.setVisibility(View.VISIBLE);
                else
                    directoraudit_11.setVisibility(View.GONE);
            }
        });


        //executive producer
        execproducer_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.execproducer_1radioyes){
                    execproducer_11.setVisibility(View.VISIBLE);
                }
                else{
                    execproducer_11.setVisibility(View.GONE);
                }

            }
        });


        //modelcoordinator
        modelcoor_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.modelcoor_1radioyes)
                    modelcoor_11.setVisibility(View.VISIBLE);
                else
                    modelcoor_11.setVisibility(View.GONE);
            }
        });


        //producer
        producer_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.producer_1radioyes)
                    producer_11.setVisibility(View.VISIBLE);
                else
                    producer_11.setVisibility(View.GONE);
            }
        });



        //prodhouseman
        prodhouseman_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.prodhouseman_1radioyes)
                    prodhouseman_11.setVisibility(View.VISIBLE);
                else
                    prodhouseman_11.setVisibility(View.GONE);
            }
        });

    }

    public void captcha(View v)
    {
//                Intent next = new Intent(getApplicationContext(),ProfileView.class);
//                startActivity(next);

        SafetyNet.getClient(this).verifyWithRecaptcha(SITE_KEY)
                .addOnSuccessListener(this,
                        new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                // Indicates communication with reCAPTCHA service was
                                // successful.
                                userResponseToken = response.getTokenResult();
                                if (!userResponseToken.isEmpty()) {
                                    // Validate the user response token using the
                                    // reCAPTCHA siteverify API.
                                    sendRequest();
                                }
                            }
                        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the
                            // reCAPTCHA service. Refer to the status code to
                            // handle the error appropriately.
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            Log.d("TAG", "Error: " + CommonStatusCodes
                                    .getStatusCodeString(statusCode));
                        } else {
                            // A different, unknown type of error occurred.
                            Log.d("TAG", "Error: " + e.getMessage());
                        }
                    }
                });

    }







    public void sendRequest()
    {
        String url = "https://www.google.com/recaptcha/api/siteverify";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            //Toast.makeText(MainActivity.this, obj.getString("success"), Toast.LENGTH_LONG).show();
                            if (obj.getString("success").equals("true")){
                                moveNewActivity();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("secret",SECRET_KEY);
                params.put("response", userResponseToken);
                return params;
            }
        };
        AppController.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void moveNewActivity(){
        Intent intent = new Intent(this, ProfileView.class);
        startActivity(intent);
        finish();
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