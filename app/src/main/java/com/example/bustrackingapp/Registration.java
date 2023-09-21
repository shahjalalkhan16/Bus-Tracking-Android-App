package com.example.bustrackingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    TextInputEditText userNameEditText,emailEditText,passwordEditText,deptEditText,batchEditText;
    Button registerButton;
    TextView loginTextView;
    ProgressBar progressBar;
     FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        userNameEditText = findViewById(R.id.userName);
        deptEditText = findViewById(R.id.dept);
        batchEditText = findViewById(R.id.batch);
        registerButton = findViewById(R.id.buttonRegister);
        loginTextView = findViewById(R.id.loginTextView);
        progressBar =findViewById(R.id.progressBar);

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final String userName = userNameEditText.getText().toString().trim();
//                final String email = emailEditText.getText().toString().trim();
//                final String password = passwordEditText.getText().toString().trim();
//                final String dept = deptEditText.getText().toString().trim();
//                final String batch = batchEditText.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);
                String email,password;
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if(TextUtils.isEmpty(email)){

                    Toast.makeText(Registration.this ,"Enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Registration.this ,"Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Toast.makeText(Registration.this, "Registration Successfully.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(getApplicationContext(),Login.class);
                                    startActivity(i);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Registration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                /*
                // Register the user using Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Registration successful
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    // Store user data in your database here

                                    // Proceed to the next activity
                                    startActivity(new Intent(Registration.this, Login.class));
                                    finish();
                                } else {
                                    // Registration failed
                                    if (task.getException() instanceof FirebaseAuthException) {
                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        String errorCode = e.getErrorCode();

                                        // Log or display the error code and description
                                        Log.e("Registration", "Error code: " + errorCode);
                                        Toast.makeText(Registration.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }); */
            }
        });
    }
}