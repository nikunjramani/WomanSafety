package com.example.womensafety.ui.slideshow;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.womensafety.R;
import com.example.womensafety.ui.home.sendLocation;
import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.CameraPosition;
import com.tomtom.online.sdk.map.MapView;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.RouteBuilder;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TomtomMap;
import com.tomtom.online.sdk.routing.OnlineRoutingApi;
import com.tomtom.online.sdk.routing.RoutingApi;
import com.tomtom.online.sdk.routing.data.FullRoute;
import com.tomtom.online.sdk.routing.data.RouteQuery;
import com.tomtom.online.sdk.routing.data.RouteQueryBuilder;
import com.tomtom.online.sdk.routing.data.RouteResponse;
import com.tomtom.online.sdk.routing.data.RouteType;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class viewLocationOnMap extends AppCompatActivity implements OnMapReadyCallback {

    Location location;
    private TomtomMap tomtomMap;
    Button getDirection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_location_on_map);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getDirection = (Button) findViewById(R.id.get_direction);
        MapView mapView = (MapView) findViewById(R.id.mapFragment_view);
        mapView.addOnMapReadyCallback(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onMapReady(@NonNull TomtomMap map) {
        this.tomtomMap = map;
        tomtomMap.setMyLocationEnabled(true);
//        location = tomtomMap.getUserLocation();


        LatLng amsterdam = new LatLng(Double.parseDouble(getIntent().getStringExtra("lat")), Double.parseDouble(getIntent().getStringExtra("log")));
        SimpleMarkerBalloon balloon = new SimpleMarkerBalloon("");
        tomtomMap.addMarker(new MarkerBuilder(amsterdam).markerBalloon(balloon));
        tomtomMap.centerOn(CameraPosition.builder(amsterdam).zoom(7).build());

        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(viewLocationOnMap.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(viewLocationOnMap.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new sendLocation.MyLocationListener());
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                LatLng startPoint = new LatLng(23.0225, 72.5714);
                LatLng endPoint = new LatLng(location.getLatitude(),location.getLongitude());
                RoutingApi routingApi = OnlineRoutingApi.create(viewLocationOnMap.this);
                RouteQuery routeQuery = new RouteQueryBuilder(startPoint, endPoint).withRouteType(RouteType.FASTEST).build();
                routingApi.planRoute(routeQuery)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSingleObserver<RouteResponse>() {
                            @Override
                            public void onSuccess(RouteResponse routeResponse) {
                                for (FullRoute fullRoute : routeResponse.getRoutes()) {
                                    RouteBuilder routeBuilder = new RouteBuilder(
                                            fullRoute.getCoordinates());
                                    map.addRoute(routeBuilder);
                                }
                            }
                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(viewLocationOnMap.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }

}
