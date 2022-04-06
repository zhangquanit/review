package com.review.fragment.adapter;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.review.fragment.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangquan
 */
public class TabAct extends AppCompatActivity {

    private ViewGroup container;
    private SparseArray<Fragment> fragmentList = new SparseArray(4);
    private List<Class> fragementClass = new ArrayList(Arrays.asList(ItemFragment.class, ItemFragment.class, ItemFragment.class, ItemFragment.class));
    private TabPagerAdapter tabPagerAdapter;
    private int currentPos = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        container = findViewById(R.id.container);
        LinearLayout bottomBar = findViewById(R.id.bottom_bar);
        int childCount = bottomBar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final int pos = i;
            bottomBar.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCurrentItem(pos);
                }
            });
        }
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        setCurrentItem(0);
    }

    private void setCurrentItem(int pos) {
        if (currentPos == pos) return;
        currentPos = pos;
        Fragment frag = (Fragment) tabPagerAdapter.instantiateItem(container, pos); //如果没有添加过，则创建Fragment 回调adapter的getItem
        tabPagerAdapter.setPrimaryItem(container, pos, frag);//切换当前显示的Fragment，通过setUserVisibleHint 控制view的显示或隐藏
        tabPagerAdapter.finishUpdate(container);
    }

    private class TabPagerAdapter extends FragmentPagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = fragmentList.get(position);
            if (null == fragment) {
                Bundle bundle = ItemFragment.getBundle("ItemFrag-" + position);
                fragment = Fragment.instantiate(TabAct.this, fragementClass.get(position).getName(), bundle);
                fragmentList.put(position, fragment);
            }
            //通过setUserVisibleHint 控制View的VISIBLE或GONE
            ItemFragment itemFragment = (ItemFragment) fragment;
            itemFragment.setIntercepterVisibleHint(true);
            return fragment;
        }

        @Override
        public int getCount() {
            return fragementClass.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            System.out.println("getItemPosition");
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
