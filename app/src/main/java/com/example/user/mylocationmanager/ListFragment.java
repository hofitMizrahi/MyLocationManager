package com.example.user.mylocationmanager;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    static MyLocationRecyclerAdapter myLocationRecyclerAdapter;
    ArrayList<Loc> allLocations;
    DBmanager myDB;

    public ListFragment() {
    }

    public static void refreshAdapter(){
        myLocationRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        myDB = new DBmanager(getActivity());
        allLocations = new ArrayList<>();

        Cursor cursor = myDB.getCursor();

        while (cursor.moveToNext()){
            // get the movie names from database from col1 "subject"
            // and add the data to arrayList

            String locationName = cursor.getString(1);
            double lat = Double.parseDouble(cursor.getString(2));
            double lon = Double.parseDouble(cursor.getString(3));
            allLocations.add(new Loc(locationName,lat,lon));
        }

        RecyclerView recyclerView= v.findViewById(R.id.recycler_fragment_ID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        myLocationRecyclerAdapter = new MyLocationRecyclerAdapter(allLocations, getActivity());

        recyclerView.setAdapter(myLocationRecyclerAdapter);

        return v;
    }
}
