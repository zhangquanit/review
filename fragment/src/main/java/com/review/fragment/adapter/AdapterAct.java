package com.review.fragment.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;

import com.review.fragment.R;

/**
 * @author zhangquan
 */
public class AdapterAct extends AppCompatActivity {
    private static String[] mTitles = new String[]{
            FragmentPagerAdapterAct.class.getSimpleName(),
            FragmentStatePagerAdapterAct.class.getSimpleName(),
            TabAct.class.getSimpleName()
    };

    private static Class[] mActivities = new Class[]{
            FragmentPagerAdapterAct.class,
            FragmentStatePagerAdapterAct.class,
            TabAct.class
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        getSupportFragmentManager().beginTransaction().add(R.id.container,new MyListFrag()).commit();

    }
    public static class MyListFrag extends ListFragment {

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            setListAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mTitles));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            startActivity(new Intent(getContext(), mActivities[position]));
        }
    }
}
