package com.test.testapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.test.testapp.model.DataModel;

/**
 * Created by Rijosh on 9/13/2018.
 */

@Database(entities = {DataModel.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();



}
