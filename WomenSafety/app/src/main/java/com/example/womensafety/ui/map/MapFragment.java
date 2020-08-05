package com.example.womensafety.ui.map;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.womensafety.Login;
import com.example.womensafety.R;
import com.example.womensafety.URL;
import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.common.location.LatLngAcc;
import com.tomtom.online.sdk.map.CameraPosition;
import com.tomtom.online.sdk.map.MapView;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.TomtomMap;
import com.tomtom.online.sdk.routing.data.InstructionsType;
import com.tomtom.online.sdk.routing.data.Report;
import com.tomtom.online.sdk.routing.data.RouteQuery;
import com.tomtom.online.sdk.routing.data.RouteQueryBuilder;
import com.tomtom.online.sdk.routing.data.RouteType;
import com.tomtom.online.sdk.search.OnlineSearchApi;
import com.tomtom.online.sdk.search.SearchApi;
import com.tomtom.online.sdk.search.data.fuzzy.FuzzySearchQueryBuilder;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    Location location;
    private TomtomMap tomtomMap;
    private MapView mapView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        mapView=root.findViewById(R.id.mapFragment);
        mapView.addOnMapReadyCallback(this);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Map");
        return root;
    }

    @Override
    public void onMapReady(@NonNull TomtomMap map) {
        this.tomtomMap=map;
        tomtomMap.setMyLocationEnabled(true);
        location = tomtomMap.getUserLocation();

        LatLng ahm=new LatLng(23.0225,72.5714);
        tomtomMap.addMarker(new MarkerBuilder(ahm));
        tomtomMap.centerOn(CameraPosition.builder(ahm).zoom(7).build());
//        tomtomMap.getTrafficSettings().turnOnRasterTrafficFlowTiles();
    }
}
