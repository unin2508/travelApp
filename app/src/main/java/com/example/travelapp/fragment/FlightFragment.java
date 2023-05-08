package com.example.travelapp.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelapp.R;
import com.example.travelapp.activity.SelectAirPortActivity;
import com.example.travelapp.activity.SelectFlightActivity;
import com.example.travelapp.model.Airport;
import com.example.travelapp.model.Flight;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FlightFragment extends Fragment {
    private Integer airportFrom = -1;
    private Integer airportTo = -1;
    private LocalDate departureDate;
    private Button selectAirportFromBtn;
    private Button selectAirPortToBtn;
    private Button selectDepartureDateBtn;
    private Button plusAdultTicketBtn;
    private Button minusAdultTicketBtn;
    private TextView adultTicketTv;

    private Button plusChildTicketBtn;
    private Button minusChildTicketBtn;
    private TextView childTicketTv;
    private Button searchFlightBtn;
    private ProgressBar progressBar;

    private ArrayList<Airport> airportArrayList;




    public FlightFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        try{
            Gson gson = new Gson();
            InputStream inputStream = getActivity().getAssets().open("airports.json");
            byte [] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            String jsonString = new String(data,"UTF-8");
            airportArrayList = gson.fromJson(jsonString, new TypeToken<List<Airport>>() {}.getType());
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar_flight);

        plusAdultTicketBtn = view.findViewById(R.id.plus_adult_ticket_btn);
        minusAdultTicketBtn = view.findViewById(R.id.minus_adult_ticket_btn);
        adultTicketTv = view.findViewById(R.id.adult_ticket_text);

        plusChildTicketBtn = view.findViewById(R.id.plus_child_ticket_btn);
        minusChildTicketBtn = view.findViewById(R.id.minus_child_ticket_btn);
        childTicketTv = view.findViewById(R.id.child_ticket_text);

        selectAirportFromBtn = view.findViewById(R.id.select_airportFrom_btn);
        selectAirPortToBtn = view.findViewById(R.id.select_airportTo_btn);
        selectDepartureDateBtn = view.findViewById(R.id.select_date_btn);

        searchFlightBtn = view.findViewById(R.id.search_flight_btn);

        if (airportFrom == -1) {
            selectAirportFromBtn.setText("Choose Departure from");
            selectAirportFromBtn.setTextColor(Color.parseColor("#9B9B9B"));
        } else {
            selectAirportFromBtn.setText(airportFrom);
            selectAirportFromBtn.setTextColor(Color.parseColor("#000000"));
        }
        if (airportTo == -1) {
            selectAirPortToBtn.setText("Choose Arrival at");
            selectAirPortToBtn.setTextColor(Color.parseColor("#9B9B9B"));
        } else {
            selectAirportFromBtn.setText(airportTo);
            selectAirportFromBtn.setTextColor(Color.parseColor("#000000"));
        }

        if (departureDate == null) {
            selectDepartureDateBtn.setText("Choose your Date");
            selectDepartureDateBtn.setTextColor(Color.parseColor("#9B9B9B"));
        }
        selectAirPortToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAirport(1,view);
            }
        });

        selectAirportFromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAirport(0,view);
            }
        });

        selectDepartureDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        departureDate = LocalDate.of(year, monthOfYear+1, dayOfMonth);
                        selectDepartureDateBtn.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                        selectDepartureDateBtn.setTextColor(Color.parseColor("#000000"));
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), listener, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        plusAdultTicketBtn.setOnClickListener(view1 -> {
            int ticket = Integer.parseInt(adultTicketTv.getText().toString())+1;
            if (ticket < 10){
                adultTicketTv.setText("0"+ticket);
            } else {
                adultTicketTv.setText(ticket+"");
            }
        });

        minusAdultTicketBtn.setOnClickListener(view1 -> {
            int ticket = Integer.parseInt(adultTicketTv.getText().toString())-1;
            if (ticket < 10 && ticket >= 0){
                adultTicketTv.setText("0"+ticket);
            } else if (ticket >= 10){
                adultTicketTv.setText(ticket+"");
            }
        });

        plusChildTicketBtn.setOnClickListener(view1 -> {
            int ticket = Integer.parseInt(childTicketTv.getText().toString())+1;
            if (ticket < 10){
                childTicketTv.setText("0"+ticket);
            } else {
                childTicketTv.setText(ticket+"");
            }
        });
        minusChildTicketBtn.setOnClickListener(view1 -> {
            int ticket = Integer.parseInt(childTicketTv.getText().toString())-1;
            if (ticket < 10 && ticket >= 0){
                childTicketTv.setText("0"+ticket);
            } else if (ticket >= 10){
                childTicketTv.setText(ticket+"");
            }
        });

        searchFlightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (airportFrom == -1){
                    Toast.makeText(getContext(),"Pick Departure Airport",Toast.LENGTH_LONG).show();
                } else if (airportTo == -1){
                    Toast.makeText(getContext(),"Pick Arrive Airport",Toast.LENGTH_LONG).show();
                } else if (departureDate == null){
                    Toast.makeText(getContext(),"Pick Departure Date",Toast.LENGTH_LONG).show();
                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("flights");

                    Intent intent = new Intent(view.getContext(), SelectFlightActivity.class);
                    intent.putExtra("flightDate",departureDate);
                    intent.putExtra("departureAirport",airportArrayList.get(airportFrom));
                    intent.putExtra("arrivalAirport",airportArrayList.get(airportTo));
                    startActivity(intent);
                }
            }
        });



    }


    private void selectAirport(Integer type , @NonNull View view) {
        Intent intent = new Intent(view.getContext(), SelectAirPortActivity.class);
        intent.putExtra("type",type);
        startActivityIntent.launch(intent);
    }

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == getActivity().RESULT_OK) {
                        Intent intent = result.getData();
                        int airportFromIntent = intent.getIntExtra("FromAirport",-1);
                        int airportToIntent = intent.getIntExtra("ToAirport",-1);

                        if (airportFromIntent != -1){
                            airportFrom = airportFromIntent;
                            selectAirportFromBtn.setText(airportArrayList.get(airportFromIntent).getName());
                            selectAirportFromBtn.setTextColor(Color.parseColor("#000000"));
                        }
                        if (airportToIntent != -1){
                            airportTo = airportToIntent;
                            selectAirPortToBtn.setText(airportArrayList.get(airportToIntent).getName());
                            selectAirPortToBtn.setTextColor(Color.parseColor("#000000"));
                        }
                        // Handle the Intent
                    }
                }
            });
}