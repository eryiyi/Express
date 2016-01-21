package com.seeker.common.volly;

/**
 * 方便使用Volley类，使用方法
 * 1. 在Application里面先初始化init()
 * 2. 调用Volley方法
 * @author 郑晓彬
 */
public class RequestUtil {
//	private static RequestQueue mRequestQueue;
//	private static ImageLoader mImageLoader;
//
//	private RequestUtil() {
//		// no instances
//	}
//
//	public static void init(Context context) {
//		mRequestQueue = Volley.newRequestQueue(context);
//
//		int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
//				.getMemoryClass();
//		// Use 1/8th of the available memory for this memory cache.
//		int cacheSize = 1024 * 1024 * memClass / 8;
//		mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));
//	}
//
//	public static RequestQueue getRequestQueue() {
//		if (mRequestQueue != null) {
//			return mRequestQueue;
//		} else {
//			throw new IllegalStateException("RequestQueue not initialized");
//		}
//	}
//	
//	public static void addRequest(Request<?> request, Object tag) {
//        if (tag != null) {
//            request.setTag(tag);
//        }
//        mRequestQueue.add(request);
//    }
//	
//	public static void cancelAll(Object tag) {
//        mRequestQueue.cancelAll(tag);
//    }
//	
//	/**
//	 * Returns instance of ImageLoader initialized with {@see FakeImageCache}
//	 * which effectively means that no memory caching is used. This is useful
//	 * for images that you know that will be show only once.
//	 * 
//	 * @return
//	 */
//	public static ImageLoader getImageLoader() {
//		if (mImageLoader != null) {
//			return mImageLoader;
//		} else {
//			throw new IllegalStateException("ImageLoader not initialized");
//		}
//	}
}
