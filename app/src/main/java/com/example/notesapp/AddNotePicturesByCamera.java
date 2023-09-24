package com.example.notesapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class AddNotePicturesByCamera extends AppCompatActivity {

    private ImageView capturedImageView;
    private EditText lectureTitleEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_pictures_by_camera);

        capturedImageView = findViewById(R.id.capturedImageView);
        lectureTitleEditText = findViewById(R.id.lectureTitleEditText);
        saveButton = findViewById(R.id.saveButton);

        Bitmap imageBitmap = getIntent().getParcelableExtra("imageBitmap");
        if (imageBitmap != null) {
            capturedImageView.setImageBitmap(imageBitmap);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lectureTitle = lectureTitleEditText.getText().toString();
                // Save the image and lecture title to your notes or database
                // You can modify this part based on your note structure
                // For example: Create a new Note object with imageBitmap and lectureTitle
                // and store it in your notes collection or database
                // Once saved, you can navigate back to the main activity
                finish();
            }
        });
    }
}
