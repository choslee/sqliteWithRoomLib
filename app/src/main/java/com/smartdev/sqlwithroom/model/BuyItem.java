package com.smartdev.sqlwithroom.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "buy_item_table")
public class BuyItem {

    @PrimaryKey(autoGenerate = true)
    private long mId;
    public String mName;
    public String mAmount;
    public String mTimestamp;

    public long getId(){
        return mId;
    }
    public void setId(int id){
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        this.mAmount = amount;
    }

    public String getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }
}
