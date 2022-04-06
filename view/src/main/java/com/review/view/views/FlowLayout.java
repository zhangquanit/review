package com.review.view.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张全
 */

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 测量子控件的宽高，根据子控件的宽高来设置自己的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取父容器给FlowLayout设置的测量模式和大小
        int iWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int iHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);


        int iMeasureW = 0;
        int iMeasureH = 0;

        if (widthModel == MeasureSpec.EXACTLY && heightModel == MeasureSpec.EXACTLY) {
            iMeasureW = iWidthSize;
            iMeasureH = iHeightSize;
        } else {

            int childCount = getChildCount();
            int childWidth = 0; //子View的宽
            int childHeight = 0; //子View的高
            int lineWidth = 0; //行宽
            int lineHeight = 0; //行高
            List<View> lineViews = new ArrayList<>();//当前行中的views
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

                if (lineWidth + childWidth > iWidthSize) {//超过父容器的宽度的时候，就需要换行
                    /*************1、记录当前行信息***********/
                    iMeasureW = Math.max(iMeasureW, lineWidth); //最大宽度
                    iMeasureH += lineHeight; //行高

                    mViewLinesList.add(lineViews); //添加上一行中的所有View
                    mLineHeights.add(lineHeight); //添加上一行的行高

                    /*************2、记录新建行信息***********/
                    //1、新建一行,并赋值当前child的宽、高
                    lineWidth = childWidth;
                    lineHeight = childHeight;
                    //2、新建一行,添加当前child
                    lineViews = new ArrayList<>();
                    lineViews.add(child);

                } else { //行内宽高累加
                    lineWidth += childWidth;
                    lineHeight = Math.max(lineHeight, childHeight);//child 中高度最大的
                    lineViews.add(child); //当前行中的Views
                }

                //最后一个item所在的行， 需要添加到行数里面
                if (i == childCount - 1) {
                    iMeasureW = Math.max(iMeasureW, lineWidth); //最大宽度
                    iMeasureH += lineHeight; //行高

                    mViewLinesList.add(lineViews); //添加上一行中的所有View
                    mLineHeights.add(lineHeight); //添加上一行的行高
                }
            }
        }
        System.out.println("measureH=" + iMeasureH);
        System.out.println("mViewLinesList.size="+mViewLinesList.size());

        setMeasuredDimension(iMeasureW, iMeasureH);
    }

    private List<List<View>> mViewLinesList = new ArrayList<>();//用来保存每行Views的list
    private List<Integer> mLineHeights = new ArrayList<>();//用来保存行高的list

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("onLayout,,,,changed="+changed);
        int lineSize = mViewLinesList.size(); //有多少行

        //记录当前行中view的起始坐标
        int curLineLeft = 0;
        int curLineTop = 0;
        for (int i = 0; i < lineSize; i++) {
            List<View> viewList = mViewLinesList.get(i); //每行中的child views
            int lineViewSize = viewList.size();//每行中有多少个view
            for (int j = 0; j < lineViewSize; j++) {
                View child = viewList.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                //每个child的坐标
                int childLeft = curLineLeft + layoutParams.leftMargin;
                int childTop = curLineTop + layoutParams.topMargin;
                int childRight = childLeft + child.getMeasuredWidth();
                int childBottom = childTop + child.getMeasuredHeight();

                child.layout(childLeft, childTop, childRight, childBottom);

                //下一个view的起始x坐标
                curLineLeft += child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

            }
            //下一行的起始y坐标
            curLineTop += mLineHeights.get(i);
            curLineLeft = 0;
        }
        mViewLinesList.clear();
        mLineHeights.clear();

    }

    /**
     * 给子View设置的LayoutParam，参考LayoutInflater
     *
     * @param attrs
     * @return
     */
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
