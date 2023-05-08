package com.example.travelapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.travelapp.R;
import com.example.travelapp.adapter.FlightAdapter;
import com.example.travelapp.model.Flight;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    RecyclerView flightRV;
    ArrayList<Flight> flights;
    ProgressBar progressBar;
    TextView emptyTv;

    public CartFragment() {
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
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        flights = new ArrayList<>();
        emptyTv = view.findViewById(R.id.empty_cart_tv);
        progressBar = view.findViewById(R.id.progress_bar_cart);
        progressBar.setVisibility(View.VISIBLE);
        databaseReference.child("cart").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Flight flight = dataSnapshot.getValue(Flight.class);
                    flights.add(flight);
                }
                flightRV = view.findViewById(R.id.flight_cart_rv);
                FlightAdapter flightAdapter = new FlightAdapter(getActivity(),2);

                flightAdapter.setData(flights);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
                flightRV.setLayoutManager(linearLayoutManager);
                flightRV.setAdapter(flightAdapter);
                progressBar.setVisibility(View.INVISIBLE);
                if (flights.isEmpty()){
                    emptyTv.setVisibility(View.VISIBLE);
                } else {
                    emptyTv.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}