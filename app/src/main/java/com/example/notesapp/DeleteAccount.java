package com.example.notesapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeleteAccount extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        Button deleteButton = findViewById(R.id.deleteButton);
        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth here

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();

                if (user != null) {
                    // Check if the user is authenticated
                    if (user.isEmailVerified()) {
                        // User is authenticated and email is verified, proceed with account deletion
                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Account deleted successfully
                                            Toast.makeText(getApplicationContext(), "Account Deleted Successfully", Toast.LENGTH_LONG).show();
                                            // Redirect or perform any other actions
                                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(i);
                                        } else {
                                            // Account deletion failed, handle the error
                                            Log.e(TAG, "Error deleting account: " + task.getException().getMessage());
                                            Toast.makeText(getApplicationContext(), "Account Deletion Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        // User is not authorized (e.g., email is not verified)
                        // Show an error message or take appropriate action
                        Toast.makeText(getApplicationContext(), "User is not authorized. Please verify your email.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // User is not authenticated, handle this case (e.g., show an error message)
                    Toast.makeText(getApplicationContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
