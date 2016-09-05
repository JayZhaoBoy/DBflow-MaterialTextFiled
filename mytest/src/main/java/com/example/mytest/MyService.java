package com.example.mytest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by jay Z on 2016/9/1.
 */
public class MyService extends Service {
    private  MyIBinder binder = new MyIBinder() ;

    public class MyIBinder extends Binder {
        public String getString(){
            return new String("hello world");
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
