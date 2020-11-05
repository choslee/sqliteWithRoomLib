package com.smartdev.sqlwithroom.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

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

    /* Static instance - Singleton pattern */
    private static BuyItemsRepository mInstance = null;
    public static BuyItemsRepository getInstance(Application application){
        if(mInstance == null){
            mInstance = new BuyItemsRepository(application);
        }
        return mInstance;
    }

    /* Kod ove metode ne moramo da brinemo o izvršenju sa background thread-a
     jer o njoj brine Room */
    public LiveData<List<BuyItem>> getAllItemsFromRepo() {
        return listFromDB;
    }

    /* Call DB method from repo on background thread */
    public void insertItem (BuyItem buyItem) {
        BuyItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                buyItemDao.insertItemToDB(buyItem);
            }
        });
    }

    /* Call DB method from repo on background thread */
    public void removeItem(BuyItem buyItem) {
        BuyItemDB.databaseWriteExecutor.execute(()-> {
            buyItemDao.removeItemFromDB(buyItem);
        });
    }


}
