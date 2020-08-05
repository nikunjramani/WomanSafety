package com.example.womensafety.utils.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class RestartService {
    private Context context;

    public RestartService(Context context) {
        this.context = context;
    }

    public void restart() {
        /*get the intent to be restarted */
        Intent restartServiceIntent = new Intent(context.getApplicationContext(),
                this.getClass());
        restartServiceIntent.setPackage(context.getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(
                context.getApplicationContext(), 1, restartServiceIntent,
                PendingIntent.FLAG_ONE_SHOT);
        /*Alam manager to restart the service
         * whenever it is closed*/
        AlarmManager alarmService = (AlarmManager) context.getApplicationContext()
                .getSystemService(Context.ALARM_SERVICE);
        alarmService.set(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);

    }

}
