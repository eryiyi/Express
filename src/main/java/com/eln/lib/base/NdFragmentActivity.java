package com.eln.lib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class NdFragmentActivity extends FragmentActivity {
	protected  Activity mContext;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mContext=this;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		  MobclickAgent.onResume(this);       //统计时长
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		  MobclickAgent.onPause(this);       //统计时长
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	public void finish() {
		super.finish();
	}
	
}
