package com.example.praktikum6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.praktikum6.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        EditText editTextSource = findViewById(R.id.Source);
        EditText editTextDestination = findViewById(R.id.Destination);
        Button button = findViewById(R.id.btnSubmit);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Source = editTextSource.getText().toString();
                String Destination = editTextDestination.getText().toString();
                if (Source.equals("") && Destination.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter both source and destination", Toast.LENGTH_SHORT).show();
                } else {
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/" + Source + "/" + Destination);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
    }
}