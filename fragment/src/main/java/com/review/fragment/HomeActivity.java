package com.review.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.review.fragment.adapter.AdapterAct;
import com.review.fragment.adapter.FragmentStatePagerAdapterAct;
import com.review.fragment.backstack.BackStackActivity;
import com.review.fragment.lifecycle.LifeCycleAct;
import com.review.fragment.retain.RetainHomeActivity;

public class HomeActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}


	public void adapter(View v){
		Intent intent = new Intent(this, AdapterAct.class);
		startActivity(intent);
	}
	public void lifecycle(View v){
		Intent intent = new Intent(this, LifeCycleAct.class);
		startActivity(intent);
	}
	public void retainTest(View view){
		Intent intent = new Intent(this, RetainHomeActivity.class);
		startActivity(intent);
	}
	public void backtackTest(View view){
		Intent intent = new Intent(this, BackStackActivity.class);
		startActivity(intent);
	}
}
