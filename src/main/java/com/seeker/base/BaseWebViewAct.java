package com.seeker.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.eln.lib.R;
import com.eln.lib.util.log.MLog;
import com.seeker.common.ActionBarUtil;
import com.seeker.common.web.CommonWebViewAct;
import com.seeker.common.web.MyWebChromeClient;
import com.seeker.common.web.MyWebViewClient;
/**
 * 网页WebView的基类
 */
public abstract class BaseWebViewAct extends BaseAct {
		

	private static final String TAG = "BaseWebViewActivity.java";

	public WebView mWebView;
	public String mUrl;
//	public String hostUrl;

	private ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wv_act);
		initUrl();
		initView();
		initWebView();
		loadUrl(mUrl);
		ActionBarUtil.initLeft(this, null);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			if (mWebView != null) {
				mWebView.removeAllViews();
				mWebView.destroy();
			}
		} catch (Exception e) {
			MLog.e(TAG, ">>>>>>>>>>> onDestroy() <<<<<<<<<<<<<", e);
		}

	}

	private void initView() {
		ActionBarUtil.initLeft(this, null);
		mWebView = (WebView) findViewById(R.id.wv_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_layout);
        mWebView.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
                    mWebView.goBack();
                    return true;
                }
                return false;
            }

        });
	}

	public void initWebView() {
		CommonWebViewAct.initWebViewSettting(mContext,mWebView);
		mWebView.setWebChromeClient(new MyWebChromeClient(mContext,mProgressBar));
		mWebView.setWebViewClient(new MyWebViewClient(mContext, mUrl));
		mWebView.addJavascriptInterface(this,"ndWeb");
	}

	
	/** 加载网页 */
	public void loadUrl(String url){
		try {
			if(!this.isFinishing() && mWebView != null){
				mWebView.loadUrl(url);
			}
		} catch (Exception e) {
			MLog.e(e.toString());
		}
	}
	
	@JavascriptInterface
	public void refresh(){
		loadUrl(mUrl);
		mWebView.clearHistory();
	}
	
	public abstract  void initUrl();
	
}
