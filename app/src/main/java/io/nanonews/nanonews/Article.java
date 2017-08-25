package io.nanonews.nanonews;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Probably what the api will return
 * Created by louistsai on 25.08.17.
 */
public class Article extends RealmObject implements Serializable {
    private String title;
    private String description;
    private String full_url;
    private RealmList<Category> categoryList;
    private String media_url;
    private String media_type;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFull_url() {
        return full_url;
    }

    public RealmList<Category> getCategoryList() {
        return categoryList;
    }

    public String getMedia_url() {
        return media_url;
    }

    public String getMedia_type() {
        return media_type;
    }
}
