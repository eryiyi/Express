package com.eln.lib.base;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import com.eln.lib.util.sdCard.Configuration;
import com.eln.lib.common.SystemVal;
import com.eln.lib.util.SystemEvent;
import com.eln.lib.util.device.ScreenUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class BaseApplication extends Application {

	private static BaseApplication mInstance = null;

	private static boolean isPad = false;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		// 捕获所有异常
//		AppErrorLogHandler.getInstance(this);
		// 日志初始化
//		MLog.initScreen(this, false);
		// 设置SD卡存放路径
		Configuration.baseCacheDir = getCacheDirName() + "/";
		initImageLoader();
		
		initScreen();
		//Volley 请求初始化
//        RequestUtil.initScreen(this);
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

	public static BaseApplication getInstance() {
		return mInstance;
	}

	/**
	 * 子类重写SD卡存储路径
	 * @return
     */
	public String getCacheDirName(){
		return "seeker";
	}

	private void initScreen() {
		ScreenUtil.init(this);// 屏幕工具初始化
		SystemVal.init(this);

		DisplayMetrics dm = new DisplayMetrics();
		dm = this.getResources().getDisplayMetrics();
		double diagonal = Math.hypot(dm.widthPixels / dm.xdpi,
				dm.heightPixels / dm.ydpi);
		isPad = diagonal >= 7;
	}

	public boolean isPad() {
		return isPad;
	}
}
