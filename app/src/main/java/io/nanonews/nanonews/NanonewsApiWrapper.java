package io.nanonews.nanonews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by louistsai on 25.08.17.
 */

public class NanonewsApiWrapper {
    public static final String BASE_URL = "http://10.240.86.191:3000/api/v1/";
    Retrofit retrofit;
    NanonewsInterface nanonews;

    public NanonewsApiWrapper(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(context))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        nanonews = retrofit.create(NanonewsInterface.class);
    }

    /**
     *
     * @param categories is the filter you want to apply, nullable
     * @return the call object so that you can cancel it if you want
     */
    public Call<List<Article>> getArticles(@Nullable List<Integer> categories) {
        String output = null;
        if (categories != null && categories.size() > 0) {
            output = TextUtils.join(",", categories);
        }
        return nanonews.getArticles(output);
    }

    /**
     * This is simple
     * @return
     */
    public Call<List<Category>> getCategories() {
        return nanonews.getCategories();
    }
}
