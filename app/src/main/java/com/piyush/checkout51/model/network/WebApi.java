package com.piyush.checkout51.model.network;

import com.piyush.checkout51.model.responses.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebApi {

    //Api Endpoint to fetch Items
    @GET("bins/nd6ct")
    Call<ItemResponse> getItems();
}
