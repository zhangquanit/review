package com.review.layout_optimize;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @author zhangquan
 */
public class FrameFragActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_layout);
        getSupportFragmentManager().beginTransaction().add(R.id.content_container, new TestFrag(),"TestFrag").commitAllowingStateLoss();

    }




    public static  class TestFrag extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            System.out.println("container="+container);
            //merge作为根布局，attachToRoot必须为true  由于已经添加到了container，所以这里就不用再返回View了，返回null即可。
            View view= inflater.inflate(R.layout.test_frag, container, true);
            System.out.println("view="+view);
            return null;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }
}
