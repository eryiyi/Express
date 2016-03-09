package com.eln.lib.util.network;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;

public class GpsUtil {
	
	/** 
	 * 强制帮用户打开GPS 
	 * @param context 
	 */  
	public static final void openGPS(Context context) {  
		Intent GPSIntent = new Intent();  
		GPSIntent.setClassName("com.android.settings",  
				"com.android.settings.widget.SettingsAppWidgetProvider");  
		GPSIntent.addCategory("android.intent.category.ALTERNATIVE");  
		GPSIntent.setData(Uri.parse("custom:3"));  
		try {  
			PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();  
		} catch (CanceledException e) {  
			e.printStackTrace();  
		}  
	}  

	/** 是否开启GPS定位功能 */
	public static final boolean isOpenGPS(Context context){
		LocationManager alm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);  
		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {  
			return true;  
		}  	
		return false;
	}
	
}
