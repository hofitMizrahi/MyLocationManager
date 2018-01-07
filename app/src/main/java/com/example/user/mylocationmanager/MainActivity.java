package com.example.user.mylocationmanager;

import android.content.Intent;
import android.content.res.Configuration;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyFragmentChanger{

    MapFragment mapFragment;
    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragments();
    }

    @Override
    public void changeFragments(final Loc location) {

        if(isPortrait()) {
            mapFragment = new MapFragment();
            getFragmentManager().beginTransaction().addToBackStack("replacing").replace(R.id.main_layout, mapFragment).commit();
        }
            mapFragment.getMapAsync(new OnMapReadyCallback() {
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

        int orientation = this.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            return true;
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        setFragments();
    }

    private void setFragments(){

        listFragment = new ListFragment();
        getFragmentManager().beginTransaction().addToBackStack("replacing").replace(R.id.main_layout, listFragment).commit();

        if(!isPortrait()){

            mapFragment = new MapFragment();
            getFragmentManager().beginTransaction().addToBackStack("replacing").replace(R.id.map_land_layout, mapFragment).commit();

        }
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
        switch (item.getItemId()) {

            // alertDialog --> ask if the user wont to delete his list
            case R.id.deleteAll:
                //delete from DB
                ArrayList<Loc> loc = (ArrayList<Loc>) Loc.listAll(Loc.class);
                Loc.deleteAll(Loc.class);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
        }

        return true;
    }

    public void exitBtn(View view) {
        finish();
    }
}
