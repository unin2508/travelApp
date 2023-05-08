package com.example.travelapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.travelapp.R;
import com.example.travelapp.adapter.FlightTicketAdapter;
import com.example.travelapp.adapter.RowFlightTicketAdapter;
import com.example.travelapp.model.Flight;
import com.example.travelapp.model.FlightTicket;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class FlightTicketDetailActivity extends AppCompatActivity {
    Flight flight;
    ArrayList<FlightTicket> flightTickets;
    RecyclerView flightTicketRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_ticket_detail);

        Intent intent = getIntent();
        flight = null;
        flightTickets = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            flight = intent.getSerializableExtra("flight", Flight.class);
        } else {
            flight = (Flight) intent.getSerializableExtra("flight");
        }
        String uid = FirebaseAuth.getInstance().getUid();
        for (FlightTicket flightTicket: flight.getTicketList()){

            if (flightTicket.getUserId() != null && flightTicket.getUserId().equals(uid)){
                flightTickets.add(flightTicket);
            }
        }
        flightTicketRv = findViewById(R.id.ticket_Rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        flightTicketRv.setLayoutManager(linearLayoutManager);
        FlightTicketAdapter adapter = new FlightTicketAdapter(this);
        adapter.setData(flightTickets);
        flightTicketRv.setAdapter(adapter);
    }
}