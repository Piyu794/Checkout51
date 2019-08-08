package com.piyush.checkout51.model.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {

    @SerializedName("batch_id")
    private int batchId;

    @SerializedName("offers")
    private List<Item> itemList;

    public int getBatchId() {
        return batchId;
    }

    public List<Item> getItemList() {
        return itemList;
    }
}
