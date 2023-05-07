package com.example.travelapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.travelapp.R;
import com.example.travelapp.adapter.AirportAdapter;
import com.example.travelapp.model.Airport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SelectAirPortActivity extends AppCompatActivity {
    private ListView listView;

    private List<Airport> airportArrayList;
    private Button cancleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_air_port);
        Intent intent = getIntent();
        Integer type = intent.getIntExtra("type",-1);
        cancleButton = findViewById(R.id.cancel_search_btn);
        cancleButton.setOnClickListener(view -> {finish();});
        Gson gson = new Gson();
        try{
            InputStream inputStream = getAssets().open("airports.json");
            byte [] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            String jsonString = new String(data,"UTF-8");
            airportArrayList = gson.fromJson(jsonString, new TypeToken<List<Airport>>() {}.getType());
            listView = findViewById(R.id.list_airport_item);
            AirportAdapter airportAdapter = new AirportAdapter(this.getBaseContext(),airportArrayList.subList(0,30));
            listView.setAdapter(airportAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d("debug","click " + i);
                    if (type == 0) {
                        intent.putExtra("FromAirport",i);
                    } else if (type == 1) {
                        intent.putExtra("ToAirport",i);
                    }
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}