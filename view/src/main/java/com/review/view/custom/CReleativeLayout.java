package com.review.view.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author 张全
 */

public class CReleativeLayout extends RelativeLayout {
    public CReleativeLayout(Context context) {
        super(context);
    }

    public CReleativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CReleativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CReleativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("CReleativeLayout......onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("CReleativeLayout......onLayout");
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("CReleativeLayout......onDraw");
        super.onDraw(canvas);
    }
}
