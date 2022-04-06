package com.review.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * 横竖屏切换
 * 1、显示不同的布局
 * 取名相同的layout，横屏的布局放在layout-land下
 * 2、进入页面时(调用onCreate)加载布局 ，横竖屏layout中相同的id的控件能够使用，不同的id只能在onCreate加载的layout中使用。
 * 3、对于Fragment，横竖屏切换与Activity类似，会调用onConfigurationChanged，不会走其他生命周期函数。
 */

public class ConfigChangeActivity extends FragmentActivity {
    private TextView textView1;
    private Button btn_portarit; //只有竖屏才有
    private Button btn_land; //只有横屏才有

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configchange);
        System.out.println("------onCreate");
        textView1 = (TextView) findViewById(R.id.tv1);
        btn_portarit = (Button) findViewById(R.id.btn_portarit);
        btn_land = (Button) findViewById(R.id.btn_land);

        if (null != btn_portarit) {
            btn_portarit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ConfigChangeActivity.this, "点击竖屏按钮", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (null != btn_land) {
            btn_land.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ConfigChangeActivity.this, "点击横屏按钮", Toast.LENGTH_SHORT).show();
                }
            });
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout, MyFragment.instantiate(this, MyFragment.class.getName())).commit();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("------------onConfigurationChanged,,orientation=" + newConfig.orientation);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            System.out.println("-----竖屏,textView=" + textView1);
            textView1.setText("切换到竖屏");

        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            System.out.println("-----横屏,textView=" + textView1);
            textView1.setText("切换到横屏");
        }
    }

    public static class MyFragment extends Fragment {
        private TextView textView;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            System.out.println("MyFragment-------onAttach");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            System.out.println("MyFragment-------onCreate");
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            System.out.println("MyFragment-------onCreateView");
            textView = new TextView(getContext());
            textView.setText(isPortarit(getContext())?"竖屏(Frament)":"橫屏(Frament)");
            textView.setTextSize(20);
            return textView;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            System.out.println("MyFragment-------onActivityCreated");
        }

        @Override
        public void onStart() {
            super.onStart();
            System.out.println("MyFragment-------onStart");
        }

        @Override
        public void onResume() {
            super.onResume();
            System.out.println("MyFragment-------onResume");
        }

        @Override
        public void onPause() {
            super.onPause();
            System.out.println("MyFragment-------onPause");
        }

        @Override
        public void onStop() {
            super.onStop();
            System.out.println("MyFragment-------onStop");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            System.out.println("MyFragment-------onDestroyView");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            System.out.println("MyFragment-------onDestroy");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            System.out.println("MyFragment-------onDetach");
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            System.out.println("Fragment------------onConfigurationChanged,,orientation=" + newConfig.orientation);
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                textView.setText("竖屏(Frament)");

            } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                textView.setText("横屏(Fragment)");
            }
        }
    }


    public static boolean isPortarit(Context ctx){
        if (ctx.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return true;
        }
       return false;
    }
}
