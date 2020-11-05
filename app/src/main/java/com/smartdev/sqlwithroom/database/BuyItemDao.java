package com.smartdev.sqlwithroom.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.smartdev.sqlwithroom.model.BuyItem;

import java.util.List;

@Dao
public interface BuyItemDao {
    @Insert
    void insertItemToDB(BuyItem buyItem);

    @Delete
    void removeItemFromDB(BuyItem buyItem);

    @Query("SELECT * FROM buy_item_table ORDER BY id DESC")
    LiveData<List<BuyItem>> getAllItemsFromDB();
}
