package com.review.fragment.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.review.fragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangquan
 */
public class FragmentPagerAdapterAct extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    final int count = 4;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Activity--onCreate");
        setContentView(R.layout.activity_pager_adapter);

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabLayout.getTabCount() < count) {
                    for (int i = 3; i <= count; i++) {
                        dataList.add("标题" + i);
                        TabLayout.Tab tab = tabLayout.newTab();
                        LinearLayout view=tab.view;
                        view.setBackgroundColor(Color.TRANSPARENT);
                        tab.setText(dataList.get(i - 1));
                        tabLayout.addTab(tab);
                    }
                    viewPager.getAdapter().notifyDataSetChanged();
                }else{
                    viewPager.getAdapter().notifyDataSetChanged();
                }
            }
        });

        tabLayout = findViewById(R.id.tablayout);
        for (int i = 1; i <= 2; i++) {
            dataList.add("标题" + i);
            TabLayout.Tab tab = tabLayout.newTab();
            LinearLayout view=tab.view;
            view.setBackgroundColor(Color.TRANSPARENT);
            tab.setText(dataList.get(i - 1));
            tabLayout.addTab(tab);
        }
        viewPager = findViewById(R.id.viewpager);


        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ItemFragment page = new ItemFragment();
            page.setArguments(ItemFragment.getBundle(dataList.get(position)));
            return page;
        }


        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;  //以触发销毁对象以及重建对象。
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return dataList.get(position);
        }

    }

    //------------------------------

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Activity--onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Activity--onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Activity--onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Activity--onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("Activity--onSaveInstanceState");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Activity--onDestroy");
    }

}
