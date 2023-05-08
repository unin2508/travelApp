package com.example.travelapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelapp.R;
import com.example.travelapp.adapter.FlightAdapter;
import com.example.travelapp.model.Airport;
import com.example.travelapp.model.Flight;
import com.example.travelapp.service.AirportDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SelectFlightActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private RecyclerView flightRV;
    private ArrayList<Flight> flights;
    private TextView dateFlightTv;
    private TextView codeDepartureAirportTv;
    private TextView nameDepartureAirportTv;
    private TextView codeArrivalAirportTv;
    private TextView nameArrivalAirportTv;
    private TextView timeOfFlightTv;
    LocalDate flightDate = null;
    Airport departureAirport = null;
    Airport arrivalAirport = null;
    Button lowToHighBtn;
    Button highToLowBtn;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_filght);
        Intent intent = getIntent();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            flightDate = (LocalDate) intent.getSerializableExtra("flightDate",LocalDate.class);
            departureAirport = (Airport) intent.getSerializableExtra("departureAirport",Airport.class);
            arrivalAirport = (Airport) intent.getSerializableExtra("arrivalAirport", Airport.class);
        } else {
            flightDate = (LocalDate) intent.getSerializableExtra("flightDate");
            departureAirport = (Airport) intent.getSerializableExtra("departureAirport");
            arrivalAirport = (Airport) intent.getSerializableExtra("arrivalAirport");
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("flights");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = flightDate.format(formatter);
        flights = new ArrayList<>();
        databaseReference.child(formattedDate).child(departureAirport.getIata_code()).child(arrivalAirport.getIata_code()).orderByChild("departureTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            progressBar.setVisibility(View.INVISIBLE);
                if (!snapshot.hasChildren()) {
                    Toast.makeText(getApplicationContext(),"khong co chuyen bay phu hop",Toast.LENGTH_LONG).show();
                } else {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Flight flight = dataSnapshot.getValue(Flight.class);
                        flights.add(flight);
                    }
                    setUpUI();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
//        LocalDateTime dateTime = LocalDateTime.of(flightDate, LocalTime.now());
//
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd MMMM yyyy");
//        String formattedDateTime = dateTime.format(formatter1);
//        int seconds = (int) (flights.get(0).getArrivalTime() - flights.get(0).getDepartureTime()); // replace with your desired number of seconds
//
//        int hours = seconds / 3600; // calculate the number of hours
//        int minutes = (seconds % 3600) / 60; // calculate the number of minutes
//
//        LocalTime time = LocalTime.of(hours, minutes);
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh'h' mm'm'");
//        String flightTimeString = time.format(formatter2);
//
//        codeDepartureAirportTv = findViewById(R.id.code_departure_airport_Tv);
//        nameDepartureAirportTv = findViewById(R.id.name_departure_airport_Tv);
//        codeArrivalAirportTv = findViewById(R.id.code_arrival_airport_Tv);
//        nameArrivalAirportTv = findViewById(R.id.name_arrival_airport_Tv);
//        timeOfFlightTv = findViewById(R.id.time_of_flight_selectFlight_tv);
//
//
//        codeDepartureAirportTv.setText(departureAirport.getIata_code());
//        nameDepartureAirportTv.setText(departureAirport.getName());
//        codeArrivalAirportTv.setText(arrivalAirport.getIata_code());
//        nameArrivalAirportTv.setText(arrivalAirport.getName());
//        timeOfFlightTv.setText(flightTimeString);
//
//        dateFlightTv = findViewById(R.id.date_of_flight_tv);
//        dateFlightTv.setText(formattedDateTime);
//
//        backBtn = findViewById(R.id.back_btn);
//        backBtn.setOnClickListener(view -> {finish();});
//
//        flightRV = findViewById(R.id.flight_rv);
//        FlightAdapter flightAdapter = new FlightAdapter(this);
//
//        flightAdapter.setData(flights);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
//        flightRV.setLayoutManager(linearLayoutManager);
//        flightRV.setAdapter(flightAdapter);


//        gen flight firebase
//        for (Flight flight :flights){
//            Date date = new Date(flight.getDepartureTime()* 1000L);
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            String formattedDate = formatter.format(date);
//            Log.d("testdate",formattedDate);
//            databaseReference.child(formattedDate).child(flight.getDepartureAirport()).child(flight.getArrivalAirport()).setValue(null);
//            databaseReference.child(formattedDate).child(flight.getDepartureAirport()).child(flight.getArrivalAirport()).setValue(null).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    for (DataSnapshot snapshot1 :snapshot.getChildren()){
//                        Flight flight = snapshot1.getValue(Flight.class);
//                        databaseReference.child(flight.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()){
////                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
//                                    Log.d("debug","success");
//                                } else {
//                                    Log.d("debug",task.getException().getMessage());
////                    Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }

//        List<Flight> flights = new ArrayList<>();
//        flights.add(new Flight(UUID.randomUUID().toString(),"SGN","HAN",1684101600L,1684109700L,"VN",240,1900));
//        for (int i = 0 ;i<18;i++){
//            for (int j = 0;j<30;j++){
//                Flight flight = new Flight(UUID.randomUUID().toString(),flights.get(0).getDepartureAirport(),flights.get(0).getArrivalAirport(),flights.get(0).getDepartureTime()+3600*(i+1)+3600*24*(j-5),flights.get(0).getArrivalTime()+3600*(i+1)+3600*24*(j-5),flights.get(0).getAirlineName(),flights.get(0).getSeat(),flights.get(0).getMinPrice());
//                flights.add(flight);
//            }
//        }
//            for (Flight flight :flights){
//                Date date = new Date(flight.getDepartureTime()* 1000L);
//                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//                String formattedDate = formatter.format(date);
//                Log.d("testdate",formattedDate);
//
//                databaseReference.child(formattedDate).child(flight.getDepartureAirport()).child(flight.getArrivalAirport()).child(flight.getId()).setValue(flight).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Log.d("debug","success");
//                        }
//                    }
//                });
//            }


    }

    private void setUpUI(){
        LocalDateTime dateTime = LocalDateTime.of(flightDate, LocalTime.now());

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDateTime = dateTime.format(formatter1);
        int seconds = (int) (flights.get(0).getArrivalTime() - flights.get(0).getDepartureTime()); // replace with your desired number of seconds

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


        codeDepartureAirportTv.setText(departureAirport.getIata_code());
        nameDepartureAirportTv.setText(departureAirport.getName());
        codeArrivalAirportTv.setText(arrivalAirport.getIata_code());
        nameArrivalAirportTv.setText(arrivalAirport.getName());
        timeOfFlightTv.setText(flightTimeString);

        dateFlightTv = findViewById(R.id.date_of_flight_tv);
        dateFlightTv.setText(formattedDateTime);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {finish();});

        flightRV = findViewById(R.id.flight_rv);
        FlightAdapter flightAdapter = new FlightAdapter(this,1);

        flightAdapter.setData(flights);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        flightRV.setLayoutManager(linearLayoutManager);
        flightRV.setAdapter(flightAdapter);
        highToLowBtn = findViewById(R.id.high_to_low_btn);
        highToLowBtn.setOnClickListener(view -> {
            Collections.sort(flights,((f1, f2) -> {
                return f2.getMinPrice() - f1.getMinPrice() ;
            }));
            flightAdapter.setData(flights);
        });
        lowToHighBtn = findViewById(R.id.low_to_high_btn);
        lowToHighBtn.setOnClickListener(view -> {
            Collections.sort(flights,((f1, f2) -> {
                return f1.getMinPrice() - f2.getMinPrice() ;
            }));
            flightAdapter.setData(flights);
        });
    }
}