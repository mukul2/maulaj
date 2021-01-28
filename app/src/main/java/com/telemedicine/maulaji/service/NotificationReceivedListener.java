package com.telemedicine.maulaji.service;

import com.telemedicine.maulaji.model.Data;

public class NotificationReceivedListener {

    public  static  NotiReceivedListener listener;
    public  static  interface NotiReceivedListener {
        String onSucced(Data data);


    }

    public static void setMyPicUploadListener(NotiReceivedListener l) {
        NotificationReceivedListener.listener = l;
    }
}
