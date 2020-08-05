package com.example.womensafety.utils.schedule;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


import com.example.womensafety.utils.service.MTouchService;

import static android.content.ContentValues.TAG;
import static com.example.womensafety.utils.StringUtil.NAME;
import static com.example.womensafety.utils.StringUtil.PREF_NAME;


public class Util {
    private static final long REFRESH_INTERVAL = 5 * 1000;

    /*Jobschedule
     * to make a continous
     * running
     * service*/
    public static void scheduleJob(Context context) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
                ComponentName serviceComponent = new ComponentName(context, TestJobService.class);
                JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
                builder.setMinimumLatency(1000); //wait at least 1 sec
                builder.setPeriodic(REFRESH_INTERVAL);
                builder.setOverrideDeadline(7 * 1000); // maximum delay 3 sec
                builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
                builder.setRequiresDeviceIdle(false); // device should be idle
                builder.setRequiresCharging(false); // we don't care if the device is charging or not
                JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
                assert jobScheduler != null;
                jobScheduler.schedule(builder.build());


                checkIfrunningAfterBoot(context);
            }
        }catch (Exception e){
            Log.i(TAG, "scheduleJob: argument exception error");
        }

    }

    /*check if the service is running
     * after reboot or boot
     * and restart it if it was enabled */
    private static void checkIfrunningAfterBoot(Context context) {
        SharedPreferences sharedPreferences;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        if (sharedPreferences.contains(NAME)){
            String d=sharedPreferences.getString(NAME,"");
            if (d.contains("on")){
                Intent mfloat=new Intent(context, MTouchService.class);
                context.startService(mfloat);
            }else {
                Log.i(TAG, "scheduleJob: shared preference is OFF");
            }
        }
    }

}
