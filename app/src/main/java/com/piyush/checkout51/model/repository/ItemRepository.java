package com.piyush.checkout51.model.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.piyush.checkout51.model.network.WebApi;
import com.piyush.checkout51.model.network.WebClient;
import com.piyush.checkout51.model.responses.Item;
import com.piyush.checkout51.model.responses.ItemResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {

    private static ItemRepository repository;
    private WebApi api;

    //To Initialize Api Reference
    private ItemRepository() {
        api = WebClient.getClient();
    }

    //To Get ItemRepository Object
    public static ItemRepository getRepositoryInstance() {
        if (repository == null)
            repository = new ItemRepository();
        return repository;
    }

    //To Fetch List Of Items Over Network
    public MutableLiveData<List<Item>> fetchItemsList() {
        final MutableLiveData<List<Item>> itemsList = new MutableLiveData<>();
        api.getItems().enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemResponse> call, @NonNull Response<ItemResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                    itemsList.setValue(response.body().getItemList());
            }

            @Override
            public void onFailure(@NonNull Call<ItemResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
                itemsList.setValue(new ArrayList<>());
            }
        });
        return itemsList;
    }
}
