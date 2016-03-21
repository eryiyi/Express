package com.eln.lib.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.eln.lib.R;
import com.eln.lib.common.web.CommonWebViewActivity;
import com.eln.lib.util.log.MLog;
import com.eln.lib.common.ActionBarUtil;
import com.eln.lib.common.web.MyWebChromeClient;
import com.eln.lib.common.web.MyWebViewClient;
/**
 * 网页WebView的基类
 */
public abstract class BaseWebViewActivity extends BaseActivity {
		

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
		CommonWebViewActivity.initWebViewSettting(mContext,mWebView);
		mWebView.setWebChromeClient(new MyWebChromeClient(this,mProgressBar));
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
