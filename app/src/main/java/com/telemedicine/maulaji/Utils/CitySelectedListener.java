package com.telemedicine.maulaji.Utils;

public class CitySelectedListener {

    public  static  CityCelectListener listener;
    public  static  interface CityCelectListener {
        void onSelectedSucced(String link);


    }

    public static void setListener(CityCelectListener l) {
        CitySelectedListener.listener = l;
    }
}
