package com.example.user.mylocationmanager;

import com.google.android.gms.maps.model.LatLng;
import com.orm.SugarRecord;

/**
 * Created by user on 27/12/2017.
 */

public class Loc extends SugarRecord{

    private String place;
    private double lon;
    private double lat;


    public Loc(String place, double lon, double lat) {
        this.place = place;
        this.lat  = lat;
        this.lon = lon;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getPlace() {
        return place;
    }
}
