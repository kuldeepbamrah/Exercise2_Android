package com.example.exercise2_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {


    private FusedLocationProviderClient fusedLocationProviderClient;
    double  latitude,longitude,altitude,accuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Button getLocationBtn = findViewById(R.id.get_Location_Btn);

        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                    requestPermission();
                    return;
                }
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null)
                        {
                            TextView lat = findViewById(R.id.lat);
                            TextView longi = findViewById(R.id.longitude);
                            TextView alt = findViewById(R.id.altitude);
                            TextView acc = findViewById(R.id.accuracy);
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            altitude = location.getAltitude();
                            accuracy = location.getAccuracy();


                            String lati = ""+latitude;
                            Toast.makeText(MainActivity.this,""+lati,Toast.LENGTH_SHORT).show();
                            lat.setText(String.valueOf(latitude));
                            longi.setText(String.valueOf(latitude));
                            alt.setText(String.valueOf(altitude));
                            acc.setText(String.valueOf(accuracy));


                        }
                    }
                });
            }
        });
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);

    }

}
