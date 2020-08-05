package com.example.womensafety.Account;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.womensafety.R;

public class SharedPreferenceConfig {
    private static SharedPreferences sharedPreferences;
    Context ctx;

    public SharedPreferenceConfig(Context ctx) {
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences(ctx.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);

    }

    public void WriteLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(ctx.getResources().getString(R.string.login_status_preference), status);
        editor.commit();
    }

    public boolean ReadLoginStatus() {
        boolean status = false;
        status = sharedPreferences.getBoolean(ctx.getResources().getString(R.string.login_status_preference), false);
        return status;
    }
    public static String getCid()
    {
        return sharedPreferences.getString("cid",null);
    }
    public void getEmaill(String cid) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cid",cid);
            editor.apply();
    }
    public void setHomepageView(String view) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("view", view);
            editor.apply();
    }
    public static String getHomepageView()
    {
        return sharedPreferences.getString("view","false");
    }

    public void setFriendContact1(String contact) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("friend1", contact);
        editor.apply();
    }
    public static String getFriendContact1()
    {
        return sharedPreferences.getString("friend1","false");
    }

    public void setFamilyContact1(String family) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("family1", family);
        editor.apply();
    }
    public static String getFamilyContact1()
    {
        return sharedPreferences.getString("family1","false");
    }
    public void setFriendContact2(String contact) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("friend2", contact);
        editor.apply();
    }
    public static String getFriendContact2()
    {
        return sharedPreferences.getString("friend2","false");
    }

    public void setFamilyContact2(String family) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("family2", family);
        editor.apply();
    }
    public static String getFamilyContact2()
    {
        return sharedPreferences.getString("family2","false");
    }
    public static void setContactButton(String family) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("button", family);
        editor.apply();
    }
    public static String getContactButton()
    {
        return sharedPreferences.getString("button","ON");
    }
}
