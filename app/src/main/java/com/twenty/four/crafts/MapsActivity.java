package com.twenty.four.crafts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 100;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 200;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 300;
    GoogleMap mMap;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    ImageButton location;
    TextView text;
    double latitude, longitude;
    LocationManager locationManager;
    String searchedPlace = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);


        User.getInstance().navbarpos = 0;

        permissionRequest();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        PlaceAutocompleteFragment places= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Toast.makeText(getApplicationContext(),place.getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        currentLocation();


    }

    public void currentLocation()
    {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Log.e("isProviderEnablednp",locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)+"");
        Log.e("isProviderEnabledgps",locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)+"");

        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    String str = "";
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude,longitude);
                    Log.e("listener",latitude + " " + longitude);

                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);

                        str = addressList.get(0).getLocality() + addressList.get(0).getCountryName();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mMap.addMarker(new MarkerOptions().position(latLng).title(str).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.2f));
                }



                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                    Log.e("listener","statuschanged");
                }

                @Override
                public void onProviderEnabled(String s) {

                    Log.e("listener","providerenabled");
                }

                @Override
                public void onProviderDisabled(String s) {

                    Log.e("listener","providerdisabled");
                }
            });
        }




        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    String str = "";
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    Log.e("listener",latitude + " " + longitude);
                    LatLng latLng = new LatLng(latitude,longitude);

                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);

                        str = addressList.get(0).getLocality() + addressList.get(0).getCountryName();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.2f));
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {
                    Log.e("listener","Statuschanged");
                }

                @Override
                public void onProviderEnabled(String s) {
                    Log.e("listener","providerenabled");
                }

                @Override
                public void onProviderDisabled(String s) {

                    Log.e("listener","providerdisabled");
                }
            });
        }

    }


    public void permissionRequest()
    {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, do your work....
                    currentLocation();
                } else {
                    // permission denied
                    // Disable the functionality that depends on this permission.
                    Log.e("tag","Permission denied");
                }
                return;
            }

            // other 'case' statements for other permssions
        }
    }

   /* public void placesAutocomplete(View view) {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("TAG", "Place: " + place.getName());
                String location = place.getName().toString();
                text.setText(location);
                searchPlace(location);

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i("TAG", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        LatLng chennai = new LatLng(13.0827, 80.2707);
        LatLng randomplace = new LatLng(13.0253, 75.4758);

        LatLng latLng;
        double[][] multi = new double[][]{
                {10.79,78.70},
                {9.9252,78.1198},
                {11.0168,76.9558},{11.6643,78.1460},{10.8505,76.2711},{15.91229,79.74}


        };

        for(int i=0;i<6;i++)
        {
            latLng = new LatLng(multi[i][0],multi[i][1]);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        }


    }


    public void searchPlace(String strAddress)
    {

        Geocoder coder = new Geocoder(getApplicationContext());
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return;
            }
            Address location = address.get(0);
            //location.getLatitude();
            //location.getLongitude();

            Log.e("maps",location.getLatitude()+ "  " + location.getLongitude());
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        MarkerOptions myMarker = new MarkerOptions().position(p1).title("Marker");
        mMap.addMarker(myMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p1,10.2f));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(getApplicationContext(),ProfileView.class)
                               .putExtra("thisistogetback", "do nothing")
                               .putExtra("fromwhom", "do nothing");
        startActivity(intent);

        return true;
    }
}
