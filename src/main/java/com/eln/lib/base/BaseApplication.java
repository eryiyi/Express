package com.eln.lib.base;

import android.util.DisplayMetrics;

import com.eln.lib.util.device.ScreenUtil;
import com.eln.lib.common.SystemVal;

/**
 * 应用全局入口 ,处理应用初始化工作
 */
public class BaseApplication extends NdApplication{

	private static boolean isPad = false;

	@Override
	public void onCreate() {
		super.onCreate();
		init();
		//Volley 请求初始化
//        RequestUtil.init(this);
	}

	@Override
	public String getBaseCacheDirName() {
		return "wiki";
	}
	
	private void init() {
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
