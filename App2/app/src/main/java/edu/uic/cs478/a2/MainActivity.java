package edu.uic.cs478.a2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyReceiver receiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("A2");

        receiver = new MyReceiver();
        filter = new IntentFilter();
        filter.addAction("edu.uic.cs478.sp.project3.ACTION_ATTRACTIONS");
        filter.addAction("edu.uic.cs478.sp.project3.ACTION_RESTAURANTS");
        registerReceiver(receiver, filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attractions:
                Toast.makeText(this, "Attractions", Toast.LENGTH_LONG).show();
                break;
            case R.id.restaurants:
                Toast.makeText(this, "Restaurants", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        registerReceiver(receiver, filter,"edu.uic.cs478.sp.project3",null);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }


    // allows the receiver to still be registered when back is pressed
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
