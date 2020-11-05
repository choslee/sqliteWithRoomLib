package com.smartdev.sqlwithroom.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "buy_item_table")
public class BuyItem {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "amount")
    private String mAmount;

    @ColumnInfo(name = "time")
    private String mTimestamp;

    public BuyItem(String mName, String mAmount, String mTimestamp) {
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
    public String getTimestamp() { return mTimestamp;
    }
}
