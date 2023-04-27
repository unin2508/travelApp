package com.example.travelapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.travelapp.R;
import com.example.travelapp.adapter.FlightAdapter;
import com.example.travelapp.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class SelectFlightActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private RecyclerView flightRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_filght);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {finish();});

        flightRV = findViewById(R.id.flight_rv);
        FlightAdapter flightAdapter = new FlightAdapter(this);
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("01","NBA","TanSanNhat","7:30","9:30","VNA",100,1000));
        flights.add(new Flight("02","NBA","TanSanNhat","7:30","9:30","VNA",100,1000));
        flights.add(new Flight("03","NBA","TanSanNhat","7:30","9:30","VNA",100,1000));
        flights.add(new Flight("04","NBA","TanSanNhat","7:30","9:30","VNA",100,1000));
        flights.add(new Flight("05","NBA","TanSanNhat","7:30","9:30","VNA",100,1000));
        flightAdapter.setData(flights);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        flightRV.setLayoutManager(linearLayoutManager);
        flightRV.setAdapter(flightAdapter);


    }
}