package io.nanonews.nanonews;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by louistsai on 25.08.17.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
