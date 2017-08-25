package io.nanonews.nanonews;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by louistsai on 25.08.17.
 */
public class App extends Application {
    public static boolean isOnline = false;   //default to offline

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        //TODO see the internet is available: if not, use realm; else, call api (set flag). This will not change in runtime
    }
}
