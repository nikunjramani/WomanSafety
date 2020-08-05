package com.example.womensafety;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.womensafety.Account.SharedPreferenceConfig;
import com.example.womensafety.Account.account;
import com.example.womensafety.ui.home.HomeFragment;
import com.example.womensafety.ui.home.sendLocation;
import com.example.womensafety.ui.map.MapFragment;
import com.example.womensafety.ui.slideshow.LocationShow;
import com.example.womensafety.utils.service.MTouchService;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.womensafety.utils.StringUtil.NAME;
import static com.example.womensafety.utils.StringUtil.REQUEST_CODE;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button start;
    SharedPreferences mPref;
    SharedPreferences.Editor medit;
    SharedPreferenceConfig sharedPreferenceConfig;
    TextView name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferenceConfig=new SharedPreferenceConfig(getApplicationContext());
        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        medit = mPref.edit();
        fn_permission();
        Intent intent = new Intent(getApplicationContext(), sendLocation.class);
        startService(intent);
//        startService(new Intent(this, sendLocation.class));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        name=navigationView.getHeaderView(0).findViewById(R.id.nav_name);
        email=navigationView.getHeaderView(0).findViewById(R.id.nav_email);
        getUserData();

        loadFragment(new HomeFragment());
//      checkDrawOverlayPermission();
    }

    /*check if my service is running
     * */
    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (MTouchService.class.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }
    /*floating/overlayed permission request
     * */
    public void checkDrawOverlayPermission() {
        /* check if we already  have permission to draw over other apps */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                /* if not construct intent to request permission */
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                /* request permission via start activity for result */
                startActivityForResult(intent, REQUEST_CODE);

            }
        }
        // createRunningService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean getServiceState = isMyServiceRunning();
        if (getServiceState) {
            startService(new Intent(homepage.this, MTouchService.class));
        } else {
            stopService(new Intent(homepage.this, MTouchService.class));
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        /*request permission if granted or not*/
        if (requestCode == REQUEST_CODE) {
            //not set
            if (!Settings.canDrawOverlays(this)) {
                finish();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void fn_permission() {
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_SMS,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    void getUserData(){
        RequestQueue queue = Volley.newRequestQueue(homepage.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.url + "account/show_client_details.php?id="+SharedPreferenceConfig.getCid(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(homepage.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject contact = array.getJSONObject(i);
                        name.setText(contact.getString("name"));
                        email.setText(contact.getString("email"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        Fragment fragment = null;
        if(id==R.id.nav_logout){
            sharedPreferenceConfig.WriteLoginStatus(false);
            startActivity(new Intent(homepage.this,Login.class));
        }else if(id==R.id.nav_home){
            fragment=new HomeFragment();

        }else if(id==R.id.nav_account){
            fragment=new account();

        }else if(id==R.id.nav_gallery){
            fragment=new MapFragment();
        }else if(id==R.id.nav_slideshow){
            fragment=new LocationShow();
        }else {
            fragment=new HomeFragment();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return loadFragment(fragment);
    }
    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

