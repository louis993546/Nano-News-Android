package io.nanonews.nanonews;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.CategoryRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by louistsai on 25.08.17.
 */
@Parcel(implementations = {CategoryRealmProxy.class}, value = Parcel.Serialization.BEAN, analyze = {Category.class})
public class Category extends RealmObject {
    @PrimaryKey @SerializedName("id") private int id;
    @SerializedName("title") private String title;

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
