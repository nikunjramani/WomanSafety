package com.example.womensafety.ui.home;

import android.Manifest;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.womensafety.Account.SharedPreferenceConfig;
import com.example.womensafety.URL;
import com.example.womensafety.ui.map.MapFragment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import timber.log.Timber;

import static com.android.volley.VolleyLog.TAG;

public class sendLocation extends Service{
    public static final String BROADCAST_ACTION = "Hello World";
    public LocationManager locationManager;
    Location location;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    public MyLocationListener listener;
    public static String str_receiver = "servicetutorial.service.receiver";
    Intent intent;
    int counter = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.v("STOP_SERVICE", "DONE");
        stopSelf();
        locationManager.removeUpdates(listener);
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        startService(new Intent(sendLocation.this,sendLocation.class));
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                if (ActivityCompat.checkSelfPermission(sendLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(sendLocation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, (LocationListener) listener);

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, 0, listener);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                Toast.makeText(sendLocation.this, location.getLatitude()+"", Toast.LENGTH_SHORT).show();
                assert location != null;
                sendlocation(location.getLatitude(),location.getLongitude());

                handler.postDelayed(this, 50000 );
            }
        };

        handler.postDelayed(r, 50000 );

        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(this, "Service Start", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }
    void sendlocation(double lat, double log){
        RequestQueue queue = Volley.newRequestQueue(sendLocation.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.url + "location/add_location.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("cid", SharedPreferenceConfig.getCid());
                param.put("latitude", String.valueOf(lat));
                param.put("longitude", String.valueOf(log));
                return param;
            }
        };
        queue.add(stringRequest);
    }

    public static class MyLocationListener implements LocationListener
    {

        public void onLocationChanged(final Location loc)
        {
            Log.i("*****", "Location changed");
//            Toast.makeText(sendLocation.this, loc.getLatitude()+"aa"+loc.getLongitude(), Toast.LENGTH_SHORT).show();
//            sendlocation(String.valueOf(loc.getLatitude()),String.valueOf(loc.getLongitude()));
//                loc.getLatitude();
//                loc.getLongitude();
//                intent.putExtra("Latitude", loc.getLatitude());
//                intent.putExtra("Longitude", loc.getLongitude());
//                intent.putExtra("Provider", loc.getProvider());
//                sendBroadcast(intent);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public void onProviderDisabled(String provider)
        {
        }


        public void onProviderEnabled(String provider)
        {
        }

    }
}
