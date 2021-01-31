package com.telemedicine.maulaji.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by riad on 1/21/2018.
 */

public class ApiClientCodIgntrApi {

    private static final String BASE_URL = "https://callgpnow.com/public/auth/";
    private static Retrofit retrofit = null;

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApiInterface() {
        return getClient().create(ApiInterface.class);
    }
}
