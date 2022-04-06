package com.review.fragment.retain;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

/**
 * 使用Fragment保存异步任务，并将Fragment.setRetainInstance(true)，旋转屏幕时，销毁dialog
 */
public class FixProblemsActivity extends ListActivity
{
	private static final String TAG = "MainActivity";
	private ListAdapter mAdapter;
	private List<String> mDatas;
	private OtherRetainedFragment dataFragment;
	private MyAsyncTask mMyTask;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");

		FragmentManager fm = getFragmentManager();
		dataFragment = (OtherRetainedFragment) fm.findFragmentByTag("data");

		if (dataFragment == null)
		{
			dataFragment = new OtherRetainedFragment();
			fm.beginTransaction().add(dataFragment, "data").commit();
		}
		//获取保留的异步任务对象
		mMyTask = dataFragment.getData();
		if (mMyTask != null)
		{
			mMyTask.setActivity(this);
		} else
		{
			mMyTask = new MyAsyncTask(this);
			dataFragment.setData(mMyTask);
			mMyTask.execute();
		}
	}


	@Override
	protected void onRestoreInstanceState(Bundle state)
	{
		super.onRestoreInstanceState(state);
		Log.e(TAG, "onRestoreInstanceState");
	}


	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		mMyTask.setActivity(null); //关闭Dialog，并将dialog引用的Activity置为null.
		super.onSaveInstanceState(outState);
		Log.e(TAG, "onSaveInstanceState");
	}

	@Override
	protected void onDestroy()
	{
		Log.e(TAG, "onDestroy");
		super.onDestroy();

	}
	/**
	 * 回调
	 */
	public void onTaskCompleted()
	{
		mDatas = mMyTask.getItems();
		mAdapter = new ArrayAdapter<String>(FixProblemsActivity.this,
				android.R.layout.simple_list_item_1, mDatas);
		setListAdapter(mAdapter);
	}

}
