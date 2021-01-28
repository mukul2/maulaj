package com.telemedicine.maulaji.api;

import com.google.gson.JsonObject;
import com.telemedicine.maulaji.model.SearchedPlaceModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mohak on 2/8/17.
 */

public interface getPlacesApiData {


    @GET("json")
    Call<SearchedPlaceModel> getPlaces(
            @Query("input") String input,
            @Query("location") String location,
            @Query("key") String key);

//    @GET("maps/api/directions/json")
//    Call Single<Result> getDirections(@Query("mode") String mode,
//                                        @Query("transit_routing_preference") String routingPreference,
//                                        @Query("origin") String origin, @Query("destination"), String destination, @Query("key") String key);
}