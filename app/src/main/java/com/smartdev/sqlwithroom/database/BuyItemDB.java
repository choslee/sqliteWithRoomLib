package com.smartdev.sqlwithroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.smartdev.sqlwithroom.model.BuyItem;


@Database(entities = {BuyItem.class},exportSchema = false, version = 1)
public abstract class  BuyItemDB extends RoomDatabase {
    private static final String DB_NAME = "buy_items_database";

    public static BuyItemDB instance;

    public static synchronized BuyItemDB getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BuyItemDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract BuyItemDao getBuyItemDao();
}
