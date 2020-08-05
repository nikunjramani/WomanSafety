package com.example.womensafety.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.womensafety.Login;
import com.example.womensafety.R;
import com.example.womensafety.URL;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class editAccount extends AppCompatActivity {

    TextInputEditText name,email,mobileno;
    Button submit;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        progressBar=findViewById(R.id.progress_horizontal);
        progressBar.setVisibility(View.GONE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mobileno=findViewById(R.id.mobileno);
        name=findViewById(R.id.firstname);
        email=findViewById(R.id.email);
        submit=findViewById(R.id.submit);
        getUserData();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                RequestQueue queue= Volley.newRequestQueue(editAccount.this);
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URL.url+"account/edit_details.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(editAccount.this, "Update Successfully", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                } ,new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<String, String>();
                        param.put("cid",SharedPreferenceConfig.getCid());
                        param.put("name",name.getText().toString());
                        param.put("email",email.getText().toString());
                        param.put("mobileno",mobileno.getText().toString());
                        return param;
                    }
                };
                queue.add(stringRequest);

            }
        });
    }
    void getUserData(){
        progressBar.setVisibility(View.VISIBLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestQueue queue= Volley.newRequestQueue(editAccount.this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL.url+"account/show_client_details.php?id="+ SharedPreferenceConfig.getCid(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject contact = array.getJSONObject(i);
                        name.setText(contact.getString("name"));
                        email.setText(contact.getString("email"));
                        mobileno.setText(contact.getString("mobileno"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        } ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
