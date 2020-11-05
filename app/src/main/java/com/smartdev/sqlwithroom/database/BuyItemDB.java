package com.smartdev.sqlwithroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.smartdev.sqlwithroom.model.BuyItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {BuyItem.class},exportSchema = false, version = 1)
public abstract class  BuyItemDB extends RoomDatabase {
    private static final String DB_NAME = "buy_items_database";
    private static final int NUMBER_OF_THREADS = 3;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BuyItemDB instance;

    public static synchronized BuyItemDB getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BuyItemDB.class, DB_NAME)
                    /*sprečavanje pucanja pri migraciji baze*/
                    .fallbackToDestructiveMigration()
                    /*samo zbog debuging-a pri korišćenju DB browsre-a*/
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build();

        }
        return instance;
    }

    public abstract BuyItemDao getBuyItemDao();
}
