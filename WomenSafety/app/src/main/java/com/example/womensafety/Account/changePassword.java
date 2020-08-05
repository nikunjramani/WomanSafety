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
import com.example.womensafety.Validation;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class changePassword extends AppCompatActivity {

    TextInputEditText pass1,pass2;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ProgressBar progressBar;
        progressBar=findViewById(R.id.progress_horizontal);
        progressBar.setVisibility(View.GONE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pass1=findViewById(R.id.password1);
        pass2=findViewById(R.id.password2);
        submit=findViewById(R.id.change_password);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Validation.isValidPassword(pass1.getText().toString())){
                    pass1.setError("Enter Valid Password");
                }else if(!Validation.isValidPassword(pass2.getText().toString())){
                    pass2.setError("Enter Valid Password");
                }else if(!pass1.equals(pass2)){
                    Toast.makeText(changePassword.this, "Password Must Be Same", Toast.LENGTH_SHORT).show();
                }

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.VISIBLE);
                RequestQueue queue= Volley.newRequestQueue(changePassword.this);
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URL.url+"account/changepassword.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(changePassword.this, "Update Successfully", Toast.LENGTH_LONG).show();
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
                        param.put("password",pass2.getText().toString());
                        return param;
                    }
                };
                queue.add(stringRequest);

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
