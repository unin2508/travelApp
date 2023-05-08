package com.example.travelapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.travelapp.R;
import com.example.travelapp.adapter.FlightTicketAdapter;
import com.example.travelapp.adapter.RowFlightTicketAdapter;
import com.example.travelapp.model.Airport;
import com.example.travelapp.model.Flight;
import com.example.travelapp.model.FlightTicket;
import com.example.travelapp.service.AirportDao;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FlightTicketDetailActivity extends AppCompatActivity {
    Flight flight;
    ArrayList<FlightTicket> flightTickets;
    RecyclerView flightTicketRv;

    private TextView dateFlightTv;
    private TextView codeDepartureAirportTv;
    private TextView nameDepartureAirportTv;
    private TextView codeArrivalAirportTv;
    private TextView nameArrivalAirportTv;
    private TextView timeOfFlightTv;
    private ImageButton backBtn;

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

        long timestamp = flight.getDepartureTime()*1000L;
        Date flightDate = new Date(timestamp);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        String dateString = dateFormat.format(flightDate);
        int seconds = (int) (flight.getArrivalTime() - flight.getDepartureTime()); // replace with your desired number of seconds

        int hours = seconds / 3600; // calculate the number of hours
        int minutes = (seconds % 3600) / 60; // calculate the number of minutes

        LocalTime time = LocalTime.of(hours, minutes);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh'h' mm'm'");
        String flightTimeString = time.format(formatter2);

        codeDepartureAirportTv = findViewById(R.id.code_departure_airport_Tv);
        nameDepartureAirportTv = findViewById(R.id.name_departure_airport_Tv);
        codeArrivalAirportTv = findViewById(R.id.code_arrival_airport_Tv);
        nameArrivalAirportTv = findViewById(R.id.name_arrival_airport_Tv);
        timeOfFlightTv = findViewById(R.id.time_of_flight_selectFlight_tv);

        Airport departureAirport = new AirportDao(this).getAirportByCode(flight.getDepartureAirport());
        Airport arrivalAirport = new AirportDao(this).getAirportByCode(flight.getArrivalAirport());

        codeDepartureAirportTv.setText(departureAirport.getIata_code());
        nameDepartureAirportTv.setText(departureAirport.getName());
        codeArrivalAirportTv.setText(arrivalAirport.getIata_code());
        nameArrivalAirportTv.setText(arrivalAirport.getName());
        timeOfFlightTv.setText(flightTimeString);

        dateFlightTv = findViewById(R.id.date_of_flight_tv);
        dateFlightTv.setText(dateString);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {finish();});
    }
}