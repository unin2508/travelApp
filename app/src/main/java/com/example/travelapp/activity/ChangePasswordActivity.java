package com.example.travelapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.travelapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText oldPassword;
    EditText newPassword;
    EditText confirmNewPassword;
    Button changePasswordBtn;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPassword = findViewById(R.id.old_password_et);
        newPassword = findViewById(R.id.new_password_et);
        confirmNewPassword = findViewById(R.id.cf_new_password_et);
        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view -> {finish();});

        changePasswordBtn = findViewById(R.id.change_password_btn);
        changePasswordBtn.setOnClickListener(view -> {
            if (oldPassword.getText().toString().isEmpty()  || newPassword.getText().toString().isEmpty() || confirmNewPassword.getText().toString().isEmpty()) {
                Toast.makeText(this,"You fill all form",Toast.LENGTH_LONG).show();
                return;
            } else if (!newPassword.getText().toString().equals(confirmNewPassword.getText().toString())){
                Toast.makeText(this,"New Password is not match",Toast.LENGTH_LONG).show();
                return;
            }
            FirebaseUser user;
            user = FirebaseAuth.getInstance().getCurrentUser();
            final String email = user.getEmail();
            AuthCredential credential = EmailAuthProvider.getCredential(email, oldPassword.getText().toString());

            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        user.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();

                                } else {
                                    finish();
                                    Toast.makeText(getApplicationContext(), "Password Successfully Modified", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {

                    }
                }
            });
        });
    }

}