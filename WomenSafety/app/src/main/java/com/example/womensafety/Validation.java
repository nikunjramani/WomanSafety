package com.example.womensafety;

import android.text.TextUtils;
import android.util.Patterns;

public class Validation {
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isValidMobile(String phone) {
        if(phone.length() == 10){
            return true;
        }else{
            return false;
        }
    }
    public  static boolean isValidPassword(String pass){
        if(pass.length() >= 8){
            return true;
        }else{
            return false;
        }
    }


}
