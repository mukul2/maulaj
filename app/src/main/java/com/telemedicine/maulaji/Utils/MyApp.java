package com.telemedicine.maulaji.Utils;

import android.app.Application;

import com.stripe.android.PaymentConfiguration;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PaymentConfiguration.init(
            getApplicationContext(),
            "pk_test_n8Hp30Vzoo2rKWREZr6cZtu600irOIK4Jl"
        );
    }
}