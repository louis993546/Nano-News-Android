package io.nanonews.nanonews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apmem.tools.layouts.FlowLayout;
import org.parceler.Parcels;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleFragment extends Fragment {
    private static final String TAG = "ArticleFragment";
    //keys
    private static final String KEY_ARTICLE = "ARTICLE";
    //views
    @BindView(R.id.image_view_cover_photo) ImageView coverPhoto;
    @BindView(R.id.text_view_title) TextView title;
    @BindView(R.id.text_view_content) TextView content;
    @BindView(R.id.flow_layout_categories) FlowLayout flowLayout;
//    @BindView(R.id.button_share) Button buttonShare;
//    @BindView(R.id.button_read_more) Button buttonReadMore;

    //data
    @Nullable Article thisArticle;

    private OnFragmentInteractionListener mListener;

    public ArticleFragment() {
        // Required empty public constructor
    }

    public static ArticleFragment newInstance(Article article) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        Parcelable parcelable = Parcels.wrap(article);
        args.putParcelable(KEY_ARTICLE, parcelable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            thisArticle = Parcels.unwrap(getArguments().getParcelable(KEY_ARTICLE));
            Log.d(TAG, "yay: " + thisArticle.toString());
        }
    }



    @OnClick(R.id.button_read_more)
    public void onReadMoreClick() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent intent = builder.build();
        intent.launchUrl(getContext(), Uri.parse(thisArticle.getFullUrl()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText(thisArticle.getTitle());
        content.setText(thisArticle.getDescription());
        if (Objects.equals(thisArticle.getMediaType(), Const.MEDIA_TYPE_IMAGE)) {
            Glide.with(getContext()).load(thisArticle.getMediaUrl()).into(coverPhoto);
        } else if (Objects.equals(thisArticle.getMediaType(), Const.MEDIA_TYPE_VIDEO)) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(thisArticle.getMediaUrl())));
            //TODO youtube api instead of youtube intent
        }
        for (Category c: thisArticle.getCategoryList()) {
            Button button = new Button(getContext());
            button.setText(c.getTitle());
            flowLayout.addView(button);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
