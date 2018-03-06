package com.twenty.four.crafts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class PeopleNearbyFragment extends android.support.v4.app.Fragment {

    View myView;

    String jwtToken = "";
    ImageView imageView;
    CircleImageView imageView1;
    CircleImageView imageView2;
    CircleImageView imageView3;
    CircleImageView imageView4;
    CircleImageView imageView5;
    Random random;
    ZoomableRelativeLayout mainLayout;
    ScaleGestureDetector scaleGestureDetector;
    int a;
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_people_nearby, container, false);

        //locationOfUser();

        getActivity().setTitle("People Nearby");
        User.getInstance().navbarposclient = 0;

        TextView textView = (TextView) myView.findViewById(R.id.textView2);

        mainLayout = (ZoomableRelativeLayout) myView.findViewById(R.id.mainLayout);
        scaleGestureDetector = new ScaleGestureDetector(getActivity(),new onPinchListener(mainLayout));

        imageView = (ImageView) myView.findViewById(R.id.IMG);

        random = new Random();
        a = random.nextInt(6)+1;
        button = (Button) myView.findViewById(R.id.button);
        counter(a);

        imageView1 = (CircleImageView) myView.findViewById(R.id.imageView3);
        imageView2 = (CircleImageView) myView.findViewById(R.id.imageView4);
        imageView3 = (CircleImageView) myView.findViewById(R.id.imageView5);
        imageView4 = (CircleImageView) myView.findViewById(R.id.imageView6);
        imageView5 = (CircleImageView) myView.findViewById(R.id.imageView7);

        counter(a+1);

        counter(a-1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                imageView1.setVisibility(View.INVISIBLE);
                imageView2.setVisibility(View.INVISIBLE);
                imageView3.setVisibility(View.INVISIBLE);
                imageView4.setVisibility(View.INVISIBLE);
                imageView5.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);

                a = random.nextInt(6)+1;
                counter(a);
                counter(a+1);
                counter(a-1);
            }
        });



        mainLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });
        
        
        return myView;
    }


    public void counter(int a)
    {


        final ProgressBar progressBar = (ProgressBar) myView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);



        switch (a)
        {
            case 1:

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        imageView1.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn).duration(1000).repeat(1).playOn(myView.findViewById(R.id.imageView3));
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }
                }, 5000);

                break;

            case 2:
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        imageView2.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn).duration(1000).repeat(1).playOn(imageView2);
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }
                }, 5000);

                break;

            case 3:
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        imageView3.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn).duration(1000).repeat(1).playOn(imageView3);
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }
                }, 5000);

                break;

            case 4:
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        imageView4.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn).duration(1000).repeat(1).playOn(imageView4);
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }
                }, 5000);

                break;

            case 5:
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        imageView5.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn).duration(1000).repeat(1).playOn(imageView5);
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }
                }, 5000);

                break;

            default:

                //Toast.makeText(this,"No Searches Nearby",Toast.LENGTH_LONG).show();
                //button.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                break;
            
        }

    }





    /*private void locationOfUser() {




                        //to get the user data
        String newurl = User.getInstance().BASE_URL + "user/geolocation/update/100/100";


        jwtToken = getSPData("jwtToken");

        Log.e("jwtToken",jwtToken);


                        StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {



                                try {


                                    JSONObject obj = new JSONObject(response);
                                    String isClient = obj.optString("isClient");

                                    if(isClient.equals("true")) {

                                        Log.e("response",response);;

                                    } else {

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }){
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {

                                Map<String, String> params = new HashMap<String, String>();

                                params.put("authorization", jwtToken);

                                return params;
                            }
                        };

                        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(getRequest);
                        //to get the user data
                    }

*/





    //Shared Preferences
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = getActivity().getApplicationContext().getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(String key) {

        SharedPreferences mUserData = getActivity().getApplicationContext().getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }












}
