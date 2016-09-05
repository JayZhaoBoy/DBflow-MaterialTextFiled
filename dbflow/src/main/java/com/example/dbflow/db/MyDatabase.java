package com.example.dbflow.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by jay Z on 2016/9/4.
 */
@Database(name = MyDatabase.NAME,version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDatabase";
    public static final int VERSION = 1;
}
