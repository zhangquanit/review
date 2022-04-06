package com.review.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @author 张全
 */
public class MyRelativeLayout extends RelativeLayout{
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //--------------------------------------View绘制-------------------------------

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("MyRelativeLayout-------------onMeasure,widthMeasureSpec="+widthMeasureSpec+",heightMeasureSpec="+heightMeasureSpec);

        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthModel==MeasureSpec.EXACTLY){ //match_pae
            System.out.println("MyRelativeLayout,width------EXACTLY");
        }else if(widthModel==MeasureSpec.AT_MOST){
            System.out.println("MyRelativeLayout,width------AT_MOST");
        }else{
            System.out.println("MyRelativeLayout,width------UNSPECIFIED");
        }

        if(heightModel==MeasureSpec.EXACTLY){
            System.out.println("MyRelativeLayout,height------EXACTLY");
        }else if(heightModel==MeasureSpec.AT_MOST){
            System.out.println("MyRelativeLayout,height------AT_MOST");
        }else{
            System.out.println("MyRelativeLayout,height------UNSPECIFIED");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int width=getMeasuredWidth();
        int height=getMeasuredHeight();
        System.out.println("MyRelativeLayout,measureWidth="+width+",measureHeight="+height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("MyRelativeLayout-------------onLayout,[changed="+changed+",left="+left+",top="+top+",right="+right+",bottom="+bottom+"]");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        System.out.println("MyRelativeLayout-------------dispatchDraw");
        super.dispatchDraw(canvas);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("MyRelativeLayout-------------onDraw");
        super.onDraw(canvas);
    }

    //--------------------------------------触摸事件-------------------------------
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("MyRelativeLayout------dispatchTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MyRelativeLayout------dispatchTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("MyRelativeLayout------dispatchTouchEvent------ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("MyRelativeLayout------onInterceptTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MyRelativeLayout------onInterceptTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("MyRelativeLayout------onInterceptTouchEvent------ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("MyRelativeLayout------onTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MyRelativeLayout------onTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("MyRelativeLayout------onTouchEvent-------ACTION_UP");
                break;
        }
//        return true;
        return super.onTouchEvent(event);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        System.out.println("MyRelativeLayout-------------requestDisallowInterceptTouchEvent,disallowIntercept="+disallowIntercept);
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    //--------------------------------------按键事件-------------------------------
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("MyRelativeLayout------dispatchKeyEvent------code=" + event.getKeyCode());
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                System.out.println("MyRelativeLayout------dispatchKeyEvent------KEYCODE_BACK");
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("MyRelativeLayout------onKeyDown------keyCode="+keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        System.out.println("MyRelativeLayout------onKeyUp------keyCode="+keyCode);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        System.out.println("MyRelativeLayout------onKeyLongPress------keyCode="+keyCode);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        System.out.println("MyRelativeLayout------onKeyMultiple------keyCode="+keyCode);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        System.out.println("MyRelativeLayout------onKeyShortcut------keyCode="+keyCode);
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        System.out.println("MyRelativeLayout------dispatchKeyShortcutEvent------keyCode="+event.getKeyCode());
        return super.dispatchKeyShortcutEvent(event);
    }
}
