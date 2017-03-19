package com.example.watanabear.tasks;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by watanabear on 2017/03/19.
 */

public class TasksApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
