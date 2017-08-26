package io.nanonews.nanonews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by louistsai on 25.08.17.
 */

public class ArticlesFragmentsAdapter extends FragmentStatePagerAdapter {
    private List<Article> articles;

    public ArticlesFragmentsAdapter(FragmentManager fm, List<Article> articles) {
        super(fm);
        this.articles = articles;
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleFragment.newInstance(articles.get(position));   //TODO
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        this.notifyDataSetChanged();
    }
}
