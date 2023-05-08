package com.example.travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.model.FlightTicket;

import java.util.ArrayList;

public class FlightTicketAdapter extends RecyclerView.Adapter<FlightTicketAdapter.FlightTicketDetailViewHolder> {
    private Context context;
    private ArrayList<FlightTicket>  flightTickets;

    public FlightTicketAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<FlightTicket>  flightTickets){
        this.flightTickets = flightTickets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightTicketDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_flight_ticket_detail,parent,false);
        return new FlightTicketDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightTicketDetailViewHolder holder, int position) {
        holder.priceTv.setText(flightTickets.get(position).getPrice()*1000+" VND");
        holder.seatTv.setText(flightTickets.get(position).getSeat());
        holder.terminalTv.setText(flightTickets.get(position).getTerminal());
        holder.gateTv.setText(flightTickets.get(position).getGate());
        holder.typeTv.setText(flightTickets.get(position).getType());
    }

    @Override
    public int getItemCount() {
        if (this.flightTickets != null){
            return this.flightTickets.size();
        }
        return 0;
    }

    public class FlightTicketDetailViewHolder extends RecyclerView.ViewHolder{

        TextView nameTv;
        TextView typeTv;
        TextView priceTv;
        TextView boardingTv;
        TextView gateTv;
        TextView terminalTv;
        TextView seatTv;

        public FlightTicketDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.passenger_name_tv);
            typeTv = itemView.findViewById(R.id.type_flight_ticket_tv);
            priceTv = itemView.findViewById(R.id.price_flight_ticket_tv);
            boardingTv = itemView.findViewById(R.id.boarding_time_tv);
            gateTv = itemView.findViewById(R.id.gate_tv);
            terminalTv = itemView.findViewById(R.id.terminal_tv);
            seatTv = itemView.findViewById(R.id.seat_tv);
        }
    }
}
