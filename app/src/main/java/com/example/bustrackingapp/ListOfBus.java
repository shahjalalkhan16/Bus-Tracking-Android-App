package com.example.bustrackingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListOfBus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_bus);

        ListView busListView = findViewById(R.id.busListView);

        // Sample list of buses (you can replace this with your data)
        String[] buses = {"Bus 1", "Bus 2", "Bus 3", "Bus 4", "Bus 5"};

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buses);

        // Set the adapter for the ListView
        busListView.setAdapter(adapter);

        // Set item click listener to handle bus selection
        busListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedBus = (String) parent.getItemAtPosition(position);
                // Handle bus selection, e.g., show bus details or navigate to the real-time location page
                Toast.makeText(ListOfBus.this, "Selected Bus: " + selectedBus, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
