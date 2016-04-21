package com.eln.lib.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.eln.lib.R;
import com.eln.lib.common.web.CommonWebViewActivity;
import com.eln.lib.common.web.MyWebChromeClient;
import com.eln.lib.common.web.MyWebViewClient;
import com.eln.lib.util.log.MLog;


/**
 * 碎片 Fragment的基类 
 */
public abstract class BaseWebFragment extends BaseFragment {

    private static final String TAG = "BaseWebFragment";
    public WebView mWebView;
    public String localUrl;
//    public String hostUrl;
    public View mView;
	private ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.wv_layout, null);
        initUrl();
        initView();
   
        loadUrl(localUrl);
        return mView;
    }

    @Override
    public void onDestroy() {
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

    public void initView() {
        mWebView = (WebView) mView.findViewById(R.id.wv_layout);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.pb_layout);
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
        initWebView();
    }

    public   void initWebView() {
        CommonWebViewActivity.initWebViewSettting(mContext, mWebView);
        mWebView.setWebChromeClient(new MyWebChromeClient(null, mProgressBar));
        mWebView.setWebViewClient(new MyWebViewClient(mContext, localUrl));
//        mWebView.addJavascriptInterface(mContext,"ndWeb");
    }



    /** 加载网页 */
    public void loadUrl(String url){
        try {
            if(!getActivity().isFinishing() && mWebView != null){
                mWebView.loadUrl(url);
            }
        } catch (Exception e) {
            MLog.e(e.toString());
        }
    }

//    @JavascriptInterface
//    public void app_call(final String id,String callType,String json,String url){
//        if("GET".equalsIgnoreCase(callType)){
//            Param param=new Param();
//            if(!TextUtils.isEmpty(json)){
//                try {
//                    JSONObject obj=new JSONObject(json);
//                    Iterator<String> itr=obj.keys();
//                    while(itr.hasNext()){
//                        String key=itr.next();
//                        param.put(key, obj.optString(key, ""));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            url=hostUrl+url;
//            if(param.size()>0){
//                url=Param.appendToUrl(param, url);
//            }
//            new JsonGet(url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String arg0) {
//                    loadUrl("javascript:app_callback('"+id+"','"+arg0+"')");
//
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError arg0) {
//                    Toast.makeText(mContext, "请求失败，请重试", Toast.LENGTH_LONG).show();
//                }
//            }).request();
//        }else if("POST".equalsIgnoreCase(callType)){
//            url= hostUrl+url;
//            new JsonPost(url, json.toString().getBytes(), new Response.Listener<String>() {
//                @Override
//                public void onResponse(String arg0) {
//                    loadUrl("javascript:app_callback('"+id+"','"+arg0+"')");
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError arg0) {
//                    Toast.makeText(mContext, "请求失败，请重试",Toast.LENGTH_LONG).show();
//                }
//            }).request();
//        }
//    }



    public abstract void  initUrl();
}
