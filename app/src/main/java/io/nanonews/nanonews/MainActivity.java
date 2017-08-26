package io.nanonews.nanonews;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ArticleFragment.OnFragmentInteractionListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final static int KEY_FILTER = 9721;

    //views
    @BindView(R.id.view_pager) ViewPager viewPager;

    DataCenter dataCenter;
    ArticlesFragmentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataCenter = new DataCenter(this);
        dataCenter.getArticles(null, new DataFetchingCallback<List<Article>>() {    //TODO hard code null
            @Override
            public void onDataFetched(List<Article> result) {
                if (result != null && result.size() > 0) {
                    adapter = new ArticlesFragmentsAdapter(getSupportFragmentManager(), result);
                    viewPager.setAdapter(adapter);
                }
            }

            @Override
            public void onFail(Throwable exception) {
                //TODO display sth?
                exception.printStackTrace();
            }
        });
    }

    @OnClick(R.id.button_menu)
    public void onMenuButtonClick() {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivityForResult(intent, KEY_FILTER);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //TODO something?
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataCenter.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KEY_FILTER && resultCode == RESULT_OK) {
            viewPager.setCurrentItem(0);
            dataCenter.getArticles(data.getIntegerArrayListExtra(CategoriesActivity.KEY_SELECTED_CATEGORIES), new DataFetchingCallback<List<Article>>() {
                @Override
                public void onDataFetched(List<Article> result) {
//                    adapter.setArticles(result);
                    adapter = new ArticlesFragmentsAdapter(getSupportFragmentManager(), result);
                    viewPager.setAdapter(adapter);
                }

                @Override
                public void onFail(Throwable exception) {
                    exception.printStackTrace();
                }
            });
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}