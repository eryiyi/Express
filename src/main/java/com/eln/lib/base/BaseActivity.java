package com.eln.lib.base;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import com.eln.lib.util.SystemEvent;

 /**
  * 碎片Fragment的Activity的基类
  * @author zhengxiaobin <gybin02@Gmail.com>
  * @since  2016/3/19 15:08
  */
public class BaseActivity extends FragmentActivity implements SystemEvent.IEventListener {
	protected Activity mContext;
	
    @Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mContext=this;
		
		Resources res = getResources();
		android.content.res.Configuration config=new android.content.res.Configuration(); 
		config.setToDefaults();  
		res.updateConfiguration(config,res.getDisplayMetrics() );
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		MobclickAgent.onResume(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		MobclickAgent.onPause(this);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	public void finish() {
		super.finish();
	}

	/**
	 * 消息传递处理
	 * @param msg
     */
    @Override
    public void onEvent(Message msg) {
        // TODO Auto-generated method stub
    }
}
