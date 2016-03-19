package com.eln.lib.util;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.eln.lib.base.BaseApplication;

/**
 * 1. 支持在 UI界面 和 Service，线程中显示。 默认Toast是运行在UI线程。
 * 2. 避免重复Toast显示多次
 * @author zhengxiaobin <gybin02@Gmail.com>
 * @since  2016/3/19 14:24
 */
public class ToastUtil {
	private static Toast mToast;

	public static void showToastLong(final CharSequence msg){
		showToast(msg,Toast.LENGTH_LONG);
	}

	public static void showToastShort(final CharSequence msg){
		showToast(msg,Toast.LENGTH_SHORT);
	}

	public static void showToastShort(int resId){
		showToast(BaseApplication.getInstance().getString(resId),Toast.LENGTH_SHORT);
	}

	private static void showToast(final CharSequence msg, final int length){
		if(Looper.myLooper() != Looper.getMainLooper()){
			new Handler(Looper.getMainLooper()).post(new Runnable() {

				@Override
				public void run() {
					makeText(msg,length);
				}
			});
		}else{
			makeText(msg,length);
		}

	}

	private static void makeText(CharSequence msg,int length){
		if(mToast == null){
			mToast = Toast.makeText(BaseApplication.getInstance(), msg, length);
		}else{
			mToast.setText(msg);
		}

		mToast.show();
	}

	/**
	 * 取消显示Toast
	 */
	public static void cancelToast(){
		if(mToast != null){
			mToast.cancel();
		}
	}

}
