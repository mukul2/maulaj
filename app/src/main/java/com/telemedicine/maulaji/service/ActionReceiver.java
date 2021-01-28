package com.telemedicine.maulaji.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.telemedicine.maulaji.Activity.PatientHomeActivity;


public class ActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //Toast.makeText(context,"recieved",Toast.LENGTH_SHORT).show();

        String action=intent.getStringExtra("action");
        if(action.equals("action1")){
            context.startActivity(new Intent(context, PatientHomeActivity.class));
        }
        else if(action.equals("action2")){
            context.startActivity(new Intent(context, PatientHomeActivity.class));

        }
        //This is used to close the notification tray
        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);
    }

    public void performAction1(){


    }

    public void performAction2(){

    }

}