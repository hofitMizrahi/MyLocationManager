package com.example.user.mylocationmanager;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private MyLocationRecyclerAdapter myLocationRecyclerAdapter;
    private ArrayList<Loc> allLocations;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        allLocations = (ArrayList<Loc>) Loc.listAll(Loc.class);

        RecyclerView recyclerView= v.findViewById(R.id.recycler_fragment_ID);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        myLocationRecyclerAdapter = new MyLocationRecyclerAdapter(allLocations, getActivity());

        recyclerView.setAdapter(myLocationRecyclerAdapter);

        return v;
    }
}
