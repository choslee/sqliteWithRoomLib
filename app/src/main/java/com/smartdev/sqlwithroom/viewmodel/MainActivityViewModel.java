package com.smartdev.sqlwithroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smartdev.sqlwithroom.database.BuyItemsRepository;
import com.smartdev.sqlwithroom.model.BuyItem;
import com.smartdev.sqlwithroom.view.BuyListAdapter;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private BuyListAdapter mAdapter;
    private BuyItemsRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = BuyItemsRepository.getInstance(application);

        /* This is MutableLiveData from repository (not new MutableLiveData<>()) */
        allDataItems = repository.getAllItemsFromRepo();
    }


    private LiveData<List<BuyItem>> allDataItems;
    /* LiveData Getter from ViewModel*/
    public LiveData<List<BuyItem>> getAllItemsFromViewModel() {
        return allDataItems;
    }

    /*Insert item to DB*/
    public void insertItemDB(BuyItem buyItem) {
        repository.insertItem(buyItem);
    }

    /* Remove item from DB*/
    public void removeItemFromDB(BuyItem buyItem) {
        repository.removeItem(buyItem);
    }
}
