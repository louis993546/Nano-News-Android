package io.nanonews.nanonews;

import android.net.Uri;
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

public class MainActivity extends AppCompatActivity implements ArticleFragment.OnFragmentInteractionListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.view_pager) ViewPager viewPager;
    DataCenter dataCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataCenter = new DataCenter();
        Log.d(TAG, "1");
        dataCenter.getArticles(null, new DataFetchingCallback<List<Article>>() {    //TODO hard code null
            @Override
            public void onDataFetched(List<Article> result) {
                Log.d(TAG, "2");
                if (result != null && result.size() > 0) {
                    Log.d(TAG, "3");
                    viewPager.setAdapter(new ArticlesFragmentsAdapter(getSupportFragmentManager(), result));
                }
            }

            @Override
            public void onFail(Throwable exception) {
                //TODO display sth?
                exception.printStackTrace();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //TODO something?
    }
}