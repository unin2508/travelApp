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
import com.example.travelapp.activity.FlightTicketDetailActivity;
import com.example.travelapp.activity.SelectFlightTicketActivity;
import com.example.travelapp.model.Category;
import com.example.travelapp.model.Flight;
import com.example.travelapp.service.AirportDao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {
    private Context context;
    private List<Flight> flightList;
    private int type;

    public FlightAdapter(Context context,int type){
        this.context = context;
        this.type = type;
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
        int seconds = (int) (flightList.get(position).getArrivalTime() - flightList.get(position).getDepartureTime()); // replace with your desired number of seconds
        int hours = seconds / 3600; // calculate the number of hours
        int minutes = (seconds % 3600) / 60; // calculate the number of minutes
        LocalTime time = LocalTime.of(hours, minutes);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh'h' mm'm'");
        String flightTimeString = time.format(formatter2);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Timestamp departureTime = new Timestamp(flightList.get(position).getDepartureTime()*1000L);
        Timestamp arrivalTime = new Timestamp(flightList.get(position).getArrivalTime()*1000L);
//        String formattedTime = formatter.format(timestamp);
        holder.airlineTv.setText(flightList.get(position).getAirlineName());
        holder.departureAirportTv.setText(flightList.get(position).getDepartureAirport());

        holder.departureTimeTv.setText(formatter.format(departureTime));
        holder.timeFlightTv.setText(flightTimeString);
        holder.arriveAirportTv.setText(flightList.get(position).getArrivalAirport());
        holder.timeArriveTv.setText(formatter.format(flightList.get(position).getArrivalTime()*1000L));
        holder.minPriceTv.setText(flightList.get(position).getMinPrice()+"000 VND");

        holder.layout.setOnClickListener(view -> {
            if (type == 1){
                Intent intent = new Intent(context, SelectFlightTicketActivity.class);
                intent.putExtra("flight",flightList.get(position));
                intent.putExtra("flightTime",flightTimeString);
                intent.putExtra("departureAirportName",new AirportDao(context).getAirportNameByCode(flightList.get(position).getDepartureAirport()));
                intent.putExtra("arrivalAirportName",new AirportDao(context).getAirportNameByCode(flightList.get(position).getArrivalAirport()));
                context.startActivity(intent);
            } else if (type == 2){
                Intent intent = new Intent(context, FlightTicketDetailActivity.class);
                intent.putExtra("flight",flightList.get(position));
                intent.putExtra("flightTime",flightTimeString);
                intent.putExtra("departureAirportName",new AirportDao(context).getAirportNameByCode(flightList.get(position).getDepartureAirport()));
                intent.putExtra("arrivalAirportName",new AirportDao(context).getAirportNameByCode(flightList.get(position).getArrivalAirport()));
                context.startActivity(intent);
            }
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
