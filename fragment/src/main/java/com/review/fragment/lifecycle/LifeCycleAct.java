package com.review.fragment.lifecycle;

import android.content.Context;
import android.content.res.Configuration;
import android.media.midi.MidiManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.review.fragment.R;

/**
 * @author 张全
 */

public class LifeCycleAct extends FragmentActivity {
    LifeCycleFragment lifeCycleFragment;
    final String KEY = "LifeCycleFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        System.out.println("activity--onCreate");
        if (null != savedInstanceState) {
            lifeCycleFragment = (LifeCycleFragment) getSupportFragmentManager().getFragment(savedInstanceState, KEY);
        }
        System.out.println("savedInstanceState--lifeCycleFragment=" + lifeCycleFragment);
        if (null == lifeCycleFragment) {
            lifeCycleFragment = new LifeCycleFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment,lifeCycleFragment, "LifeCycleFragment").commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("activity--onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("activity--onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("activity--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("activity--onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("activity--onSaveInstanceState");
        if (null != lifeCycleFragment) {
            getSupportFragmentManager().putFragment(outState, KEY, lifeCycleFragment);
        }

    }

    //android:configChanges="orientation|screenSize|keyboardHidden" 控制横竖屏切换不走声明周期
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("activity--onConfigurationChanged");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("activity--onDestroy");
    }

    public static class LifeCycleFragment extends Fragment {
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            //当Fragment与Activity发生关联时调用。
            System.out.println("onAttach--" + this);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            System.out.println("onCreate--" + this);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            System.out.println("onCreateView--" + this);
            //创建该Fragment的视图
            return inflater.inflate(R.layout.lifecycle_frag,container,false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            System.out.println("onViewCreated--" + this);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            //当Activity的onCreate方法返回时调用
            System.out.println("onActivityCreated--" + this);
        }

        @Override
        public void onStart() {
            super.onStart();
            System.out.println("onStart--" + this);
        }

        @Override
        public void onResume() {
            super.onResume();
            System.out.println("onResume--" + this);
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
            System.out.println("onSaveInstanceState--" + this);
        }

        @Override
        public void onPause() {
            super.onPause();
            System.out.println("onPause--" + this);
        }

        @Override
        public void onStop() {
            super.onStop();
            System.out.println("onStop--" + this);
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            //与onCreateView想对应，当该Fragment的视图被移除时调用
            System.out.println("onDestroyView--" + this);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            System.out.println("onDestroy--" + this);
        }

        @Override
        public void onDetach() {
            super.onDetach();
            //与onAttach相对应，当Fragment与Activity关联被取消时调用
            System.out.println("onDetach--" + this);
        }

    }
}
