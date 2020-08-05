package com.example.womensafety.ui.home;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.DialogCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.womensafety.Account.SharedPreferenceConfig;
import com.example.womensafety.Login;
import com.example.womensafety.R;
import com.example.womensafety.URL;
import com.example.womensafety.Validation;
import com.example.womensafety.homepage;
import com.example.womensafety.utils.service.MTouchService;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.womensafety.utils.StringUtil.REQUEST_CODE;

public class HomeFragment extends Fragment {
    private SharedPreferenceConfig sharedPreferenceConfig;
    private CardView addcontact, showcontact;
    Button contact,start;
    Geocoder geocoder;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        addcontact = root.findViewById(R.id.add_contact);

        showcontact = root.findViewById(R.id.show_contact);
        progressBar=root.findViewById(R.id.progress_horizontal);
        progressBar.setVisibility(View.GONE);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");

        contact=root.findViewById(R.id.contact);
        start=root.findViewById(R.id.start);

        return root;
    }
    /*check if my service is running
     * */
    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (MTouchService.class.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        sharedPreferenceConfig=new SharedPreferenceConfig(getActivity());
//        Toast.makeText(getActivity(), SharedPreferenceConfig.getHomepageView()   , Toast.LENGTH_SHORT).show();

        TextView friend_contact1,friend_contact2,family_contact1,family_contact2;
        friend_contact1=getActivity().findViewById(R.id.friend_contact1);
        family_contact1=getActivity().findViewById(R.id.family_contact1);
        friend_contact2=getActivity().findViewById(R.id.friend_contact2);
        family_contact2=getActivity().findViewById(R.id.family_contact2);
        Button start=getActivity().findViewById(R.id.start);
//        start.setText(SharedPreferenceConfig.getContactButton());
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean getServiceState = isMyServiceRunning();
                /*check the state of the service if running or not
                 * */
                if (getServiceState) {
                    getActivity().stopService(new Intent(getActivity(), MTouchService.class));
                    SharedPreferenceConfig.setContactButton("ON");
                    start.setText(SharedPreferenceConfig.getContactButton());
                } else {
                    getActivity().startService(new Intent(getActivity(), MTouchService.class));
                    SharedPreferenceConfig.setContactButton("OFF");
                    start.setText(SharedPreferenceConfig.getContactButton());
                }

            }
        });


        if (SharedPreferenceConfig.getHomepageView().equals("false")) {

            addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1, e2,e3,e4;
                    View view = View.inflate(getActivity(), R.layout.add_contact_modal, null);
                    e1 = view.findViewById(R.id.contact1_family);
                    e2 = view.findViewById(R.id.contact2_family);
                    e3 = view.findViewById(R.id.contact1_friend);
                    e4 = view.findViewById(R.id.contact2_friend);
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setView(view);
                    alert.setIcon(getResources().getDrawable(
                            R.drawable.ic_launcher));
                    alert.setTitle("Add Contact");
                    if(!Validation.isValidMobile(e1.getText().toString())){
                        e1.setError("Enter Valid Mobile Number");
                    }else if(!Validation.isValidMobile(e2.getText().toString())){
                        e2.setError("Enter Valid Mobile Number");
                    }
                alert.setPositiveButton("Add Contact", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);
                        RequestQueue queue = Volley.newRequestQueue(getActivity());
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.url + "account/add_contact.php?cid="+SharedPreferenceConfig.getCid()+"&contact1=" + e1.getText().toString() + "&contact2=" + e2.getText().toString()+"&contact3=" + e3.getText().toString() + "&contact4=" + e4.getText().toString(), new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getActivity(), "Contect Added Successfully", Toast.LENGTH_SHORT).show();
                                addcontact.setVisibility(View.GONE);
                                showcontact.setVisibility(View.VISIBLE);
                                sharedPreferenceConfig.setHomepageView("true");
                                    getFragmentManager().beginTransaction().detach(HomeFragment.this).commit();
                                getFragmentManager().beginTransaction().attach(HomeFragment.this).commit();

                                progressBar.setVisibility(View.GONE);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        queue.add(stringRequest);
                    }
                });
                    alert.show();
            }
        });
        } else if (SharedPreferenceConfig.getHomepageView().equals("true")) {
            addcontact.setVisibility(View.GONE);
            showcontact.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.url + "account/show_contact.php?cid="+SharedPreferenceConfig.getCid(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject contact = array.getJSONObject(i);
                            family_contact1.setText(contact.getString("contact1"));
                            friend_contact1.setText(contact.getString("contact3"));
                            family_contact2.setText(contact.getString("contact2"));
                            friend_contact2.setText(contact.getString("contact4"));
                            sharedPreferenceConfig.setFamilyContact1(contact.getString("contact1"));
                            sharedPreferenceConfig.setFriendContact1(contact.getString("contact2"));
                            sharedPreferenceConfig.setFamilyContact2(contact.getString("contact3"));
                            sharedPreferenceConfig.setFriendContact2(contact.getString("contact4"));
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

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String SENT_SMS_ACTION_NAME = "SMS_SENT";
                final String DELIVERED_SMS_ACTION_NAME = "SMS_DELIVERED";

                if (SharedPreferenceConfig.getHomepageView().equals("false")) {

                    Snackbar.make(v, "Please Add Family And Friend Contact First", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if(SharedPreferenceConfig.getHomepageView().equals("true")) {
                    LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

                    Intent intent = new Intent(getActivity(), homepage.class);
                    PendingIntent pi = PendingIntent.getActivity(getActivity(), 0, intent, 0);
                    //Get the SmsManager instance and call the sendTextMessage method to send message
                    SmsManager sms = SmsManager.getDefault();
                    PendingIntent sentPI = PendingIntent.getBroadcast(getActivity(), 0, new Intent(SENT_SMS_ACTION_NAME), 0);
                    PendingIntent deliveredPI = PendingIntent.getBroadcast(getActivity(), 0, new Intent(DELIVERED_SMS_ACTION_NAME), 0);

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
        });
    }
    public static void sendSms(){

    }
}
