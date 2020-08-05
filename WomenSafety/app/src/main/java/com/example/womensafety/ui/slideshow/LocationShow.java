package com.example.womensafety.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.womensafety.Account.SharedPreferenceConfig;
import com.example.womensafety.R;
import com.example.womensafety.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocationShow extends Fragment {

    private LocationAdapter adapter;
    private RecyclerView recyclerView;
    private List<showLocation> addressList;
    ProgressBar progressBar;
    TextView t1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
       recyclerView= (RecyclerView) root.findViewById(R.id.show_location);
       t1=root.findViewById(R.id.t1);
       LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        progressBar=root.findViewById(R.id.progress_horizontal);
        progressBar.setVisibility(View.GONE);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("View Location");
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        addressList=new ArrayList<>();
        getData();
        return root;
    }
    void getData(){
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.url + "location/show_location.php?cid="+ SharedPreferenceConfig.getCid(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    if(array.length() == 0){
                           t1.setVisibility(View.VISIBLE);
                           recyclerView.setVisibility(View.GONE);
                    }else {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject contact = array.getJSONObject(i);
                            addressList.add(new showLocation(
                                    contact.getString("id"),
                                    contact.getString("latitude"),
                                    contact.getString("longitute"),
                                    contact.getString("datetime"),
                                    contact.getString("cid")
                            ));
                            adapter = new LocationAdapter(getActivity(), addressList);
                            recyclerView.setAdapter(adapter);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
