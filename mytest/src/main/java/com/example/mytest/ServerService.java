package com.example.mytest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.aidl.IPerson;

/**
 * Created by jay Z on 2016/9/2.
 */
public class ServerService extends Service {

    private String[] names = {"张三","李四","王五"};
    private  String query(int num){
        return names[num] ;
    }
    private IBinder mBinder = new PersonBinder() ;
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    private class PersonBinder extends IPerson.Stub{

        @Override
        public String queryPerson(int num) throws RemoteException {
            return query(num);
        }
    }
}
