package com.seeker.common.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.eln.lib.R;
import com.eln.lib.util.log.MLog;
import com.eln.lib.util.network.NetworkUtil;
import com.seeker.base.BaseAct;
import com.seeker.common.ActionBarUtil;

/**
 * 网页WebView的基类
 */
public class CommonWebViewAct extends BaseAct {
	public WebView mWebView;
	public String mUrl;
	private ProgressBar mProgressBar;

	public static void launcher(Context mContext, String url) {
		Intent intent = new Intent(mContext, CommonWebViewAct.class);
		intent.putExtra("url", url);
		mContext.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wv_act);
		mUrl = getIntent().getStringExtra("url");
		initView();
		initWebView();
		loadUrl(mUrl);
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
			MLog.e(e.toString());
		}

	}

	private void initView() {
		mWebView = (WebView) findViewById(R.id.wv_layout);
		ActionBarUtil.initLeft(this, null);
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
	
	public  void initWebView() {
		initWebViewSettting(mContext, mWebView);
		mWebView.setWebChromeClient(new MyWebChromeClient(this, mProgressBar));
		mWebView.setWebViewClient(new MyWebViewClient(mContext, mUrl));
		mWebView.setDownloadListener(new MyWebViewDownLoadListener(mContext));
		mWebView.addJavascriptInterface(mContext, "ndWeb");
	}

	public static void initWebViewSettting(Context mContext,
			WebView mWebView) {
		WebSettings webSettings = mWebView.getSettings();
		// 应用可以有缓存
		webSettings.setAppCacheEnabled(true);
		// 离线缓存(先启动本地LOAD_CACHE_ELSE_NETWORK)
		if(NetworkUtil.isNetworkConnected(mContext)){
			webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);	
		}else{
			webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		}
		
		String dir = mContext.getApplicationContext()
				.getDir("cache", Context.MODE_PRIVATE).getPath();
		webSettings.setAppCachePath(dir);
		// 设置可以使用localStorag
		webSettings.setDomStorageEnabled(true);
		// 应用可以有数据库
		webSettings.setDatabaseEnabled(true);
		// 可以读取文件缓存(manifest生效)
		webSettings.setAllowFileAccess(true);
		// 根据页面的值缩放
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		// 设置一下，确保cookie可用
		CookieSyncManager cookieSyncManager = CookieSyncManager
				.createInstance(mContext);
		cookieSyncManager.sync();
		cookieSyncManager.startSync();
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.setCookie("cookie", "ddddadf");
		// 设置隐藏缩放按钮
		webSettings.setBuiltInZoomControls(false);
		// 不支持缩放
		webSettings.setSupportZoom(false);
		webSettings.setLoadsImagesAutomatically(true);
		// 设置支持javascript脚本
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
	}



	/** 加载网页 */
	public void loadUrl(String url) {
		try {
			if (!this.isFinishing() && mWebView != null) {
				mWebView.loadUrl(url);
			}
		} catch (Exception e) {
			MLog.e(e.toString());
		}
	}

	@Override
	public void onBackPressed() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			this.finish();
		}
	}

	/**
	 * 网页出错页
	 */
	@JavascriptInterface
	public void refresh() {
		if(NetworkUtil.isNetworkConnected(this)){
			loadUrl(mUrl);
			mWebView.clearHistory();			
		}
	}

}
