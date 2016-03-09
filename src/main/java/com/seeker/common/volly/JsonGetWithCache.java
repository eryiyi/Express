package com.seeker.common.volly;

/**
 * 会缓存服务器请求，默认一天
 * @author ZhengXiaoBin 
 *  CreateTime: 2015年1月30日
 */
public class JsonGetWithCache{
//  extends BaseRequest {
//	
//	public JsonGetWithCache(String url, 
//			Map<String, String> headers, Listener<String> listener,
//			ErrorListener errorListener,long cacheTime) {
//		super(Method.GET, url, errorListener);
//		this.mHeaders = headers;
//		this.mListener = listener;
//		this.cacheTime=cacheTime;
//	}
//	
//	public JsonGetWithCache(String url, Listener<String> listener) {
//		super(Method.GET, url, null);
//		this.setShouldCache(true);
//		this.mHeaders = initHeader();
//		this.mListener = listener;
//		this.cacheTime=NdHttpHeaderParser.DAY;
//	}
//	
//	/**
//	 * 不使用缓存的服务器请求
//	 * @param url
//	 * @param withoutCache
//	 * @param listener
//	 */
//	public JsonGetWithCache(String url, boolean withoutCache,Listener<String> listener) {
//		super(Method.GET, url, null);
//		this.mHeaders = initHeader();
//		this.mListener = listener;
//		if(!withoutCache){
//			this.setShouldCache(true);
//			this.cacheTime=NdHttpHeaderParser.DAY;	
//		}
//	}
//	
//	public JsonGetWithCache(String url, Listener<String> listener,ErrorListener errorListener) {
//		super(Method.GET, url, errorListener);
//		this.setShouldCache(true);
//		this.mHeaders = initHeader();
//		this.mListener = listener;
//		this.cacheTime=NdHttpHeaderParser.DAY;
//	}

}
