package com.telemedicine.maulaji.Utils;

public class LocationPermisionGaveListener {
    public static     LocationRetiveListener listener;

    public  static void setNeedRefresh(LocationRetiveListener r ) {
        listener = r;
    }

    public  interface LocationRetiveListener{
        void doRefresh();

    }
}