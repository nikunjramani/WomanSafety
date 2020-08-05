package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.womensafety.Account.SharedPreferenceConfig;
import com.example.womensafety.Account.signup;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    ProgressBar progressBar;
    SharedPreferenceConfig sharedPreferenceConfig;
    TextInputEditText email,password;
    Button login1;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        progressBar=findViewById(R.id.progress_horizontal);
        progressBar.setVisibility(View.GONE);
        sharedPreferenceConfig = new SharedPreferenceConfig(Login.this);

        if (sharedPreferenceConfig.ReadLoginStatus()) {
            startActivity(new Intent(Login.this, homepage.class));
            finish();
        }
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        signup=findViewById(R.id.singup);
        login1=findViewById(R.id.login);


        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue= Volley.newRequestQueue(Login.this);

                if(!Validation.isValidEmail(email.getText().toString())){
                    email.setError("Please Enter Valid Email");
                }else if(!Validation.isValidPassword(password.getText().toString())){
                    password.setError("Please Enter Valid Password");
                }else {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest=new StringRequest(Request.Method.GET, URL.url+"account/login.php?email="+email.getText().toString()+"&password="+password.getText().toString(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                            if(response.equals("1")) {
                                getdata();
                            }else{
                                Toast.makeText(Login.this, "Enter Valid Details", Toast.LENGTH_SHORT).show();
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
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, signup.class));

            }
        });
    }
    void getdata(){
        RequestQueue queue= Volley.newRequestQueue(Login.this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL.url+"account/getid.php?email="+email.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sharedPreferenceConfig.WriteLoginStatus(true);
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject contact = array.getJSONObject(i);
                        sharedPreferenceConfig.getEmaill(contact.getString("cid"));
                        sharedPreferenceConfig.setHomepageView(contact.getString("value"));
                        startActivity(new Intent(Login.this, homepage.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // or finish();

    }
}
