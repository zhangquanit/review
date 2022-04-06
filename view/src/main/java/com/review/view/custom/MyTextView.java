package com.review.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * @author 张全
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //--------------------------------------View绘制-------------------------------
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("myTextView-------------onMeasure,widthMeasureSpec="+widthMeasureSpec+",heightMeasureSpec="+heightMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthModel==MeasureSpec.EXACTLY){ //
            System.out.println("myTextView,width------EXACTLY");
        }else if(widthModel==MeasureSpec.AT_MOST){
            System.out.println("myTextView,width------AT_MOST");
        }else{
            System.out.println("myTextView,width------UNSPECIFIED");
        }

        if(heightModel==MeasureSpec.EXACTLY){
            System.out.println("myTextView,height------EXACTLY");
        }else if(heightModel==MeasureSpec.AT_MOST){
            System.out.println("myTextView,height------AT_MOST");
        }else{
            System.out.println("myTextView,height------UNSPECIFIED");
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=getMeasuredWidth();
        int height=getMeasuredHeight();
        System.out.println("myTextView,measureWidth="+width+",measureHeight="+height);

        //文字測量1
        float fontWidth = getFontWidth();
        float fontHeight = getFontHeight();
        System.out.println("myTextView,fontWidth="+fontWidth+",fontHeight="+fontHeight);
        //文字測量2
        String text=getText().toString();
        Rect rect = new Rect();
        getPaint().getTextBounds(text,0,text.length(),rect);
        System.out.println("myTextView,fontWidth2="+rect.width()+",fontHeight2="+rect.height());
    }
    private float getFontWidth(){
        float fontWidth = getPaint().measureText(getText().toString());
        return fontWidth;
    }
    private float getFontHeight(){
        Paint.FontMetrics fm = getPaint().getFontMetrics();
        float fontHeight = (float)Math.ceil(fm.descent - fm.ascent);
        return fontHeight;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        System.out.println("myTextView-------------onLayout,[changed="+changed+",left="+left+",top="+top+",right="+right+",bottom="+bottom+"]");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("myTextView-------------onDraw");
    }

    //--------------------------------------触摸事件------------------------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//                getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("MyTextView------onTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MyTextView------onTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("MyTextView------onTouchEvent------ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("MyTextView------dispatchTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MyTextView------dispatchTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("MyTextView------dispatchTouchEvent------ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    //--------------------------------------按键事件-------------------------------
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("MyTextView------dispatchKeyEvent------code=" + event.getKeyCode());
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                System.out.println("MyTextView------dispatchKeyEvent------KEYCODE_BACK");
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("MyTextView------onKeyDown------keyCode="+keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        System.out.println("MyTextView------onKeyUp------keyCode="+keyCode);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        System.out.println("MyTextView------onKeyLongPress------keyCode="+keyCode);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        System.out.println("MyTextView------onKeyMultiple------keyCode="+keyCode);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        System.out.println("MyTextView------onKeyShortcut------keyCode="+keyCode);
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        System.out.println("MyTextView------dispatchKeyShortcutEvent------keyCode="+event.getKeyCode());
        return super.dispatchKeyShortcutEvent(event);
    }

}
