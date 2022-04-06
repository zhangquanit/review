package com.review.fragment.backstack;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment1";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println(TAG + "------------onAttach");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(TAG+"------------onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println(TAG+"------------onCreateView");
        TextView textView = new TextView(getContext());
        textView.setTextSize(30);
        textView.setText(TAG);
        return textView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println(TAG+"------------onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println(TAG+"------------onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println(TAG+"------------onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(TAG+"------------onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println(TAG+"------------onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println(TAG+"------------onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println(TAG+"------------onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(TAG+"------------onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println(TAG+"------------onDetach");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        System.out.println(TAG+"------------onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}
