package com.example.user.mylocationmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements MyFragmentChanger{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerFragment myListFragment= new RecyclerFragment();
        getFragmentManager().beginTransaction().add(R.id.activity_choose,myListFragment ).commit();
    }

    @Override
    public void changeFragments(Loc location) {

        MapFragment cityMapFragmnet= new MapFragment();

        getFragmentManager().beginTransaction().addToBackStack("replacing").replace(R.id.activity_choose,cityMapFragmnet ).commit();
        cityMapFragmnet.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                LatLng latLng = new LatLng(586, 8459);
                //update location and zoom 0 is the most far
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng , 12));

            }
        });
    }
}
