package com.eln.lib.base;

import android.app.Application;
import android.graphics.Bitmap;

import com.eln.lib.common.Configuration;
import com.eln.lib.util.SystemEvent;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public abstract class NdApplication extends Application {

	private static NdApplication mInstance = null;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		// 捕获所有异常
//		AppErrorLogHandler.getInstance(this);
		// 日志初始化
//		MLog.init(this, false);
		// 设置SD卡存放路径
		Configuration.baseCacheDir = getBaseCacheDirName() + "/";
		initImageLoader();
	}

	void initImageLoader() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.threadPoolSize(2)
				.memoryCacheExtraOptions(480, 800)
				// default = device screen dimensions
//				.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
				.diskCacheExtraOptions(480, 800,null)
				.denyCacheImageMultipleSizesInMemory()
				
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75,
				// null)
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13)
				// default
				.discCacheSize(50 * 1024 * 1024).discCacheFileCount(100)
				.defaultDisplayImageOptions(options) // default
				// .writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		SystemEvent.removeListenerAll();
	}

	public static NdApplication getInstance() {
		return mInstance;
	}

	public abstract String getBaseCacheDirName();
}
