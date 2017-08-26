package io.nanonews.nanonews;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by louistsai on 25.08.17.
 */
public interface NanonewsInterface {
    @GET("news_articles")
    Call<List<Article>> getArticles(@Query("category_ids") String categories);

    @GET("categories")
    Call<List<Category>> getCategories();
}
