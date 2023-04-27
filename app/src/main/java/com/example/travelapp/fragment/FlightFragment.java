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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.travelapp.R;
import com.example.travelapp.activity.SelectAirPortActivity;
import com.example.travelapp.activity.SelectFlightActivity;

import java.util.Calendar;
import java.util.Date;

public class FlightFragment extends Fragment {
    private String airportFrom;
    private String airportTo;
    private Date departureDate;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        if (airportFrom == null) {
            selectAirportFromBtn.setText("Choose Depature from");
            selectAirportFromBtn.setTextColor(Color.parseColor("#9B9B9B"));
        } else {
            selectAirportFromBtn.setText(airportFrom);
            selectAirportFromBtn.setTextColor(Color.parseColor("#000000"));
        }
        if (airportTo == null) {
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
                        departureDate = new Date(year,monthOfYear,dayOfMonth);
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
                Intent intent = new Intent(view.getContext(), SelectFlightActivity.class);
                startActivity(intent);
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
                        String fromAirPort = intent.getStringExtra("FromAirport");
                        String toAirPort = intent.getStringExtra("ToAirport");
                        if (fromAirPort != null){
                            selectAirportFromBtn.setText(fromAirPort);
                            selectAirportFromBtn.setTextColor(Color.parseColor("#000000"));
                        }
                        if (toAirPort != null){
                            selectAirPortToBtn.setText(toAirPort);
                            selectAirPortToBtn.setTextColor(Color.parseColor("#000000"));
                        }
                        // Handle the Intent
                    }
                }
            });
}