package com.example.travelapp.service;

import android.content.Context;

import com.example.travelapp.model.Airport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AirportDao {
    private ArrayList<Airport> airportArrayList;
    public AirportDao(Context context){
        try{
            Gson gson = new Gson();
            InputStream inputStream = context.getAssets().open("airports.json");
            byte [] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            String jsonString = new String(data,"UTF-8");
            airportArrayList = gson.fromJson(jsonString, new TypeToken<List<Airport>>() {}.getType());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Airport> getAirportArrayList() {
        return airportArrayList;
    }

    public void setAirportArrayList(ArrayList<Airport> airportArrayList) {
        this.airportArrayList = airportArrayList;
    }

    public String getAirportNameByCode(String code){
        for (Airport airport :airportArrayList){
            if (airport.getIata_code().equals(code)){
                return  airport.getName();
            }
        }
        return null;
    }
}
