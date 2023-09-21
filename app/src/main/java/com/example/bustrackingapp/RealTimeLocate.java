package com.example.bustrackingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RealTimeLocate extends AppCompatActivity implements OnMapReadyCallback  {
    private GoogleMap mMap;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_locate);
        firestore = FirebaseFirestore.getInstance();

        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Example: Add a marker for a bus's location (replace with real-time data)
        LatLng busLocation = new LatLng(37.7749, -122.4194); // Example coordinates for San Francisco
        mMap.addMarker(new MarkerOptions().position(busLocation).title("Bus 1"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(busLocation, 14)); // Zoom level 14

        // If you have real-time data, you can update the bus's location here
        // Use Firestore or another backend service to retrieve real-time bus location data
        // Example Firestore query to retrieve bus locations
        firestore.collection("bus_locations")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .addSnapshotListener(new com.google.firebase.firestore.EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            // Handle Firestore errors here
                            return;
                        }

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            // Get the bus location data and update the marker
                            double latitude = document.getDouble("latitude");
                            double longitude = document.getDouble("longitude");

                            LatLng newBusLocation = new LatLng(latitude, longitude);
                            mMap.clear(); // Clear previous markers
                            mMap.addMarker(new MarkerOptions().position(newBusLocation).title("Bus 1"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newBusLocation, 14));
                        }
                    }
                });
    }
}