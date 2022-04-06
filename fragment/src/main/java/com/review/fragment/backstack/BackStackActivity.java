package com.review.fragment.backstack;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.review.fragment.R;

import java.util.List;
import java.util.Stack;

/**
 * 1、后退栈主要是针对一次FragmentTransaction事务提交
 *  fragmentTransaction.addToBackStack(name)；
 *  也就是说如果在本次的事务中执行了多次add或replace，那么这一组操作都在一个事务中，也会同时入后退栈，退栈时，也是到当前事务。
 *  2、getSupportFragmentManager().findFragmentByTag(tag)
 *  查询通过 tranaction.replace(R.id.container, fragment,tag);或者tranaction.add(R.id.container, fragment,tag)中设置的tag对应的Fragment
 *  3、getSupportFragmentManager().findFragmentById(id)
 *   查询通过在layout中 <fragment >标签中的对应id的Fragment
         <fragment
             android:id="@+id/fragment1"
             android:name="com.example.fragmentblog2.MyFragment"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_alignParentLeft="true"
             android:layout_alignParentTop="true"
             android:layout_marginTop="143dp" />
 */
public class BackStackActivity extends AppCompatActivity implements View.OnClickListener {
    private Stack<Integer> ids = new Stack<>();
    private Stack<String> names = new Stack<>();
    private Stack<String> tags = new Stack<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backstack);
        findViewById(R.id.backstack1).setOnClickListener(this);
        findViewById(R.id.backstack2).setOnClickListener(this);
        findViewById(R.id.backstack3).setOnClickListener(this);
        findViewById(R.id.popback).setOnClickListener(this);
        findViewById(R.id.popback2).setOnClickListener(this);
        findViewById(R.id.popback3).setOnClickListener(this);


        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                System.out.println("onBackStackChanged===================beigin");
                int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
                System.out.println("backStackEntryCount=" + backStackEntryCount);
                for (int i = 0; i < backStackEntryCount; i++) {
                    FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(i);
                    System.out.println("index=" + i + "->" + backStackEntry.getName());
                }
                List<Fragment> fragments = getSupportFragmentManager().getFragments(); //当前容器内
                if(!ids.isEmpty()){
                    int id = ids.get(ids.size() - 1);
                    /**
                     *  查询通过在layout中 <fragment >标签中的对应id的Fragment
                     <fragment
                         android:id="@+id/fragment1"
                         android:name="com.example.fragmentblog2.MyFragment"
                         android:layout_width="wrap_content"
                         android:layout_height="wrap_content"
                         android:layout_alignParentLeft="true"
                         android:layout_alignParentTop="true"
                         android:layout_marginTop="143dp" />
                     */

                    Fragment fragmentById = getSupportFragmentManager().findFragmentById(id);
                    System.out.println("findFragmentById("+id+")="+fragmentById);
                }

                if(!tags.isEmpty()){
                    String tag = tags.elementAt(tags.size() - 1);
                    //查询通过 tranaction.replace(R.id.container, fragment,tag);或者tranaction.add(R.id.container, fragment,tag)中设置的tag对应的Fragment
                    Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(tag);
                    System.out.println("findFragmentByTag("+tag+")="+fragmentByTag);
                }
                System.out.println("fragments=" + fragments);
                System.out.println("onBackStackChanged====================end");
            }
        });
    }

    private void pushFragment(Class<? extends  Fragment> cls){
        String name = cls.getName();
        String tag=cls.getSimpleName();
        Fragment fragment = Fragment1.instantiate(this, cls.getName());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment,tag);
        fragmentTransaction.addToBackStack(name); //将本次的Transaction加入到后台栈中
        int id = fragmentTransaction.commit();

        ids.push(id);
        names.push(name);
        tags.push(tag);
    }

    private void popStack(int way) {
        int id = -100;
        if (!ids.isEmpty()) {
            id = ids.pop();
        }
        String name = "invalidate";
        if (!names.isEmpty()) {
            name = names.pop();
        }
        if (!tags.isEmpty()) {
            tags.pop();
        }
        switch (way) {
            case 0: //方式1、直接弹出栈顶的Fragmnet
                getSupportFragmentManager().popBackStack();
                break;
            case 1:  //方式2、按commit的id弹出栈
                if (id != -100) {
                    getSupportFragmentManager().popBackStack(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                break;
            case 2:  //方式3、按backstack的名字弹出栈
                if (!TextUtils.isEmpty(name)) {
                    getSupportFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backstack1:
                pushFragment(Fragment1.class);
                break;
            case R.id.backstack2:
                pushFragment(Fragment2.class);
                break;
            case R.id.backstack3:
                pushFragment(Fragment3.class);
                break;
            case R.id.popback:
                popStack(0);
                break;
            case R.id.popback2:
                popStack(1);
                break;
            case R.id.popback3:
                popStack(2);
                break;
        }
    }
}
