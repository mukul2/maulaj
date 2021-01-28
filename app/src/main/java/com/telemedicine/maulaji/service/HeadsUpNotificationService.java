package com.telemedicine.maulaji.service;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.telemedicine.maulaji.R;

public class HeadsUpNotificationService extends Service {

    private String CHANNEL_ID = "VoipChannel";
    private String CHANNEL_NAME = "Voip Channel";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle data = null;
        String name = "", callType = "";
        int NOTIFICATION_ID = 120;

        if (intent != null && intent.getExtras() != null) {

            data = intent.getExtras();
            name = data.getString("inititator");
            callType = "Video";

        }
        try {
            Intent receiveCallAction = new Intent(getApplicationContext(), CallNotificationActionReceiver.class);

            receiveCallAction.putExtra("ConstantApp.CALL_RESPONSE_ACTION_KEY", "ConstantApp.CALL_RECEIVE_ACTION");
            receiveCallAction.putExtra("ACTION_TYPE", "RECEIVE_CALL");
            receiveCallAction.putExtra("NOTIFICATION_ID", NOTIFICATION_ID);
            receiveCallAction.setAction("RECEIVE_CALL");

            Intent cancelCallAction = new Intent(getApplicationContext(), CallNotificationActionReceiver.class);
            cancelCallAction.putExtra("ConstantApp.CALL_RESPONSE_ACTION_KEY", "ConstantApp.CALL_CANCEL_ACTION");
            cancelCallAction.putExtra("ACTION_TYPE", "CANCEL_CALL");
            cancelCallAction.putExtra("NOTIFICATION_ID", NOTIFICATION_ID);
            cancelCallAction.setAction("CANCEL_CALL");

            Intent callDialogAction = new Intent(getApplicationContext(), CallNotificationActionReceiver.class);
            callDialogAction.putExtra("ACTION_TYPE", "DIALOG_CALL");
            callDialogAction.putExtra("NOTIFICATION_ID", NOTIFICATION_ID);
            callDialogAction.setAction("DIALOG_CALL");

            PendingIntent receiveCallPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1200, receiveCallAction, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent cancelCallPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1201, cancelCallAction, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent callDialogPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1202, callDialogAction, PendingIntent.FLAG_UPDATE_CURRENT);

            createChannel();
            NotificationCompat.Builder notificationBuilder = null;
            if (data != null) {
                // Uri ringUri= Settings.System.DEFAULT_RINGTONE_URI;
                notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle(name)
                        .setContentText("Incoming " + callType + " Call")
                      //  .setSmallIcon(R.drawable.ic_check_box)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setCategory(NotificationCompat.CATEGORY_CALL)
                      //  .addAction(R.drawable.ic_check_box, "Reject", cancelCallPendingIntent)
                      //  .addAction(R.drawable.ic_check_box, "Answer", receiveCallPendingIntent)
                        .setAutoCancel(true)
                        //.setSound(ringUri)
                        .setFullScreenIntent(callDialogPendingIntent, true);

            }

            Notification incomingCallNotification = null;
            if (notificationBuilder != null) {
                incomingCallNotification = notificationBuilder.build();
            }
            startForeground(NOTIFICATION_ID, incomingCallNotification);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();// release your media player here
    }

    public void createChannel() {
//       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            try {
//                Uri ringUri = Settings.System.DEFAULT_RINGTONE_URI;
//                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
//                channel.setDescription("Call Notifications");
//                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//           /* channel.setSound(ringUri,
//                    new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                            .setLegacyStreamType(AudioManager.STREAM_RING)
//                            .setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION).build());*/
//                Objects.requireNonNull(getSystemService(NotificationManager.class)).createNotificationChannel(channel);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_NAME;
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}