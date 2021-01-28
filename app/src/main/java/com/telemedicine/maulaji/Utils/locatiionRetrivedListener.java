package com.telemedicine.maulaji.Utils;

import android.location.Location;

public class locatiionRetrivedListener {

    public  static  myLocationRetriver myLocationRetriver_;
    public  static  interface myLocationRetriver {
        Location onLocationretrived(Location location, String address, String cityName, String coun);
    }

    public static void setMyLocationRetriver_(myLocationRetriver m_) {
        locatiionRetrivedListener.myLocationRetriver_ = m_;
    }
}
