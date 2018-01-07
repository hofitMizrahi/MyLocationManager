package com.example.user.mylocationmanager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class AddLocation extends AppCompatActivity implements LocationListener{

    LocationManager locationManager;


    MapFragment mapFragment;
    EditText userAddressET;
    LatLng latLng;
    String userAddress;
    List<Address> listAddress;
    Address myAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this,
//                        Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED)
//        {
//
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 15);
//        }else
//        {
//            getLocation();
//        }
//
//        checkPermissions();

        //set the map fragment to the layout
        mapFragment = new MapFragment();
        getFragmentManager().beginTransaction().add(R.id.map_layout, mapFragment).commit();

        userAddressET = findViewById(R.id.newAddrees_ET);
        Button search = (Button) findViewById(R.id.search_address_IV);
        Button saveAddressBtn = (Button) findViewById(R.id.save_Address_Btn);

        saveAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getAddressIfLocationFound()){

                    myAddress = listAddress.get(0);
                    Loc loc = new Loc(myAddress.getAddressLine(0), myAddress.getLatitude(), myAddress.getLongitude());
                    loc.save();

                    Intent intent = new Intent(AddLocation.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });


        //get the address by user name
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getAddressIfLocationFound();
            }
        });
    }

    private void checkPermissions() {
    }

    //check if found a address on the map that mach to the user data to search
    private boolean getAddressIfLocationFound(){

        userAddress = userAddressET.getText().toString();

            Geocoder geocoder = new Geocoder(AddLocation.this);
            try {
                listAddress = geocoder.getFromLocationName(userAddress, 1);

                if(listAddress.size() != 0){

                   // userAddressET.setText(myAddress.getAddressLine(0));
                    setMap();
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ee){
                ee.printStackTrace();
            }

            Toast.makeText(AddLocation.this, "no city found", Toast.LENGTH_LONG).show();

        return false;
    }

    //set the address on the map with marker
    private void setMap(){

        Address myAddress = listAddress.get(0);

        latLng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLngMap) {

                        googleMap.addMarker(new MarkerOptions().position(latLngMap).title("marker"));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngMap , 10));

                    }
                });

                //update location and zoom 0 is the most far
                googleMap.addMarker(new MarkerOptions().position(latLng).title(userAddress));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 10));

            }
        });
    }

    public void getMyLocation(View view) {


    }



    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void getLocation() {


    }
}
