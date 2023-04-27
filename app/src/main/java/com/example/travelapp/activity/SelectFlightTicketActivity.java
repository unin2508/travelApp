package com.example.travelapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.travelapp.R;
import com.example.travelapp.model.Flight;

public class SelectFlightTicketActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flight_ticket);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {finish();});

        Intent intent = getIntent();
        Flight flight = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            flight = (Flight) intent.getSerializableExtra("flight", Flight.class);
        } else {
            flight = (Flight) intent.getSerializableExtra("flight");
        }

        recyclerView = findViewById(R.id.ticket_recyclerView);

    }
}