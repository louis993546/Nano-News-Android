package io.nanonews.nanonews;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.ArticleRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Probably what the api will return
 * Created by louistsai on 25.08.17.
 */
@Parcel(implementations = {ArticleRealmProxy.class}, value = Parcel.Serialization.BEAN, analyze = {Article.class})
public class Article extends RealmObject {
    @PrimaryKey @SerializedName("id")private int id;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("full_url") private String full_url;
    @SerializedName("categories") private RealmList<Category> categoryList;
    @SerializedName("media_url") private String media_url;
    @SerializedName("media_type") private String media_type;

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getFullUrl() {
        return full_url;
    }

    @Nullable
    public RealmList<Category> getCategoryList() {
        return categoryList;
    }

    @Nullable
    public String getMediaUrl() {
        return media_url;
    }

    @Nullable
    public String getMediaType() {
        return media_type;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", full_url='" + full_url + '\'' +
                ", categoryList=" + categoryList +
                ", media_url='" + media_url + '\'' +
                ", media_type='" + media_type + '\'' +
                '}';
    }
}
