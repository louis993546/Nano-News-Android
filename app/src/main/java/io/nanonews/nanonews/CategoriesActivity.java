package io.nanonews.nanonews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by louistsai on 26.08.17.
 */
public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CategoriesActivity";
    public static final String KEY_SELECTED_CATEGORIES = "SELECTED_CATEGORIES";
    //views
    @BindView(R.id.flow_layout_categories) FlowLayout flowLayout;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    DataCenter dataCenter;
    ArrayList<Integer> currentlySelectedCategories = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        dataCenter = new DataCenter(this);
        dataCenter.getCategories(new DataFetchingCallback<List<Category>>() {
            @Override
            public void onDataFetched(List<Category> result) {
                if (result != null && result.size() > 0) {
                    for (Category c: result) {
                        CategoryChecker cc = new CategoryChecker(CategoriesActivity.this, c);
                        cc.setOnClickListener(CategoriesActivity.this);
                        flowLayout.addView(cc);
                    }
                    progressBar.setVisibility(View.GONE);
                    flowLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(Throwable exception) {
                exception.printStackTrace();
            }
        });
    }

    @OnClick(R.id.linear_layout_button)
    public void onGoToNewsClick() {
        finishAndReturnSelectedFilters();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAndReturnSelectedFilters();
    }

    private void finishAndReturnSelectedFilters() {
        Intent intent = new Intent();
        intent.putIntegerArrayListExtra(KEY_SELECTED_CATEGORIES, currentlySelectedCategories);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataCenter.close();
    }

    @Override
    public void onClick(View view) {
        if (view instanceof CategoryChecker) {
            CategoryChecker cc = (CategoryChecker) view;
            Log.d(TAG, cc.getCategory().toString());
            Log.d(TAG, String.valueOf(cc.isOn()));
            if (currentlySelectedCategories.contains(cc.getCategory().getId())) {
                currentlySelectedCategories.remove(currentlySelectedCategories.indexOf(cc.getCategory().getId()));
            } else {
                currentlySelectedCategories.add(cc.getCategory().getId());
            }
        }
    }
}
