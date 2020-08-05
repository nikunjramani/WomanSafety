package com.example.womensafety.utils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.womensafety.utils.schedule.Util;
import com.example.womensafety.utils.service.MTouchService;

public class BootReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Util.scheduleJob(context);
        }else {
            context.startService(new Intent(context, MTouchService.class));
        }


    }

}
