package io.nanonews.nanonews;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity implements ArticleFragment.OnFragmentInteractionListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.toolbar)  Toolbar toolbar;
    DataCenter dataCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar(toolbar);
        dataCenter = new DataCenter(this);
        dataCenter.getArticles(null, new DataFetchingCallback<List<Article>>() {    //TODO hard code null
            @Override
            public void onDataFetched(List<Article> result) {
                if (result != null && result.size() > 0) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(this ,"home click",
                        Toast.LENGTH_LONG).show();

                break;
            case R.id.action_share:
                share(null);
                Toast.makeText(this ,"share click",
                        Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }

        return true;
    }

    /**
     * When you want to open the share news, call this
     */
    public void share(@NonNull String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, url);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }

    /**
     * Function to display the toolbar
     */
    public void initToolBar(@NonNull Toolbar toolbar) {

        if (toolbar != null) {
            this.toolbar = toolbar;
            toolbar.setTitleTextColor(getResources().getColor(R.color.colorText));
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar.setNavigationIcon(R.mipmap.ic_categories);
            //getSupportActionBar().setLogo(R.mipmap.ic_categories);
            toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Back clicked!",     Toast.LENGTH_SHORT).show();
                }
            });

        }
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
}