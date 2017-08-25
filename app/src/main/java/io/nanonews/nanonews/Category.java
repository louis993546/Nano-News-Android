package io.nanonews.nanonews;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by louistsai on 25.08.17.
 */

public class Category extends RealmObject implements Serializable {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
