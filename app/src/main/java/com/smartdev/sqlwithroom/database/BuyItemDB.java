package com.smartdev.sqlwithroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.smartdev.sqlwithroom.model.BuyItem;


@Database(entities = {BuyItem.class}, version = 1)
public abstract class  BuyItemDB extends RoomDatabase {

    public static BuyItemDB instance;

    public static synchronized BuyItemDB getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BuyItemDB.class, "buy_items_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract BuyItemDao getBuyItemDao();
}
