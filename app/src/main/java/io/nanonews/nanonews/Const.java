package io.nanonews.nanonews;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by louistsai on 25.08.17.
 */

public class Const {
    public final static String MEDIA_TYPE_IMAGE = "image";
    public final static String MEDIA_TYPE_VIDEO = "video";
    public final static RequestOptions GLIDE_STANDARD_CONFIG = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate();
    public final static RequestOptions GLIDE_COVER_PHOTO_CONFIG = new RequestOptions().apply(GLIDE_STANDARD_CONFIG).placeholder(R.drawable.background_2);
}
