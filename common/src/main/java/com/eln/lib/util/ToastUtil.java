package com.eln.lib.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtil {
	public static Toast makeToast(Context mContext, int resId) {
		return Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
	}

	public static Toast makeToast(Context mContext, String text) {
		return Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
	}

	public static void showToast(final Context mContext, final int resId) {
		if(Looper.myLooper() != Looper.getMainLooper()){
			new Handler(Looper.getMainLooper()).post(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();			
				}
			});
		}else{
			Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();						
		}
	}

	public static void showToast(final Context mContext,final  String text) {
		if(Looper.myLooper() != Looper.getMainLooper()){
			new Handler(Looper.getMainLooper()).post(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
				}
			});
		}else{
			Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
		}
	}

	public static void showLongToast(final Context mContext,final  int resId) {
		if(Looper.myLooper() != Looper.getMainLooper()){
			new Handler(Looper.getMainLooper()).post(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(mContext, resId, Toast.LENGTH_LONG).show();
				}
			});
		}else{
			Toast.makeText(mContext, resId, Toast.LENGTH_LONG).show();
		}
	}

	public static void showLongToast(final Context mContext, final String text) {
		if(Looper.myLooper() != Looper.getMainLooper()){
			new Handler(Looper.getMainLooper()).post(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
				}
			});
		}else{
			Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
		}
	}
}
