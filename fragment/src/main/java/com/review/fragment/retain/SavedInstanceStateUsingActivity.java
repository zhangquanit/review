package com.review.fragment.retain;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1、数据加载完毕后，使用onSaveInstanceState和onRestoreInstanceState保存数据
 * 旋转屏幕：
 * onSaveInstanceState
 * onDestroy
 * onCreate
 * onRestoreInstanceState
 * 2、如果在加载的时候，进行旋转，则会发生错误，异常退出（退出原因：dialog.dismiss()时发生NullPointException，因为与当前对话框绑定的FragmentManager为null）
 *  java.lang.NullPointerException: Attempt to invoke virtual method 'android.app.FragmentTransaction android.app.FragmentManager.beginTransaction()' on a null object reference

 * @author zhy
 */
public class SavedInstanceStateUsingActivity extends ListActivity {
    private static final String TAG = "MainActivity";
    private ListAdapter mAdapter;
    private ArrayList<String> mDatas;
    private DialogFragment mLoadingDialog;
    private LoadDataAsyncTask mLoadDataAsyncTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        initData(savedInstanceState);
    }

    /**
     * 初始化数据
     */
    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) //savedInstanceState和onRestoreInstanceState方法的参数一样
            mDatas = savedInstanceState.getStringArrayList("mDatas");
            Log.e(TAG, "onCreate ，mDatas=" + mDatas);


        if (mDatas == null) {
            mLoadingDialog = new LoadingDialog();
            mLoadingDialog.show(getFragmentManager(), "LoadingDialog");
            mLoadDataAsyncTask = new LoadDataAsyncTask();
            mLoadDataAsyncTask.execute();

        } else {
            initAdapter();
        }

    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new ArrayAdapter<String>(
                SavedInstanceStateUsingActivity.this,
                android.R.layout.simple_list_item_1, mDatas);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        List<String> mDatas = state.getStringArrayList("mDatas");
        Log.e(TAG, "onRestoreInstanceState， mDatas=" + mDatas);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
        outState.putSerializable("mDatas", mDatas);

    }

    /**
     * 模拟耗时操作
     *
     * @return
     */
    private ArrayList<String> generateTimeConsumingDatas() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return new ArrayList<String>(Arrays.asList("通过Fragment保存大量数据",
                "onSaveInstanceState保存数据",
                "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
                "Spark"));
    }

    private class LoadDataAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            mDatas = generateTimeConsumingDatas();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            System.out.println("mLoadingDialog="+mLoadingDialog);
            mLoadingDialog.dismiss();
            initAdapter();
        }
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

}
