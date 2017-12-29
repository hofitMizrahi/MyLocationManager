package com.example.user.mylocationmanager;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements MyFragmentChanger{

    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ifPortrait()){
            RecyclerFragment listFragment= new RecyclerFragment();
            getFragmentManager().beginTransaction().add(R.id.list_layout,listFragment).commit();

        }else{

            MapFragment cityMapFragmnet= new MapFragment();
            getFragmentManager().beginTransaction().add(R.id.map_land_layout,cityMapFragmnet).commit();

        }
    }

    @Override
    public void changeFragments(Loc location) {

        MapFragment cityMapFragmnet= new MapFragment();

        getFragmentManager().beginTransaction().addToBackStack("replacing").replace(R.id.list_layout,cityMapFragmnet ).commit();
        cityMapFragmnet.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                LatLng latLng = new LatLng(32.08015, 34.78714);
                //update location and zoom 0 is the most far
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng , 12));

            }
        });
    }

    public boolean ifPortrait(){

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
}
