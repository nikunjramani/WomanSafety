package com.example.womensafety;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.womensafety.Account.SharedPreferenceConfig;

public class StatrPage extends AppCompatActivity {
    SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceConfig=new SharedPreferenceConfig(StatrPage.this);
        setContentView(R.layout.woman_sefty);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferenceConfig.ReadLoginStatus()) {
                    startActivity(new Intent(StatrPage.this, homepage.class));
                    finish();
                }else {
                    startActivity(new Intent(StatrPage.this, Login.class));
                    finish();
                }
            }
        }, 2000);


    }
}
