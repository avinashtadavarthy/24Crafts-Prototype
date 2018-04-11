package com.twenty.four.crafts;

import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RadarView extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    boolean requestSent = false;
    final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    double currentLatitude, currentlongitude;

    String profImgURL = "";
    ArrayList<Bitmap> peopleNearbyBitmaps = new ArrayList<>();
    int i = 0;
    int size;
    JSONArray peopleNearbyURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_view);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1 * 1000);


        AndroidNetworking.initialize(getApplicationContext());
        //routeToGetPeopleNearby();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    private void routeTogetUserDetails(JSONArray peopleNearby) {


        peopleNearbyURL = peopleNearby;

        size = peopleNearby.length();

        Log.e("PeoplenearbyURLLength", peopleNearbyURL.length() + "");

        try {
            String userId = (String) peopleNearby.get(0);
            String modUserId = userId.substring(0, userId.length());
            Log.e("modUserID", modUserId);
            String url = User.getInstance().BASE_URL + "users/" + modUserId + "/profileImage.jpg";
            new ImageLoadTask(url).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //call initview on response

    }


    public void routeToGetPeopleNearby() {
        String url = "";

        try {
            if (new JSONObject(getSPData("userdatamain")).optString("isClient").equals("false"))
                url = "user/geolocation/nearby";

            else
                url = "client/geolocation/nearby";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.get(User.getInstance().BASE_URL + url)
                .setPriority(Priority.MEDIUM)
                .addHeaders("authorization", getSPData("jwtToken"))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray nearby = response.optJSONArray("nearby");
                        Log.e("PeopleNearby", nearby.toString());
                        Log.e("PeopleNearbyLength", nearby.length() + "");

                        routeTogetUserDetails(nearby);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }


    private void initView() {


        for(int i=0;i<peopleNearbyBitmaps.size();i++)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            peopleNearbyBitmaps.get(i).compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] b = baos.toByteArray();

            String encoded = Base64.encodeToString(b,Base64.DEFAULT);
            storeSPData("person"+i,encoded);
        }



        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putInt("nearbySize", peopleNearbyBitmaps.size());
        mUserEditor.commit();


        RadarViewClass mRadarView = (RadarViewClass) findViewById(R.id.radar_view);
        mRadarView.setSearching(true);

        Log.e("bitmapArraySize", peopleNearbyBitmaps.size() + "");

        for (int i = 0; i < peopleNearbyBitmaps.size(); i++)
            mRadarView.addPoint();

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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if(location == null)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
        }

        else
        {
            currentLatitude = location.getLatitude();
            currentlongitude = location.getLongitude();
            locationOfUser(currentLatitude,currentlongitude);

            Log.e("Location",currentLatitude + " " + currentlongitude);





        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        if(connectionResult.hasResolution())
        {
            try {
                connectionResult.startResolutionForResult(this,CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }


        else
        {
            Log.e("ErrorPeopleNearby","Location Services COnnection failed " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        currentlongitude = location.getLongitude();
        currentLatitude = location.getLatitude();

        if(requestSent == false)
        {
            locationOfUser(currentLatitude,currentlongitude);
        }

    }



    private void locationOfUser(double currentLatitude,double currentlongitude) {




        //to get the user data
        String newurl = User.getInstance().BASE_URL + "user/geolocation/update/" + currentLatitude + "/" + currentlongitude;


        AndroidNetworking.get(newurl)
                .addHeaders("authorization",getSPData("jwtToken"))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("locationUpdateResponse",response);
                        requestSent = true;
                        routeToGetPeopleNearby();
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.e("locationUpdateResponse","Error");
                    }
                });
    }



    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;


        public ImageLoadTask(String url) {
            this.url = url;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Bitmap doInBackground(Void... params) {

                try {
                    URL urlConnection = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) urlConnection
                            .openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    return myBitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            if(result!=null)
                peopleNearbyBitmaps.add(result);

            else
                peopleNearbyBitmaps.add(Bitmap.createBitmap(BitmapFactory.decodeResource(
                        getApplicationContext().getResources(), R.drawable.taigamod2)));


            Log.e("bitmapArraySize:onPost",peopleNearbyBitmaps.size()+"");

            i++;

            if(i == peopleNearbyURL.length())
            initView();

            else {
                try {
                    String userId = (String) peopleNearbyURL.get(i);
                    String modUserId = userId.substring(0, userId.length());
                    Log.e("modUserID", modUserId);
                    String url = User.getInstance().BASE_URL + "users/" + modUserId + "/profileImage.jpg";
                    new ImageLoadTask(url).execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }

    }






}
