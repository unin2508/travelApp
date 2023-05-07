package com.example.travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.model.FlightTicket;

import java.util.ArrayList;
import java.util.List;

public class RowFlightTicketAdapter extends RecyclerView.Adapter<RowFlightTicketAdapter.FlightTicketViewHolder> {

    private final Context context;
    private List<FlightTicket> flightTicketList;
    private List<FlightTicket> flightTicketSelectedList = new ArrayList<>();
    private OnRowFlightTicketAdapterItemClickListener adapterItemClickListener = null;

    public RowFlightTicketAdapter(Context context,OnRowFlightTicketAdapterItemClickListener listener){
        this.context = context;
        this.adapterItemClickListener = listener;
    }

    public void setData(List<FlightTicket> flightTicketList){
        this.flightTicketList = flightTicketList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_flight_ticket,parent,false);

        return new FlightTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightTicketViewHolder holder, int position) {
        int row = (int) position/6;
        holder.rowTv.setText(position+1+"");
        setUpUI(holder.imageButton1,flightTicketList.get(row * 6),row,0);
        setUpUI(holder.imageButton2,flightTicketList.get(row*6 +1),row,1);
        setUpUI(holder.imageButton3,flightTicketList.get(row*6 +2),row,2);
        setUpUI(holder.imageButton4,flightTicketList.get(row*6 +3),row,3);
        setUpUI(holder.imageButton5,flightTicketList.get(row*6 +4),row,4);
        setUpUI(holder.imageButton6,flightTicketList.get(row*6 +5),row,5);
    }


    @Override
    public int getItemCount() {
        if (flightTicketList != null) {
            return (Integer) flightTicketList.size()/6;
        }
        return 0;
    }

    private void setUpUI(ImageButton button, FlightTicket flightTicket,int row,int index){
        FlightTicket ticket = flightTicketList.get(row*6+index);
        if (flightTicket.getSold()){
            button.setImageResource(R.drawable.ic_ticket_booked);
        } else if (!flightTicketSelectedList.contains(flightTicket)){
            button.setImageResource(R.drawable.ic_ticket_available);
        }
        button.setOnClickListener(view -> {
            if (flightTicketSelectedList.contains(ticket)){
                button.setImageResource(R.drawable.ic_ticket_available);
                flightTicketSelectedList.remove(ticket);
            } else {
                button.setImageResource(R.drawable.ic_ticket_selected);
                flightTicketSelectedList.add(ticket);
            }
            adapterItemClickListener.onAdapterItemClickListener(this.flightTicketSelectedList);
        });

    }

    public interface OnRowFlightTicketAdapterItemClickListener {
        void onAdapterItemClickListener(List<FlightTicket> flightTicketSelectedList);
    }

    public class FlightTicketViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imageButton1;
        private ImageButton imageButton2;
        private ImageButton imageButton3;
        private ImageButton imageButton4;
        private ImageButton imageButton5;
        private ImageButton imageButton6;
        private TextView rowTv;

        public FlightTicketViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton1 = itemView.findViewById(R.id.ticket1_btn);
            imageButton2 = itemView.findViewById(R.id.ticket2_btn);
            imageButton3 = itemView.findViewById(R.id.ticket3_btn);
            imageButton4 = itemView.findViewById(R.id.ticket4_btn);
            imageButton5 = itemView.findViewById(R.id.ticket5_btn);
            imageButton6 = itemView.findViewById(R.id.ticket6_btn);
            rowTv = itemView.findViewById(R.id.row_ticket_tv);
        }
    }
}
