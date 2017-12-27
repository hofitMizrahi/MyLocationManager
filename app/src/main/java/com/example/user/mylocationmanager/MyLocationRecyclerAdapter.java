package com.example.user.mylocationmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 27/12/2017.
 */

class MyLocationRecyclerAdapter extends RecyclerView.Adapter <MyLocationRecyclerAdapter.MyViewHoder> {
    ArrayList<Loc> allLocations;
    Context context;

    public MyLocationRecyclerAdapter(ArrayList<Loc> allLocations, Context context) {
        this.allLocations = allLocations;
        this.context = context;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewFromXML = LayoutInflater.from(context).inflate(R.layout.single_list_item, null);
        MyViewHoder singleItem = new MyViewHoder(viewFromXML);
        return singleItem;
    }

    @Override
    public void onBindViewHolder(MyViewHoder singleItem, int position) {
        Loc currentCity = allLocations.get(position);

        singleItem.bindMyCityData(currentCity);

    }

    @Override
    public int getItemCount() {
        return allLocations.size();
    }

    public class MyViewHoder extends RecyclerView.ViewHolder {
        View itemView;

        public MyViewHoder(View itemView) {
            super(itemView);
            this.itemView = itemView;

        }

        public void bindMyCityData(final Loc currentCity) {
            final TextView textView = itemView.findViewById(R.id.location_name_TV);
            textView.setText(currentCity.getPlace());

//            ImageView imageView =itemView.findViewById(R.id.cityIV);
//            imageView.setImageResource(currentCity.res);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    MyCitychanger citychanger= (MyCitychanger) context;
//                    citychanger.changeFragments(currentCity);
                    MyFragmentChanger citychanger= (MyFragmentChanger) context;
                    citychanger.changeFragments(currentCity);
                }
            });
        }
    }
}
