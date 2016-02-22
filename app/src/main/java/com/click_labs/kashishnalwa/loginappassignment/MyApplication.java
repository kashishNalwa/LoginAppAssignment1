package com.click_labs.kashishnalwa.loginappassignment;

import android.app.Application;

import io.paperdb.Paper;

/**
 * Created by kashish nalwa on 17-02-2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(getApplicationContext());
    }
}
