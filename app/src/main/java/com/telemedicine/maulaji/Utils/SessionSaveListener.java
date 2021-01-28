package com.telemedicine.maulaji.Utils;

public class SessionSaveListener {

    public  static  SesssionSaveListener listener;
    public  static  interface SesssionSaveListener {
        void onSucced();


    }

    public static void setListener(SessionSaveListener.SesssionSaveListener l) {
        SessionSaveListener.listener = l;
    }
}
