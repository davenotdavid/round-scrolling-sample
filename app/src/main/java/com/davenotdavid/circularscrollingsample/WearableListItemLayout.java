package com.davenotdavid.circularscrollingsample;

import android.content.Context;
import android.graphics.Color;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WearableListItemLayout extends LinearLayout
        implements WearableListView.OnCenterProximityListener {

    private TextView mName;
    private final float mFadedTextAlpha;

    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mFadedTextAlpha = getResources().getInteger(R.integer.action_text_faded_alpha) / 100f;;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mName = (TextView) findViewById(R.id.name);
    }

    @Override
    public void onCenterPosition(boolean animate) {
        mName.setAlpha(1f);
        mName.setTextColor(Color.BLACK);
        mName.setBackgroundColor(Color.WHITE);
        mName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 38);
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
        mName.setAlpha(mFadedTextAlpha);
        mName.setTextColor(Color.BLACK);
        mName.setBackgroundColor(Color.TRANSPARENT);
        mName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
    }
}
