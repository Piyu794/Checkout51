package com.piyush.checkout51.model.responses;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("offer_id")
    private String offerId;
    @SerializedName("name")
    private String offerName;
    @SerializedName("image_url")
    private String offerImage;
    @SerializedName("cash_back")
    private double cashBack;

    public String getOfferId() {
        return offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public double getCashBack() {
        return cashBack;
    }
}
