package com.test.testapp.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rijosh on 9/13/2018.
 */

@Entity
    public class DataModel {


    public DataModel() {
    }

    @PrimaryKey(autoGenerate = true)
        private int id;

    @ColumnInfo(name = "data_name")
        private String dataName;


    @ColumnInfo(name = "data_timestamp")
    private String dataTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataTimestamp() {
        return dataTimestamp;
    }

    public void setDataTimestamp(String dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }
}
