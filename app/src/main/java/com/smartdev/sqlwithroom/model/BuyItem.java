package com.smartdev.sqlwithroom.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.smartdev.sqlwithroom.database.DateConverter;

import java.util.Date;


@Entity(tableName = "buy_item_table")
public class BuyItem {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "amount")
    private String mAmount;

    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "time")
    private Date mTimestamp;

    public BuyItem(String mName, String mAmount, Date mTimestamp) {
        this.mName = mName;
        this.mAmount = mAmount;
        this.mTimestamp = mTimestamp;
    }

    public void setId(int id){
        mId = id;
    }
    public int getId(){
        return mId;
    }


    public String getName() {
        return mName;
    }
    public String getAmount() {
        return mAmount;
    }
    public Date getTimestamp() { return mTimestamp; }
}
