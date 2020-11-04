package com.smartdev.sqlwithroom.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "buy_item_table")
public class BuyItem {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "amaunt")
    private String mAmount;

    @ColumnInfo(name = "time")
    private String mTimestamp;

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
    public String getTimestamp() {
        return mTimestamp;
    }


    /* TODO: ovo bi trebalo izbaciti u radu sa ROOM-om*/
    public void setAmount(String amount) {
        this.mAmount = amount;
    }
    public void setName(String name) {
        this.mName = name;
    }
    public void setTimestamp(String timestamp) {
        this.mTimestamp = timestamp;
    }
}
