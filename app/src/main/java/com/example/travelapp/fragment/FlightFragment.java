package com.example.travelapp.fragment;

import android.app.Activity;
import android.content.Intent;
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

import com.example.travelapp.R;
import com.example.travelapp.activity.SelectAirPortActivity;

public class FlightFragment extends Fragment {

    private Button selectAirportFromBtn;
    private Button selectAirPortToBtn;


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
        selectAirportFromBtn = view.findViewById(R.id.select_airportFrom_btn);
        selectAirPortToBtn = view.findViewById(R.id.select_airportTo_btn);
        selectAirPortToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAirport(0,view);
            }
        });

        selectAirportFromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAirport(1,view);
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
                        Log.d("debug", String.valueOf(intent.getIntExtra("FromAirport",-1)));
                        // Handle the Intent
                    }
                }
            });
}