package com.eln.lib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class NdActivity extends Activity {
	protected Context mContext;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
//		UmengUpdateAgent.update(this);
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
	 * 请求数据
	 * @param url 请求的Url
	 * @param responseListener 请求回调
	 */
//	protected void executeRequest(String url,Response.Listener<String> responseListener){
//		StringRequest request=new StringRequest(Method.GET, url, responseListener,
//				errorListener());		
//		RequestUtil.addRequest(request, this);
//	}
//	
//	protected Response.ErrorListener errorListener() {
//		return new Response.ErrorListener() {
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				Toast.makeText(NdActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
//			}
//		};
//	}
}
