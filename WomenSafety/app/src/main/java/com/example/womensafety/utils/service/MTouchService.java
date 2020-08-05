package com.example.womensafety.utils.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.example.womensafety.Account.SharedPreferenceConfig;
import com.example.womensafety.R;
import com.example.womensafety.homepage;
import com.example.womensafety.ui.home.sendLocation;
import com.example.womensafety.utils.paramsInnitializer;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.womensafety.utils.StringUtil.NAME;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class MTouchService extends Service implements View.OnTouchListener, View.OnClickListener {

    private RelativeLayout.LayoutParams params_imageview;
    private RelativeLayout relativeLayout;
    private ImageView overlayedButton;
    private boolean moving;
    private WindowManager windowManager;
    private int initialX;
    private int initialY;
    private float initialTouchX;
    private float initialTouchY;
    private int mWidth;
    final String SENT_SMS_ACTION_NAME = "SMS_SENT";
    final String DELIVERED_SMS_ACTION_NAME = "SMS_DELIVERED";
    Geocoder geocoder;
    private static final String PREF_NAME="MTouch";
    SharedPreferences sharedPreferences;
    private RestartService restartService;
    private final int btnOverlay = View.generateViewId();
    private com.example.womensafety.utils.paramsInnitializer paramsInnitializer = new paramsInnitializer();
/// find a way to get rid of tempoview
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate() {
        /*initializing  restart service
         * and
         * floating settingButton classes*/
        restartService = new RestartService(this);

        /*
         * create
         * the
         * floating touch*/
        createMtouch();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void createMtouch() {

        /*windowmanager
         * to allow
         * drawing on the screen*/
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        /*initializing
         * imageview
         * to be displayed
         * and giving it params*/
        overlayedButton = new ImageView(this);
        overlayedButton.setImageResource(R.drawable.drawble_icon);
        //setting id for the overlay button
        overlayedButton.setId(btnOverlay);
        overlayedButton.setAlpha(0.7f);
        geocoder = new Geocoder(MTouchService.this, Locale.getDefault());
        overlayedButton.setOnTouchListener(this);
        overlayedButton.setOnClickListener(this);



        /*setting
         *Windowmanager params*/
//        paramsInnitializer paramsInnitializer = new paramsInnitializer();

        paramsInnitializer.setxCoordinate(0);
        paramsInnitializer.setyCoordinate(150);
        WindowManager.LayoutParams params = paramsInnitializer.wmInnitializer(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.START | Gravity.TOP;

        /*relative layout getting the params of windowmanager*/
        relativeLayout = new RelativeLayout(this);
        params_imageview = new RelativeLayout.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        params_imageview.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        /*adding view to the relative layout*/
        relativeLayout.addView(overlayedButton, params_imageview);
        /*adding
         * the
         * relative layout inside the windowmanager*/
        windowManager.addView(relativeLayout, params);

        /*get screen width */
        getScreenWidth();
    }

    private void getScreenWidth() {
        //get screen width
        Display display=windowManager.getDefaultDisplay();
        final Point size=new Point();
        display.getSize(size);


        ViewTreeObserver vto=relativeLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = relativeLayout.getMeasuredWidth();
                mWidth = size.x - width;
            }
        });
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        /*on clearing recent apps
         * check if the service prefernce
         * was saved then restart it*/
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(NAME)) {
            //String d = sharedPreferences.getString(NAME, "");

            /*
             * restart the service*/
            restartService.restart();
        } else {
            Toast.makeText(this, "Oop!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (overlayedButton != null) {
            windowManager.removeView(relativeLayout);
            overlayedButton = null;
        }

    }

    /*onclick
     * show the menu*/
    @Override
    public void onClick(View view) {
        showWindow();
    }

    private void showWindow() {

        /*inflate the layout*/
        final LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        if (SharedPreferenceConfig.getHomepageView().equals("false")) {

            Toast.makeText(this, "Please Add Family And Friend Contact First", Toast.LENGTH_SHORT).show();
        } else if(SharedPreferenceConfig.getHomepageView().equals("true")) {
            LocationManager locationManager = (LocationManager) MTouchService.this.getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(MTouchService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MTouchService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new sendLocation.MyLocationListener());
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            List<Address> addressList = null;
            try {
                addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address obj = addressList.get(0);
            String address = obj.getAddressLine(0);

            String sms_msg="[Emergency SOS] Please Help Me \n My Current Location Is \n" + address + "\n Tap the followig link to check my Location \n http://maps.google.com/maps?f=q&q=" + location.getLatitude() + "," + location.getLongitude();
            //Getting intent and PendingIntent instance
            SmsManager sms = SmsManager.getDefault();
            Intent intent = new Intent(MTouchService.this, homepage.class);
            PendingIntent sentPI = PendingIntent.getBroadcast(MTouchService.this, 0, new Intent(SENT_SMS_ACTION_NAME), 0);
            PendingIntent deliveredPI = PendingIntent.getBroadcast(MTouchService.this, 0, new Intent(DELIVERED_SMS_ACTION_NAME), 0);
            ArrayList<String> parts = sms.divideMessage(sms_msg);

            ArrayList<PendingIntent> sendList = new ArrayList<>();
            sendList.add(sentPI);

            ArrayList<PendingIntent> deliverList = new ArrayList<>();
            deliverList.add(deliveredPI);
            sms.sendMultipartTextMessage(SharedPreferenceConfig.getFamilyContact1(), null, parts, sendList, deliverList);
            sms.sendMultipartTextMessage(SharedPreferenceConfig.getFamilyContact2(), null, parts, sendList, deliverList);
            sms.sendMultipartTextMessage(SharedPreferenceConfig.getFriendContact1(), null, parts, sendList, deliverList);
            sms.sendMultipartTextMessage(SharedPreferenceConfig.getFriendContact1(), null, parts, sendList, deliverList);

        }


    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        WindowManager.LayoutParams params = (WindowManager.LayoutParams) relativeLayout.getLayoutParams();

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            //initial position
            initialX = paramsInnitializer.getxCoordinate();
            initialY = paramsInnitializer.getyCoordinate();

            //touch locationn
            initialTouchX = motionEvent.getRawX();
            initialTouchY = motionEvent.getRawY();

            return moving;


        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {

            //calculate x and y coordinate of view

            paramsInnitializer.setxCoordinate(initialX + (int) (motionEvent.getRawX() - initialTouchX));
            paramsInnitializer.setyCoordinate(initialY + (int) (motionEvent.getRawY() - initialTouchY));

            params.x = paramsInnitializer.getxCoordinate();
            params.y = paramsInnitializer.getyCoordinate();


            //update the layout
            windowManager.updateViewLayout(relativeLayout, params);

            moving = false;


        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

            //position to left | right always
            int middle = mWidth / 2;
            float nearestXWall = params.x >= middle ? mWidth : 0;

            params.x = (int) nearestXWall;
            paramsInnitializer.setxCoordinate(params.x);

            windowManager.updateViewLayout(relativeLayout, params);

            return moving;

        }

        return false;

    }



}
