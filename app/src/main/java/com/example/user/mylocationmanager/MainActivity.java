package com.example.user.mylocationmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orm.SugarDb;

public class MainActivity extends AppCompatActivity implements MyFragmentChanger{

    DBmanager myDB;
    int orientation;
    MapFragment cityMapFragmnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DBmanager(this);

        if(isPortrait()){
            ListFragment listFragment= new ListFragment();
            getFragmentManager().beginTransaction().add(R.id.list_layout,listFragment).commit();

        }else{

            ListFragment listFragment= new ListFragment();
            getFragmentManager().beginTransaction().add(R.id.list_layout,listFragment).commit();

            cityMapFragmnet= new MapFragment();
            getFragmentManager().beginTransaction().add(R.id.map_land_layout,cityMapFragmnet).commit();

        }
    }

    @Override
    public void changeFragments(final Loc location) {

        if(isPortrait()) {
            cityMapFragmnet= new MapFragment();
            getFragmentManager().beginTransaction().addToBackStack("replacing").replace(R.id.list_layout, cityMapFragmnet).commit();
        }
            cityMapFragmnet.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    LatLng latLng = new LatLng(location.getLon(),location.getLat());
                    //update location and zoom 0 is the most far
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(location.getPlace()));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 10));

                }
            });

    }

    // adding new location
    public void addNewLocationBtn(View view){

        Intent intent = new Intent(MainActivity.this, AddLocation.class);
        startActivity(intent);
    }

    // check the orientation of the screen
    public boolean isPortrait(){

        orientation = this.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            Toast.makeText(this, "Portrait", Toast.LENGTH_SHORT).show();
            return true;
        }else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Toast.makeText(this, "Landscape", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    //create the menu and inflate by main_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return MainActivity.super.onCreateOptionsMenu(menu);
    }

    // click on item on the menu method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // click on Delete All
        switch (item.getItemId()){

            // alertDialog --> ask if the user wont to delete his list
            case R.id.deleteAll:
                        myDB.deleteAll(); // delete from DB
                return true;
            // user click to EXIT from this App
            case R.id.exit:
                finish();
                return true;
        }
        return true;
    }
}
