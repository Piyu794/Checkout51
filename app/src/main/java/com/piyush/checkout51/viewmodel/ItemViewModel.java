package com.piyush.checkout51.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.piyush.checkout51.model.repository.ItemRepository;
import com.piyush.checkout51.model.responses.Item;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ItemViewModel extends ViewModel {

    //List To Store Items Fetched From Network
    private MutableLiveData<List<Item>> itemsList;

    //To Getting Items & Exposing So View Can Observe It
    public MutableLiveData<List<Item>> getItemsList() {
        if (itemsList == null)
            itemsList = ItemRepository.getRepositoryInstance().fetchItemsList();
        return itemsList;
    }

    //To Sort By Name (Ascending) & Expose Result So View Can Observe It
    public MutableLiveData<List<Item>> sortByNameAsc() {
        Collections.sort(Objects.requireNonNull(itemsList.getValue()), (item, t1) -> item.getOfferName().compareToIgnoreCase(t1.getOfferName()));
        return itemsList;
    }

    //To Sort By Name (Descending) & Expose Result So View Can Observe It
    public MutableLiveData<List<Item>> sortByNameDesc() {
        Collections.sort(Objects.requireNonNull(itemsList.getValue()), (item, t1) -> t1.getOfferName().compareToIgnoreCase(item.getOfferName()));
        return itemsList;
    }

    //To Sort By CashBack Value & Expose Result So View Can Observe It
    public MutableLiveData<List<Item>> sortByCashBack() {
        Collections.sort(Objects.requireNonNull(itemsList.getValue()), (item, t1) -> Double.compare(item.getCashBack(), t1.getCashBack()));
        return itemsList;
    }

}