package io.nanonews.nanonews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleFragment extends Fragment {
    //views
    @BindView(R.id.image_view_cover_photo) ImageView coverPhoto;
    @BindView(R.id.text_view_title) TextView title;
    @BindView(R.id.text_view_content) TextView content;
//    @BindView(R.id.button_share) Button buttonShare;
//    @BindView(R.id.button_read_more) Button buttonReadMore;

    private OnFragmentInteractionListener mListener;

    public ArticleFragment() {
        // Required empty public constructor
    }

    /**
     * TODO probably input an article
     * @return
     */
    public static ArticleFragment newInstance() {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        //TODO
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //TODO
        }
    }

    @OnClick(R.id.button_share)
    public void onShareClick() {
        //TODO
    }

    @OnClick(R.id.button_read_more)
    public void onReadMoreClick() {
        //TODO
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);
        return view;
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
