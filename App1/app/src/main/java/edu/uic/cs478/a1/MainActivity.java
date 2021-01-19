package edu.uic.cs478.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Things to Do in Chicago");

        Button attractionsBtn = (Button) findViewById(R.id.attraction_button);
        Button restaurantsBtn = (Button) findViewById(R.id.restaurants_button);


        // display toast message when "Attractions" is selected
        attractionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast('a');
            }
        });


        // display toast message when "Restaurants" is selected
        restaurantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast('r');
            }
        });
    }

    // sends a broadcast that will be picked up in the receiver app
    public void sendBroadcast(char type) {
        Intent intent = new Intent();

        // intent for attractions
        if (type == 'a') {
            intent.setAction("edu.uic.cs478.sp.project3.ACTION_ATTRACTIONS");
        }
        // intent for restaurants
        if (type == 'r') {
            intent.setAction("edu.uic.cs478.sp.project3.ACTION_RESTAURANTS");
        }
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // permission was not granted
        if (ContextCompat.checkSelfPermission(this, "edu.uic.cs478.sp2020.project3") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {"edu.uic.cs478.sp2020.project3"}, 0);
        }
    }

}
