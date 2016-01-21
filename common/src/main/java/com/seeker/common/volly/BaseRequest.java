package com.seeker.common.volly;

public abstract class BaseRequest{
}
//public abstract class BaseRequest extends Request<String> {
//
//	protected Listener<String> mListener;
//	protected Map<String, String> mHeaders;
//	protected byte[] mBody;
//	protected long cacheTime = 1;// NdHttpHeaderParser.DAY;
//
//	public BaseRequest(int method, String url, ErrorListener listener) {
//		super(method, url, listener);
//		this.setShouldCache(false);
//		this.setRetryPolicy(new DefaultRetryPolicy(10000,
//				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//	}
//
//	public static Map<String, String> initHeader() {
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Charset", "UTF-8");
//		headers.put("Content-Type", "application/json");
//		headers.put("X-AVOSCloud-Application-Id", Constant.APP_ID);
//		headers.put("X-AVOSCloud-Application-Key", Constant.APP_KEY);
//		return headers;
//	}
//
//	@Override
//	public Map<String, String> getHeaders() throws AuthFailureError {
//		return mHeaders;
//		// != null ? mHeaders : super.getHeaders();
//	}
//
//	@Override
//	public byte[] getBody() throws AuthFailureError {
//		return mBody != null ? mBody : super.getBody();
//	}
//
//	@Override
//	protected void deliverResponse(String response) {
//		mListener.onResponse(response);
//	}
//
//	public void request() {
//		RequestUtil.addRequest(this, getUrl());
//	}
//
//	@Override
//	protected Response<String> parseNetworkResponse(NetworkResponse response) {
//		try {
//			String charset="utf-8";
//			//如果接口没有设置charset, charset 会导致乱码，SO 都使用UTF-8来解析，
////					HttpHeaderParser.parseCharset(response.headers);
//			String json = new String(response.data, charset);
//			return Response.success(json,
//					NdHttpHeaderParser.parseCacheHeaders(response, cacheTime));
//		} catch (Exception e) {
//			return Response.error(new ParseError(e));
//		}
//	}
//
//	@Override
//	protected VolleyError parseNetworkError(VolleyError volleyError) {
//		Context mContext=NdApplication.getInstance();
////		try {
////			NetworkResponse response = volleyError.networkResponse;
////			if(response.statusCode==403){
////				//ticket失效,重新登录
////				ToastUtil.showToast(mContext, "ticket失效,重新登录！");
////				Message msg=Message.obtain(null, 1000);
////				SystemEvent.fireEvent(msg);
////			}else{
////				// 服务端出错 400 业务异常， 500 服务器异常
////				String json = new String(response.data,
////						HttpHeaderParser.parseCharset(response.headers));
////				Map<String, Object> result = GsonUtil.fromJson2Map(json);
////				if (result != null && result.get("error") != null) {
////					Object message = result.get("error");
////					if (message != null) {
////						ToastUtil.showToast(NdApplication.getInstance(),
////								message.toString());
////					}
////				}			
////			}
////		} catch (Exception e) {
////			//通常由于网络异常导致。
////			ToastUtil.showLongToast(mContext, "请求失败，请检查网络后重试！");
////		}
//		ToastUtil.showLongToast(mContext, "请求失败，请检查网络后重试！");
//		return super.parseNetworkError(volleyError);
//	}
//
//}
