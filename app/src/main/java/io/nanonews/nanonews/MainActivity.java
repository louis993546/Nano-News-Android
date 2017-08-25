package io.nanonews.nanonews;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.view_pager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        NanonewsApiWrapper wrapper = new NanonewsApiWrapper();
        Call<List<Article>> call = wrapper.getArticles(null);
        Log.d(TAG, call.request().toString());
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                Log.d(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, t.toString());
            }
        });
    }
}
