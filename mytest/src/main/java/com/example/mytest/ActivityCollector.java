package com.example.mytest;

import android.app.Activity;

import java.util.LinkedList;

/**
 * Created by jay Z on 2016/9/1.
 */
public class ActivityCollector {
    public static LinkedList<Activity> mList = new LinkedList<>() ;
    public static void addActivity(Activity activity){
        mList.add(activity) ;
    }
    public static void finishAll(){
        for(Activity activity :mList){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
