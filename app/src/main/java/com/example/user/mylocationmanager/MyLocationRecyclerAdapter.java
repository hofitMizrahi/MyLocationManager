package com.example.user.mylocationmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 27/12/2017.
 */

public class MyLocationRecyclerAdapter extends RecyclerView.Adapter <MyLocationRecyclerAdapter.myViewHolder> {

    ArrayList<Loc> allLocations;
    Context context;

    public MyLocationRecyclerAdapter(ArrayList<Loc> allLocations, Context context) {
        this.allLocations = allLocations;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewFromXML = LayoutInflater.from(context).inflate(R.layout.single_list_item, null);
        viewFromXML.setClipToOutline(true);

        myViewHolder singleItem = new myViewHolder(viewFromXML);
        return singleItem;

    }

    @Override
    public void onBindViewHolder(myViewHolder singleItem, int position) {
        Loc currentCity = allLocations.get(position);

        singleItem.bindMyCityData(currentCity);

    }

    @Override
    public int getItemCount() {
        return allLocations.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        public myViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

        }

        public void bindMyCityData(final Loc currentLocation) {
            final TextView textView = itemView.findViewById(R.id.location_name_TV);
            textView.setText(currentLocation.getPlace());

//            Button mapBtn = itemView.findViewById(R.id.map_Btn);
//            mapBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Log.i("map", "image map click");
//                }
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Replaces fragment to mapFragment and displays the location by the name of the place you clicked
                    MyFragmentChanger cityChanger = (MyFragmentChanger) context;
                    cityChanger.changeFragments(currentLocation);
                }
            });

//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//
//                    new DBmanager(context).deleteItem(currentLocation.getPlace());
//
//                    return true;
//                }
//            });
        }
    }
}
