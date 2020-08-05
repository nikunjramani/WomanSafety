package com.example.womensafety.Account;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.womensafety.Login;
import com.example.womensafety.URL;
import com.example.womensafety.homepage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.womensafety.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class account extends Fragment {
    TextView fullname,name,mobileno,email,editaccount,changepassword,logout;
    SharedPreferenceConfig sharedPreferenceConfig;
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_account, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Account");
        fullname=root.findViewById(R.id.account_fullname);
        name=root.findViewById(R.id.account_name);
        mobileno=root.findViewById(R.id.account_mobileno);
        email=root.findViewById(R.id.account_email);
        editaccount=root.findViewById(R.id.editaccount);
        changepassword=root.findViewById(R.id.changepassword);
        logout=root.findViewById(R.id.logout);
        progressBar=root.findViewById(R.id.progress_horizontal);
        progressBar.setVisibility(View.GONE);
        sharedPreferenceConfig=new SharedPreferenceConfig(getActivity());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUserData();
        editaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),editAccount.class));
            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),changePassword.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceConfig.WriteLoginStatus(false);
                startActivity(new Intent(getActivity(),Login.class));
            }
        });

    }
    void getUserData(){
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL.url+"account/show_client_details.php?id="+ SharedPreferenceConfig.getCid(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject contact = array.getJSONObject(i);
                        name.setText(contact.getString("name"));
                        fullname.setText(contact.getString("name"));
                        email.setText(contact.getString("email"));
                        mobileno.setText(contact.getString("mobileno"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }
        } ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
