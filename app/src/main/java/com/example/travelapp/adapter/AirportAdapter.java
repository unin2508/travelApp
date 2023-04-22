package com.example.travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.travelapp.R;
import com.example.travelapp.model.Airport;

import java.util.List;

public class AirportAdapter extends BaseAdapter {

    private Context context;
    private List<Airport> airportList;

    public AirportAdapter(Context context, List<Airport> airportList) {
        this.context = context;
        this.airportList = airportList;
    }

    @Override
    public int getCount() {
        if (airportList != null){
            return airportList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return airportList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_airport,null);

        TextView locationTv = view.findViewById(R.id.locationAirport_tv);
        TextView nameTv = view.findViewById(R.id.nameAirport_tv);

        locationTv.setText(airportList.get(i).getCity());
        nameTv.setText(airportList.get(i).getName());

        return view;
    }
}
