
package com.eln.lib.common.volly;

public class BitmapLruCache{
}
//public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
//	public BitmapLruCache(int maxSize) {
//		super(maxSize);
//	}
//
//	@Override
//	protected int sizeOf(String key, Bitmap bitmap) {
//		return bitmap.getRowBytes() * bitmap.getHeight();
//	}
//
//	@Override
//	public Bitmap getBitmap(String url) {
//		return get(url);
//	}
//
//	@Override
//	public void putBitmap(String url, Bitmap bitmap) {
//		put(url, bitmap);
//	}
//}
