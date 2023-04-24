package com.example.travelapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.travelapp.R;

public class SelectFilghtActivity extends AppCompatActivity {

    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_filght);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {finish();});

    }
}