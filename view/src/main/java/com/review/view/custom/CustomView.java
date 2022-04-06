package com.review.view.custom;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.customview.R;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author 张全
 */
public class CustomView extends View {
    private int textColor;
    private int textSize;
    private String text;
    private Paint paint;


    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);

        Resources.Theme theme = context.getTheme();
        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, defStyleRes);
        if (a != null) {
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.CustomView_myColor:
                        textColor = a.getColor(attr, Color.RED);
                        paint.setColor(textColor);
                        break;
                    case R.styleable.CustomView_myText:
                        text = (String) a.getString(attr);
                        break;
                    case R.styleable.CustomView_myTextSize:
                        textSize = a.getDimensionPixelSize(attr, 0);
                        paint.setTextSize(textSize);
                        break;
                }
            }
        }

    }

    public void setTextColor(int color) {
        this.textColor = color;
        paint.setColor(textColor);
        invalidate();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        paint.setTextSize(textSize);
        invalidate();
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

/*
    EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
    AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
    UNSPECIFIED：表示子布局想要多大就多大，很少使用
 */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        System.out.println("[widthModel=" + widthModel + ",widthSize=" + widthSize + ",heightModel=" + heightModel + ",heightSize=" + heightSize + "]");

        int minWidth = getMinimumWidth();
        int minHeight = getMinimumHeight();
        System.out.println("minWidth=" + minWidth + ",minHeight=" + minHeight);
        int width = 0;
        int height = 0;
        if (widthModel == MeasureSpec.EXACTLY) { //match_pae
            System.out.println("width------EXACTLY");
            width = widthSize;
        } else if (widthModel == MeasureSpec.AT_MOST) {
            System.out.println("width------AT_MOST");
            float fontWidth = getFontWidth();
            width = (int) (getPaddingLeft() + fontWidth + getPaddingRight());
        } else {
            System.out.println("width------UNSPECIFIED");
        }
        width = Math.max(width, minWidth);

        if (heightModel == MeasureSpec.EXACTLY) {
            System.out.println("height------EXACTLY");
            height = heightSize;
        } else if (heightModel == MeasureSpec.AT_MOST) {
            System.out.println("height------AT_MOST");
            float fontHeight = getFontHeight();
            height = (int) (getPaddingTop() + fontHeight + getPaddingBottom());
        } else {
            System.out.println("height------UNSPECIFIED");
        }
        height = Math.max(height, minHeight);

        System.out.println("width=" + width + ",height=" + height);


        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        System.out.println("onLayout,changed=" + changed + ",left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("-----onDraw");

        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);

        float fontWidth = getFontWidth();
        float fontHeight = getFontHeight();
        System.out.println("fontWidth=" + fontWidth + ",fontHeight=" + fontHeight);
        System.out.println("width=" + rect.width() + ",height=" + rect.height());


        //绘制背景
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());

        //绘制文字區域背景
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, fontWidth, fontHeight, paint);

        //绘制文字
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText(text, 0, rect.height(), paint);

        canvas.restore();

    }

    private float getFontWidth() {
        float fontWidth = paint.measureText(text);
        return fontWidth;
    }

    private float getFontHeight() {
        Paint.FontMetrics fm = paint.getFontMetrics();
        float fontHeight = (float) Math.ceil(fm.descent - fm.ascent);
        return fontHeight;
    }
}
