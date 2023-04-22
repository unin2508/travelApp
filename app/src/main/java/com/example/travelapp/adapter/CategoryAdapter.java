package com.example.travelapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
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
import com.example.travelapp.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategortViewHolder> {
    private Context context;
    private List<Category> categoryList;
    private int selectedIndex = -1;

    public CategoryAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Category> categoryList){
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new CategortViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategortViewHolder holder, int position) {
        Category category = this.categoryList.get(position);
        if (category == null){
            return;
        }
        holder.nameTV.setText(category.getName());
        switch (category.getName()){
            case "Places":
                holder.imageCategory.setImageResource(R.drawable.place);
                break;
            case "Flights":
                holder.imageCategory.setImageResource(R.drawable.plane);
                break;
            case "Trains":
                holder.imageCategory.setImageResource(R.drawable.train);
                break;
            case "Buses":
                holder.imageCategory.setImageResource(R.drawable.bus_stop);
                break;
            case "Taxi":
                holder.imageCategory.setImageResource(R.drawable.taxi);
                break;
            default:
                break;
        }
        holder.categoryCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedIndex = position;
                notifyDataSetChanged();
                Log.d("debug","test");


            }
        });

        if (selectedIndex == position){
            holder.nameTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.nameTV.setTextSize(14);
            holder.nameTV.setTextColor(0xff000000);
        } else {
            holder.nameTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.nameTV.setTextSize(12);
            holder.nameTV.setTextColor(0xff848484);
        }
    }

    @Override
    public int getItemCount() {
        if (this.categoryList != null){
            return this.categoryList.size();
        }
        return 0;
    }

    public class CategortViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageCategory;
        private TextView nameTV;
        private CardView categoryCV;

        public CategortViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.name_category);
            imageCategory = itemView.findViewById(R.id.image_category);
            categoryCV = itemView.findViewById(R.id.cardview_category);
        }
    }
}
