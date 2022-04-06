package com.review.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author 张全
 */
public class RootLinearLayout extends LinearLayout{
    public RootLinearLayout(Context context) {
        super(context);
    }

    public RootLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RootLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RootLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //--------------------------------------View绘制-------------------------------
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("RootLinearLayout-------------onMeasure,widthMeasureSpec="+widthMeasureSpec+",heightMeasureSpec="+heightMeasureSpec);

        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthModel==MeasureSpec.EXACTLY){ //match_pae
            System.out.println("RootLinearLayout,width------EXACTLY");
        }else if(widthModel==MeasureSpec.AT_MOST){
            System.out.println("RootLinearLayout,width------AT_MOST");
        }else{
            System.out.println("RootLinearLayout,width------UNSPECIFIED");
        }

        if(heightModel==MeasureSpec.EXACTLY){
            System.out.println("RootLinearLayout,height------EXACTLY");
        }else if(heightModel==MeasureSpec.AT_MOST){
            System.out.println("RootLinearLayout,height------AT_MOST");
        }else{
            System.out.println("RootLinearLayout,height------UNSPECIFIED");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int width=getMeasuredWidth();
        int height=getMeasuredHeight();
        System.out.println("RootLinearLayout,measureWidth="+width+",measureHeight="+height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("RootLinearLayout-------------onLayout,[changed="+changed+",left="+left+",top="+top+",right="+right+",bottom="+bottom+"]");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        System.out.println("RootLinearLayout-------------dispatchDraw");
        super.dispatchDraw(canvas);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("RootLinearLayout-------------onDraw");
        super.onDraw(canvas);
    }

    //--------------------------------------触摸事件-------------------------------

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RootLinearLayout------dispatchTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("RootLinearLayout------dispatchTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RootLinearLayout------dispatchTouchEvent-------ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RootLinearLayout------onInterceptTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("RootLinearLayout------onInterceptTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RootLinearLayout------onInterceptTouchEvent------ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RootLinearLayout------onTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("RootLinearLayout------onTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RootLinearLayout------onTouchEvent------ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        System.out.println("RootLinearLayout-------------requestDisallowInterceptTouchEvent,disallowIntercept="+disallowIntercept);
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    //--------------------------------------按键事件-------------------------------
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("RootLinearLayout------dispatchKeyEvent------code=" + event.getKeyCode());
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                System.out.println("RootLinearLayout------dispatchKeyEvent------KEYCODE_BACK");
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("RootLinearLayout------onKeyDown------keyCode="+keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        System.out.println("RootLinearLayout------onKeyUp------keyCode="+keyCode);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        System.out.println("RootLinearLayout------onKeyLongPress------keyCode="+keyCode);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        System.out.println("RootLinearLayout------onKeyMultiple------keyCode="+keyCode);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        System.out.println("RootLinearLayout------onKeyShortcut------keyCode="+keyCode);
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        System.out.println("RootLinearLayout------dispatchKeyShortcutEvent------keyCode="+event.getKeyCode());
        return super.dispatchKeyShortcutEvent(event);
    }
}
