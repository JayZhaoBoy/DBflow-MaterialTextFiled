package com.example.dbflow.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by jay Z on 2016/9/4.
 */
@ModelContainer
@Table(database =MyDatabase.class)
public class People extends BaseModel {
    @PrimaryKey(autoincrement = true)
    public long id ;
    @Column
    public String name ;
    @Column
    public int age ;

}