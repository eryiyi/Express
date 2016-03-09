package com.seeker.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;

import com.eln.lib.base.NdFragmentActivity;
import com.eln.lib.util.SystemEvent;

/**
 * 碎片Fragment的Activity的基类
 * @author ZhengXiaoBin 
 *  CreateTime: 2014年8月20日
 */
public class BaseAct extends NdFragmentActivity implements SystemEvent.IEventListener {

    @Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
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

    @Override
    public void onEvent(Message msg) {
        // TODO Auto-generated method stub

    }
}
