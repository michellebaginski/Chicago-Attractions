package edu.uic.cs478.a2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AttractionsActivity extends AppCompatActivity implements AttractionsFragment.ListSelectionListener {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private WebviewFragment webFragment = new WebviewFragment();
    private AttractionsFragment attractionFragment = new AttractionsFragment();
    FrameLayout landscapeLayout1, landscapeLayout2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Attractions");

        landscapeLayout1 = (FrameLayout) findViewById(R.id.fragment_container);
        landscapeLayout2 = (FrameLayout) findViewById(R.id.fragment_container2);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, attractionFragment);
        transaction.commit();

        manager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                setLayout();
            }
        });

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayout();
    }


    public void setLayout() {
        if (webFragment.isAdded()) {
            landscapeLayout1.setLayoutParams(new LinearLayout.LayoutParams(0
                    , ViewGroup.LayoutParams.MATCH_PARENT));

            landscapeLayout2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }

        else if (!webFragment.isAdded() && this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            landscapeLayout1.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            landscapeLayout2.setLayoutParams(new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT));

        }

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            landscapeLayout1.setLayoutParams(new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT, 1f));

            landscapeLayout2.setLayoutParams(new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT, 2f));
           }
    }


    @Override
    public void onListSelection(int index) {
        webFragment.setPosition(index);

        if (!webFragment.isAdded()) {
            transaction = manager.beginTransaction();
            transaction.add(R.id.fragment_container2, webFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            manager.executePendingTransactions();
        }
        else {
            transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container2, webFragment);
            transaction.commit();
            manager.executePendingTransactions();
        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attractions:
                Intent attractionsIntent = new Intent(getApplicationContext(),  AttractionsActivity.class);
                attractionsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                attractionsIntent.setClassName("edu.uic.cs478.a2", "edu.uic.cs478.a2.AttractionsActivity");
                startActivity(attractionsIntent);
                break;
            case R.id.restaurants:
                Intent restaurantsIntent = new Intent(getApplicationContext(),  RestaurantsActivity.class);
                restaurantsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                restaurantsIntent.setClassName("edu.uic.cs478.a2", "edu.uic.cs478.a2.RestaurantsActivity");
                startActivity(restaurantsIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}