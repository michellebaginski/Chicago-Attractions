package edu.uic.cs478.a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.contains("RESTAURANT")) {
            Intent i = new Intent(context,  RestaurantsActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setClassName("edu.uic.cs478.a2", "edu.uic.cs478.a2.RestaurantsActivity");
            context.startActivity(i);
        }

        if (action.contains("ATTRACTION")) {
            Intent i = new Intent(context,  AttractionsActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setClassName("edu.uic.cs478.a2", "edu.uic.cs478.a2.AttractionsActivity");
            context.startActivity(i);
        }
    }
}
