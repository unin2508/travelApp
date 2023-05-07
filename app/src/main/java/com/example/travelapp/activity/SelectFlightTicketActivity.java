package com.example.travelapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.travelapp.R;
import com.example.travelapp.adapter.RowFlightTicketAdapter;
import com.example.travelapp.model.Airport;
import com.example.travelapp.model.Flight;
import com.example.travelapp.model.FlightTicket;
import com.example.travelapp.service.AirportDao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SelectFlightTicketActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private RecyclerView recyclerView;

    private TextView dateFlightTv;
    private TextView codeDepartureAirportTv;
    private TextView nameDepartureAirportTv;
    private TextView codeArrivalAirportTv;
    private TextView nameArrivalAirportTv;
    private TextView timeOfFlightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flight_ticket);
        Intent intent = getIntent();
        Flight flight = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            flight = (Flight) intent.getSerializableExtra("flight", Flight.class);
        } else {
            flight = (Flight) intent.getParcelableExtra("flight");
        }
        long timestamp = flight.getDepartureTime();
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate flightDate = zonedDateTime.toLocalDate();

        LocalDateTime dateTime = LocalDateTime.of(flightDate, LocalTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String dateFlightString = dateTime.format(formatter);

        int seconds = (int) (flight.getArrivalTime() - flight.getDepartureTime()); // replace with your desired number of seconds

        int hours = seconds / 3600; // calculate the number of hours
        int minutes = (seconds % 3600) / 60; // calculate the number of minutes

        LocalTime time = LocalTime.of(hours, minutes);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh'h' mm'm'");
        String flightTimeString = time.format(formatter2);

        dateFlightTv = findViewById(R.id.date_of_flight_tv);
        codeDepartureAirportTv = findViewById(R.id.code_departure_airport_Tv);
        nameDepartureAirportTv = findViewById(R.id.name_departure_airport_Tv);
        codeArrivalAirportTv = findViewById(R.id.code_arrival_airport_Tv);
        nameArrivalAirportTv = findViewById(R.id.name_arrival_airport_Tv);
        timeOfFlightTv = findViewById(R.id.time_of_flight_selectFlightTicket_tv);

        dateFlightTv.setText(dateFlightString);
        codeDepartureAirportTv.setText(flight.getDepartureAirport());
        nameDepartureAirportTv.setText(new AirportDao(this).getAirportNameByCode(flight.getDepartureAirport()));
        codeArrivalAirportTv.setText(flight.getArrivalAirport());
        nameArrivalAirportTv.setText(new AirportDao(this).getAirportNameByCode(flight.getArrivalAirport()));
        timeOfFlightTv.setText(flightTimeString);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {finish();});



        recyclerView = findViewById(R.id.ticket_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RowFlightTicketAdapter adapter = new RowFlightTicketAdapter(this);
//        List<FlightTicket> flightTicketList = new ArrayList<>();
//        Flight flight1 =  new Flight(UUID.randomUUID().toString(),"SGN","HNA",1684101600L,1684109700L,"VN",240,1900);
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"001","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"002","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"003","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"004","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"005","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"006","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"007","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"008","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"009","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"010","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"011","Eco","A1","","",1000,false,null));
//        flightTicketList.add(new FlightTicket(UUID.randomUUID().toString(),"012","Eco","A1","","",1000,false,null));
        adapter.setData(flight.getTicketList());
        recyclerView.setAdapter(adapter);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    }
}