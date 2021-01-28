package com.telemedicine.maulaji.service;

import android.util.Log;

import com.telemedicine.maulaji.model.Data;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.telemedicine.maulaji.Data.Data.NOTI_DATA;


public class NotificationService extends FirebaseMessagingService {
    public NotificationService() {
    }
    String title = "No title";
    String body ="No Body";
    String intent = "no";
    String targetUserType = "no";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //Toast.makeText(this, "helo", Toast.LENGTH_SHORT).show();
        NotificationHelper notificationHelper = new NotificationHelper(NotificationService.this);

        Log.i("mklnoti",remoteMessage.getData().toString());




        String title = remoteMessage.getData().get("title").toString();
        String body = remoteMessage.getData().get("body").toString();
        String type = remoteMessage.getData().get("type").toString();
        String room = remoteMessage.getData().get("room").toString();
        String extraData = remoteMessage.getData().get("extraData").toString();
        notificationHelper.createNotification(title,  body,type, room);
        NOTI_DATA = new Data(type,room,title,body,extraData);
        NotificationReceivedListener.listener.onSucced(new Data(type,room,title,body,extraData));//the life saver line
       if (false) {

//           if (remoteMessage.getData().get("image") != null && remoteMessage.getData().get("image").length() > 0) {
//               String link = remoteMessage.getData().get("image");
//               returnBitmap(link, new AsyncTask.downloadListener() {
//                   @Override
//                   public void onDownloaded(Bitmap bitmap) {
//                     //  notificationHelper.createNotification(title,  body,intent, bitmap,targetUserType);
//
//
//                   }
//               });


           } else {
            //   notificationHelper.createNotification(title,  body,intent, null,targetUserType);

           }
       }

    }

