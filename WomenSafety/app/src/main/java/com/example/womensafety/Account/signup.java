package com.example.womensafety.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.womensafety.Validation;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    TextInputEditText name,number,email,password;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        getSupportActionBar().hide();
        name=findViewById(R.id.name);
        number=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        ProgressBar progressBar;
        progressBar=findViewById(R.id.progress_horizontal);
        progressBar.setVisibility(View.GONE);

        submit=findViewById(R.id.singup);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue= Volley.newRequestQueue(signup.this);

                if(!Validation.isValidEmail(email.getText().toString())){
                    email.setError("Please Enter Valid Email");
                }else if(!Validation.isValidPassword(password.getText().toString())){
                    password.setError("Password Must Be 8 Character");
                }else if(!Validation.isValidMobile(number.getText().toString())){
                    number.setError("Please Enter Valid Mobile Number");
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, URL.url+"account/singup.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("1")){
                                Toast.makeText(signup.this, response, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(signup.this,Login.class));
                            }else {
                                Toast.makeText(signup.this, "Something Wrong Try Again Letter", Toast.LENGTH_SHORT).show();
                            }
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
                            param.put("name",name.getText().toString());
                            param.put("email",email.getText().toString());
                            param.put("mobileno",number.getText().toString());
                            param.put("password",password.getText().toString());
                            return param;
                        }
                    };
                    queue.add(stringRequest);
                }

            }
        });
    }
}
