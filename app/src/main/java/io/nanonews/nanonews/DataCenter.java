package io.nanonews.nanonews;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by louistsai on 25.08.17.
 */
public class DataCenter {
    private static final String TAG = "DataCenter";
    NanonewsApiWrapper apiWrapper;
    Realm realm;

    public DataCenter() {
        if (App.isOnline) {
            apiWrapper = new NanonewsApiWrapper();
        } else {
            realm = Realm.getDefaultInstance();
        }
    }

    /**
     *
     * @param categories
     * @param callback
     */
    public void getArticles(@Nullable List<Integer> categories, final DataFetchingCallback<List<Article>> callback) {
        if (App.isOnline) {
            Log.d(TAG, "5");
            apiWrapper.getArticles(categories).enqueue(new Callback<List<Article>>() {
                @Override
                public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                    Log.d(TAG, "6: " + response.toString());
                    callback.onDataFetched(response.body());
                }

                @Override
                public void onFailure(Call<List<Article>> call, Throwable t) {
                    Log.d(TAG, "7");
                    t.printStackTrace();
                    callback.onFail(t);
                }
            });
        } else {
            Log.d(TAG, "8");
            RealmResults<Article> results = realm.where(Article.class).findAll();
            callback.onDataFetched(results);
        }
    }

    /**
     *
     * @param callback
     */
    public void getCategories(final DataFetchingCallback<List<Category>> callback) {
        if (App.isOnline) {
            apiWrapper.getCategories().enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    callback.onDataFetched(response.body());
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    callback.onFail(t);
                }
            });
        } else {
            RealmResults<Category> results = realm.where(Category.class).findAll();
            callback.onDataFetched(results);
        }
    }
}
