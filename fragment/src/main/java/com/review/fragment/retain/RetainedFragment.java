package com.review.fragment.retain;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

/**
 * 声明需要保存的数据对象，然后提供getter和setter，注意，一定要在onCreate调用setRetainInstance(true);
 */
public class RetainedFragment extends Fragment
{
	private static final String TAG = "RetainedFragment";
	// 需要保存的数据，提供setter和getter，在Activity中的onDestory方法保存数据
	private Bitmap data;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		/**
		 * 保留状态，不能用于back stack中的Fragment，当Activity重新创建时，生命周期稍微不同:
		 * onDestroy不会被调用，onDetach会调用
		 * onCreate不会被调用
		 * onAttach，OnActivityCreated会调用
		 */
		Log.e(TAG,"onCreate");

	}

	/**
	 * 在Activity中的onDestory方法保存数据
	 * @param data
     */
	public void setData(Bitmap data)
	{
		this.data = data;
	}

	public Bitmap getData()
	{
		return data;
	}


	//------------------------------------------------
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG,"onDestroy");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e(TAG,"onDestroyView");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.e(TAG,"onDetach");
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.e(TAG,"onAttach");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e(TAG,"onActivityCreated");
	}
}