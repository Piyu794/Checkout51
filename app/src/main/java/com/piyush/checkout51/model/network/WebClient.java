package com.piyush.checkout51.model.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {
    //Create Retrofit Object & Passing The Api Reference
    public static WebApi getClient() {
        return new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WebApi.class);
    }
}
