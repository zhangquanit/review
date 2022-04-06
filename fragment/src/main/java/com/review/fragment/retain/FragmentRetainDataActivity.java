package com.review.fragment.retain;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.review.fragment.R;

/**
 * 1、适用于数据加载完毕后
 * 设置Fragment.setRetainInstance(true);
 * 在Activity的onDestroy中保存数据状态。
 * 2、如果在加载的时候，进行旋转，则会发生错误，异常退出（退出原因：dialog.dismiss()时发生NullPointException，因为与当前对话框绑定的FragmentManager为null）
 *  java.lang.NullPointerException: Attempt to invoke virtual method 'android.app.FragmentTransaction android.app.FragmentManager.beginTransaction()' on a null object reference
 */
public class FragmentRetainDataActivity extends Activity
{

	private static final String TAG = "FragmentRetainDataActivity";
	private RetainedFragment dataFragment;
	private DialogFragment mLoadingDialog;
	private ImageView mImageView;
	private Bitmap mBitmap;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e(TAG, "onCreate");

		FragmentManager fm = getFragmentManager();
		dataFragment = (RetainedFragment) fm.findFragmentByTag("data");
		if (dataFragment == null)
		{
			dataFragment = new RetainedFragment();
			fm.beginTransaction().add(dataFragment, "data").commit();
		}
		mBitmap = collectMyLoadedData();
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData()
	{
		mImageView = (ImageView) findViewById(R.id.id_imageView);
		if (mBitmap == null)
		{
			mLoadingDialog = new LoadingDialog();
			mLoadingDialog.show(getFragmentManager(), "LOADING_DIALOG");
			RequestQueue newRequestQueue = Volley
					.newRequestQueue(FragmentRetainDataActivity.this);
			ImageRequest imageRequest = new ImageRequest(
					"http://img.my.csdn.net/uploads/201407/18/1405652589_5125.jpg",
					new Response.Listener<Bitmap>()
					{
						@Override
						public void onResponse(Bitmap response)
						{
							mBitmap = response;
							mImageView.setImageBitmap(mBitmap);
							// load the data from the web
							dataFragment.setData(mBitmap);
							mLoadingDialog.dismiss();
						}
					}, 0, 0, Config.RGB_565, null);
			newRequestQueue.add(imageRequest);
		} else
		{
			mImageView.setImageBitmap(mBitmap);
		}

	}

	@Override
	public void onDestroy()
	{
		Log.e(TAG, "onDestroy");
		super.onDestroy();
		// store the data in the fragment
		dataFragment.setData(mBitmap);
	}

	private Bitmap collectMyLoadedData()
	{
		return dataFragment.getData();
	}

}
