package com.review.fragment.retain;

import android.app.Fragment;
import android.os.Bundle;

/**
 * 保存对象的Fragment
 * 
 * @author zhy
 * 
 */
public class OtherRetainedFragment extends Fragment
{
	// 保存一个异步的任务
	private MyAsyncTask data;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	public void setData(MyAsyncTask data)
	{
		this.data = data;
	}

	public MyAsyncTask getData()
	{
		return data;
	}

	
}