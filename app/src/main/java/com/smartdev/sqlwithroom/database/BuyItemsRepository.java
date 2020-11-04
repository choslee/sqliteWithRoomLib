package com.smartdev.sqlwithroom.database;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;

import com.smartdev.sqlwithroom.model.BuyItem;

import java.util.List;

public class BuyItemsRepository {

    private BuyItemDao buyItemDao;
    private LiveData<List<BuyItem>> listFromDB;

    public BuyItemsRepository(Application app){
        BuyItemDB database = BuyItemDB.getInstance(app);
        buyItemDao = database.getBuyItemDao();
        listFromDB = buyItemDao.getAllItemsFromDB();
    }

    /* Static instance*/
    private static BuyItemsRepository mInstance = null;
    public static BuyItemsRepository getInstance(Application application){
        if(mInstance == null){
            mInstance = new BuyItemsRepository(application);
        }
        return mInstance;
    }

    public LiveData<List<BuyItem>> getAllItemsFromRepo() {
        return listFromDB;
    }

    /* Call DB method from repo*/
    public void insertItem (BuyItem buyItem) {
        new InsertBuyItemAsyncTask(buyItemDao).execute(buyItem);
    }

    /* Call DB method from repo*/
    public void removeItem(BuyItem buyItem) {
        new RemoveBuyItemAsyncTask(buyItemDao).execute(buyItem);
    }


    private static class InsertBuyItemAsyncTask extends AsyncTask<BuyItem, Void, Void> {
        private BuyItemDao buyItemDao;

        private InsertBuyItemAsyncTask(BuyItemDao buyItemDao) {
            this.buyItemDao = buyItemDao;
        }
        @Override
        protected Void doInBackground(BuyItem... buyItem) {
            buyItemDao.insertItemToDB(buyItem[0]);
            return null;
        }
    }
    private static class RemoveBuyItemAsyncTask extends AsyncTask<BuyItem, Void, Void> {
        private BuyItemDao buyItemDao;

        private RemoveBuyItemAsyncTask(BuyItemDao buyItemDao) {
            this.buyItemDao = buyItemDao;
        }
        @Override
        protected Void doInBackground(BuyItem... buyItem) {
            buyItemDao.removeItemFromDB(buyItem[0]);
            return null;
        }
    }

}
