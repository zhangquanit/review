package com.review.view.custom;

import android.app.Activity;
import android.customview.R;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author 张全
 */
public class TestActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    private TextView resultText;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);

        textView = (TextView) findViewById(R.id.myTextView);
        resultText = (TextView) findViewById(R.id.changetxt);
        relativeLayout = (RelativeLayout) findViewById(R.id.myRelativeLayout);
        findViewById(R.id.change).setOnClickListener(this);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("MyRelativeLayout================onClick");
                Toast.makeText(TestActivity.this,"onClick",Toast.LENGTH_SHORT).show();
            }
        });
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("MyRelativeLayout=========onTouch=======ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("MyRelativeLayout=========onTouch=======ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("MyRelativeLayout=========onTouch=======ACTION_UP");
                        break;
                }
                return false;
            }
        });
    }

    int index;

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "index=" + index, Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.change:
                if (index == 0) {
                    textView.setText("改变文字"); //从MyRelativeLayout到MyTextView，从onMeasure--onLayout--dispatchDraw--onDraw
                } else if (index == 1) {
                    textView.setTextColor(Color.RED); //MyTextView的onDraw
                } else if (index == 2) {
                    textView.setBackgroundColor(Color.GREEN);//从MyRelativeLayout到MyTextView，从onMeasure--onLayout--dispatchDraw--onDraw
                } else if (index == 3) {
                    textView.setPadding(20, 20, 20, 20);//从MyRelativeLayout到MyTextView，从onMeasure--onLayout--dispatchDraw--onDraw
                } else if (index == 4) { //从MyRelativeLayout到MyTextView，从onMeasure--onLayout--dispatchDraw--onDraw
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
                    layoutParams.leftMargin = 80;
                    layoutParams.rightMargin = 80;
                    textView.setLayoutParams(layoutParams);
                } else if (index == 5) {//MyRelativeLayout(onMeasure--onLayout),,myTextView(onMeasure--onLayout),MyRelativeLayout(onDraw)
                    relativeLayout.setBackgroundColor(Color.YELLOW);
                } else if (index == 6) { //从MyRelativeLayout到MyTextView，onMeasure--onLayout; MyRelativeLayout的onDraw
                    relativeLayout.setPadding(50, 50, 50, 50);
                } else if (index == 7) {//从MyRelativeLayout到MyTextView，onMeasure--onLayout; MyRelativeLayout的onDraw
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
                    layoutParams.leftMargin = 80;
                    layoutParams.rightMargin = 80;
                    relativeLayout.setLayoutParams(layoutParams);
                } else if (index == 8) {//从MyRelativeLayout到MyTextView，从onMeasure--onLayout,MyRelativeLayout(onDraw)--myTextView的onDraw
                    textView.requestLayout();
                } else if (index == 9) { //myTextView的onDraw
                    textView.invalidate();
                }
                index += 1;

                break;
        }
    }

    //---------------------------------------触摸事件-------------------------
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("Activity------dispatchTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Activity------dispatchTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("Activity------dispatchTouchEvent------ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("Activity------onTouchEvent-------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Activity------onTouchEvent-------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("Activity------onTouchEvent------ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    //---------------------------------------按键事件-------------------------
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("Activity------dispatchKeyEvent------code=" + event.getKeyCode());
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                System.out.println("Activity------dispatchKeyEvent------KEYCODE_BACK");
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("Activity------onKeyDown------keyCode="+keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        System.out.println("Activity------onKeyUp------keyCode="+keyCode);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        System.out.println("Activity------onKeyLongPress------keyCode="+keyCode);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        System.out.println("Activity------onKeyMultiple------keyCode="+keyCode);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        System.out.println("Activity------onKeyShortcut------keyCode="+keyCode);
        return super.onKeyShortcut(keyCode, event);
    }

    //---------------------------------------其他-------------------------
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        System.out.println("Activity--------onUserInteraction");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        System.out.println("Activity--------onUserLeaveHint");
    }

}
