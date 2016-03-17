package com.eln.lib.common.web;

import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    private static final String TAG = "MyWebViewClient.java";
    private Context mContext;
	private String mUrl;
    public MyWebViewClient(Context context,String url) {
    	mContext=context;
    	mUrl=url;
	}

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		//域名一致不新开页面，不一致新开页面。
		URL tempUrl;
		try {
			String localHost=new URL(mUrl).getHost();
			tempUrl = new URL(url);
			tempUrl.getHost();
			if(!localHost.equals(tempUrl.getHost())){
				CommonWebViewAct.launcher(mContext, url);
				return true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return super.shouldOverrideUrlLoading(view, url);
    }
    
    @Override
    /** 开始加载网页 */
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//        MLog.d(TAG, "onPageStarted() -- 开始加载网页 -- url:" + url);
        super.onPageStarted(view, url, favicon);
    }

    @Override
    /** 网页加载完成 */
    public void onPageFinished(WebView view, final String url) {
//        MLog.d(TAG, "onPageFinished() -- 网页加载完成 -- url:" + url);
        super.onPageFinished(view, url);
//        try {
//            if (TextUtils.isEmpty(url)) {
//                getActivity().onBackPressed();
//            } else {
//                onPageFinishedEvent(view, url);
//            }
//        } catch (Exception e) {
//            MLog.e(TAG, ">>>>>>>>>>> initView() <<<<<<<<<<<<<", e);
//        }

    }
    

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
//        MLog.e(TAG, "errorCode=" + errorCode + ",description="
//                + description + ",failingUrl=" + failingUrl);
		String errorHtml="file:///android_asset/html/error.html";
		view.loadUrl(errorHtml);
    }
}
