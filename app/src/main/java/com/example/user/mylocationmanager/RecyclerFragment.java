package com.example.user.mylocationmanager;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {

    public RecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        ArrayList<Loc> allLocations = new ArrayList<>();
        allLocations.add(new Loc("Paris"));
        allLocations.add(new Loc("israel"));

        RecyclerView recyclerView= v.findViewById(R.id.recycler_ID);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        MyLocationRecyclerAdapter MyLocationRecyclerAdapter = new MyLocationRecyclerAdapter(allLocations, getActivity());

        recyclerView.setAdapter(MyLocationRecyclerAdapter);

        return v;
    }

}
