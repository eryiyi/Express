package com.seeker.liuhe.ui.home;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.ImageView;

import com.eln.lib.R;
import com.eln.lib.util.SystemEvent;
import com.eln.lib.base.BaseWebFragment;
import com.eln.lib.common.ActionBarUtil;
import com.eln.lib.common.SystemEventID;

/**
 * 首页
 */
public class MainWebFragment extends BaseWebFragment {
	public static final String URL="http://cos.99.com/wei/index.html";
	
	
	public static MainWebFragment newInstance() {
		MainWebFragment fragment = new MainWebFragment();
		fragment.setArguments(new Bundle());
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	  public void initUrl(){
	        localUrl= URL;
	    }
	  
	@Override
	public void initView() {
		super.initView();
		ActionBarUtil.initTitle(mView, "首页");
		initSideLeft(super.mView);
		
		super.mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
	}

	public static void initSideLeft(View mView) {
		ImageView ivLeft=(ImageView) mView.findViewById(R.id.title_back);
		ivLeft.setVisibility(View.VISIBLE);
		ivLeft.setImageResource(R.drawable.common_menu);
		ivLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Message msg=new Message();
				msg.what=SystemEventID.INS_TOGGLE_SIDEBAR;
				SystemEvent.fireEvent(msg);
				
			}
		});
	}
	
}
