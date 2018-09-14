package com.test.testapp.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.test.testapp.model.DataModel;

import java.util.List;

/**
 * Created by Rijosh on 9/13/2018.
 */

@Dao
public interface DaoAccess {



    @Insert
    void insertData(List<DataModel> advocateList);


    @Query("SELECT * FROM DataModel")
    List<DataModel> fetchData();


}
