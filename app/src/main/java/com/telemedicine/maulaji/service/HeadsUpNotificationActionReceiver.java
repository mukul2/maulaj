package com.telemedicine.maulaji.service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.telemedicine.maulaji.Activity.PatientHomeActivity;


public class HeadsUpNotificationActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getExtras() != null) {
            String action = intent.getStringExtra(ConstantApp.CALL_RESPONSE_ACTION_KEY);
            Bundle data = intent.getBundleExtra(ConstantApp.FCM_DATA_KEY);

            if (action != null) {
                performClickAction(context, action, data);
            }

            // Close the notification after the click action is performed.

            Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            context.sendBroadcast(it);
            context.stopService(new Intent(context, HeadsUpNotificationService.class));
        }
    }

    private void performClickAction(Context context, String action, Bundle data) {
        if (action.equals(ConstantApp.CALL_RECEIVE_ACTION) && data != null && data.get("type").equals("voip")) {
            Intent openIntent = null;
            openIntent = new Intent(context, PatientHomeActivity.class);
            openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(openIntent);
        } else if (action.equals(ConstantApp.CALL_RECEIVE_ACTION) && data != null && data.get("type").equals("video")) {
            Intent openIntent = null;
            openIntent = new Intent(context, PatientHomeActivity.class);
            openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(openIntent);
        } else if (action.equals(ConstantApp.CALL_CANCEL_ACTION)) {
            context.stopService(new Intent(context, HeadsUpNotificationService.class));
            Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            context.sendBroadcast(it);
        }
    }
}