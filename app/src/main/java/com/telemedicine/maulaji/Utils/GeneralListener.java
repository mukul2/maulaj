package com.telemedicine.maulaji.Utils;

public class GeneralListener {
    public static     youNeedRefresh needRefresh;

    public  static void setNeedRefresh(youNeedRefresh r ) {
        needRefresh = r;
    }

    public  interface youNeedRefresh{
        void doRefresh(int value);

    }
}