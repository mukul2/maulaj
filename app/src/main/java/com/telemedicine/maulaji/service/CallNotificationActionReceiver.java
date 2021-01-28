package com.telemedicine.maulaji.service;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.telemedicine.maulaji.Activity.PatientHomeActivity;


public class CallNotificationActionReceiver extends BroadcastReceiver {


Context mContext;

@Override
public void onReceive(Context context, Intent intent) {
    this.mContext=context;
    if (intent != null && intent.getExtras() != null) {
      
        String action ="";
        action=intent.getStringExtra("ACTION_TYPE");

        if (action != null&& !action.equalsIgnoreCase("")) {
            performClickAction(context, action);
        }

        // Close the notification after the click action is performed.
        Intent iclose = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(iclose);
        context.stopService(new Intent(context, HeadsUpNotificationService.class));

    }


}
private void performClickAction(Context context, String action) {
    if(action.equalsIgnoreCase("RECEIVE_CALL")) {

        if (checkAppPermissions(context)) {
        Intent intentCallReceive = new Intent(mContext, PatientHomeActivity.class);
        intentCallReceive.putExtra("Call", "incoming");
            intentCallReceive.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(intentCallReceive);               
        }
        else{
                    Intent intent = new Intent(context, PatientHomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("CallFrom","call from push");
                    mContext.startActivity(intent);                  
            
        }
    }
    else if(action.equalsIgnoreCase("DIALOG_CALL")){

                // show ringing activity when phone is locked
                Intent intent = new Intent(context, PatientHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
            }

    else {            
        context.stopService(new Intent(context, HeadsUpNotificationService.class));
        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);
    }
}

private Boolean checkAppPermissions(Context context) {
    return hasReadPermissions(context) && hasWritePermissions(context) && hasCameraPermissions(context) && hasAudioPermissions(context);
}

private boolean hasAudioPermissions(Context context) {
    return (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED);
}

private boolean hasReadPermissions(Context context) {
    return (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
}

private boolean hasWritePermissions(Context context) {
    return (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
}
private boolean hasCameraPermissions(Context context) {
    return (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
}
}