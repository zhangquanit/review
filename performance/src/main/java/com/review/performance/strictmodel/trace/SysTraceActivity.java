package com.review.performance.strictmodel.trace;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.os.Trace;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.review.performance.strictmodel.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用systrace分析
 */
public class SysTraceActivity extends Activity implements View.OnClickListener {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trace_layou);
        findViewById(R.id.method1).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview);

        Debug.startMethodTracing();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Debug.stopMethodTracing();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.method1:
                ArrayList<String> mDataList = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    mDataList.add("Item" + i);
                }

                MyListAdapter listAdapter = new MyListAdapter(mDataList);
                listView.setAdapter(listAdapter);
                break;
        }
    }

    public class MyListAdapter extends BaseAdapter {
        List<String> mDataList;

        public MyListAdapter(List<String> list) {
            this.mDataList = list;
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Trace.beginSection("MyListAdapter.getView");
            convertView = new TextView(SysTraceActivity.this);
            convertView.setPadding(0, 30, 0, 30);

            for (int i = 0; i < 3 * 1000; i++) {
                System.out.print("aaa");
            }
            System.out.println();
            TextView textView = (TextView) convertView;
            textView.setText(mDataList.get(position));
            Trace.endSection();

            doTask();
            return convertView;
        }

        private void doTask() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Trace.beginSection("doTask");
                    for (int i = 0; i < 1 * 1000; i++) {
                        System.out.print("aaa");
                    }
                    System.out.println();
                    Trace.endSection();
                }
            }, "myThread").start();

        }

    }


}
