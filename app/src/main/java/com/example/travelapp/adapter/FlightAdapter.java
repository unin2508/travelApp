package com.example.travelapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.activity.SelectFlightTicketActivity;
import com.example.travelapp.model.Category;
import com.example.travelapp.model.Flight;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {
    private Context context;
    private List<Flight> flightList;

    public FlightAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Flight> flights){
        this.flightList = flights;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight,parent,false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        holder.airlineTv.setText(flightList.get(position).getAirlineName());
        holder.departureAirportTv.setText(flightList.get(position).getDepartureAirport());
        holder.departureTimeTv.setText(flightList.get(position).getDepartureTime());
        holder.timeFlightTv.setText("04h:15m");
        holder.arriveAirportTv.setText(flightList.get(position).getArrivalAirport());
        holder.timeArriveTv.setText(flightList.get(position).getArrivalTime());
        holder.minPriceTv.setText("1000 USD");

        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, SelectFlightTicketActivity.class);
            intent.putExtra("flight",flightList.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (flightList != null){
            return flightList.size();
        }
        return 0;
    }

    public class FlightViewHolder extends RecyclerView.ViewHolder{

        private CardView layout;
        private ImageView airlineImage;
        private TextView airlineTv;
        private TextView departureAirportTv;
        private TextView departureTimeTv;
        private TextView timeFlightTv;
        private TextView arriveAirportTv;
        private TextView timeArriveTv;
        private TextView minPriceTv;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_flight_card_view);
            airlineImage = itemView.findViewById(R.id.airline_image);
            airlineTv = itemView.findViewById(R.id.airline_name);
            departureAirportTv = itemView.findViewById(R.id.departure_airport_tv);
            departureTimeTv = itemView.findViewById(R.id.departure_time_tv);
            timeFlightTv = itemView.findViewById(R.id.time_flight_tv);
            arriveAirportTv = itemView.findViewById(R.id.arrive_airport_tv);
            timeArriveTv = itemView.findViewById(R.id.time_arrive_tv);
            minPriceTv = itemView.findViewById(R.id.minPrice_tv);
        }
    }
}
