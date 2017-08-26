package io.nanonews.nanonews;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

/**
 * Created by louistsai on 25.08.17.
 */
public class CategoryChecker extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {
    private static final String TAG = "CategoryChecker";
    private Category category;
    private boolean isOn = false;
    private OnClickListener wrappedOnClickListener;

    public CategoryChecker(Context context, Category category) {
        super(context);
        super.setOnClickListener(this);
        this.category = category;
        setText(this.category.getTitle());
        this.setTextColor(Color.parseColor("#1970D9"));
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_plus_blue, 0);
        this.setCompoundDrawablePadding(16);
        this.setPadding(64, 0, 64, 0);
        this.setBackgroundResource(R.drawable.category_checker_background_white);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "yay");
        isOn = !isOn;   //flip the state
        if (isOn) {
            this.setBackgroundResource(R.drawable.category_checker_background_red);
            this.setTextColor(Color.WHITE);
            this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
        } else {
            this.setBackgroundResource(R.drawable.category_checker_background_white);
            this.setTextColor(Color.parseColor("#1970D9"));
            this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_plus_blue, 0);
        }
        if (wrappedOnClickListener != null)
            wrappedOnClickListener.onClick(view);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        wrappedOnClickListener = l;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isOn() {
        return isOn;
    }
}
