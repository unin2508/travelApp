package com.example.travelapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.travelapp.R;
import com.example.travelapp.activity.ChangePasswordActivity;
import com.example.travelapp.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class UserFragment extends Fragment {

    Button logoutBtn;
    Button toChangePasswordBtn;
    Button callSpBtn;

    public UserFragment() {
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logoutBtn = view.findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        toChangePasswordBtn = view.findViewById(R.id.toChangePasswordViewBtn);
        toChangePasswordBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
            startActivity(intent);
        });
        callSpBtn = view.findViewById(R.id.callSp_btn);
        callSpBtn.setOnClickListener(view1 -> {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        100);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+84369904721"));
                startActivity(intent);
            }

        });
    }
}