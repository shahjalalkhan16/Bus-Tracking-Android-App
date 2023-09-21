package com.example.bustrackingapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views and set click listeners
        TextView loginTextView = findViewById(R.id.loginTextView);
        TextView registerTextView = findViewById(R.id.registerTextView);

        // Navigate to the Login activity when the "Login here" text is clicked
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });

        // Navigate to the Registration activity when the "Register here" text is clicked
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegistration();
            }
        });
    }

    // Navigate to the Login activity
    private void navigateToLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    // Navigate to the Registration activity
    private void navigateToRegistration() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}
