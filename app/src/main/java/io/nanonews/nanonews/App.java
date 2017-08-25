package io.nanonews.nanonews;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
        checkNetworkAvailability();
    }

    private void checkNetworkAvailability() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                isOnline = true;
            }
        }
    }
}
