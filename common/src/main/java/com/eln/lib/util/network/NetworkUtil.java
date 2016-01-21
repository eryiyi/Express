package com.eln.lib.util.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetworkUtil {
	
	/** 判断是否有网络连接  */
	public static boolean isNetworkConnected(Context context) { 
		if (context != null) { 
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
					.getSystemService(Context.CONNECTIVITY_SERVICE); 
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
			if (mNetworkInfo != null) { 
				return mNetworkInfo.isAvailable()&&mNetworkInfo.isConnected(); 
			} 
		} 
		return false; 
	} 

	/** 判断WIFI网络是否可用  */
	public static boolean isWifiConnected(Context context) { 
		if (context != null) { 
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
					.getSystemService(Context.CONNECTIVITY_SERVICE); 
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager 
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
			if (mWiFiNetworkInfo != null) { 
				return mWiFiNetworkInfo.isAvailable()&&mWiFiNetworkInfo.isConnected(); 
			} 
		} 
		return false; 
	} 

	/** 判断MOBILE网络是否可用  */
	public static boolean isMobileConnected(Context context) { 
		if (context != null) { 
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
					.getSystemService(Context.CONNECTIVITY_SERVICE); 
			NetworkInfo mMobileNetworkInfo = mConnectivityManager 
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE); 
			if (mMobileNetworkInfo != null) { 
				return mMobileNetworkInfo.isAvailable()&&mMobileNetworkInfo.isConnectedOrConnecting(); 
			} 
		} 
		return false; 
	} 

	/**
	 * wifi是否启动
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isWifiEnable(Context ctx) {
		try{
			if (ctx == null)
				return false;
			ConnectivityManager tele = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (tele != null  && (tele.getActiveNetworkInfo() == null || !tele.getActiveNetworkInfo().isAvailable())) {
				return false;
			}
			int type = tele.getActiveNetworkInfo().getType();
			return type == ConnectivityManager.TYPE_WIFI;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	
	/**
	 * 返回网络连接方式
	 *
	 * @param ctx
	 * @return	0为wifi连接;1为gprs连接
	 */
	public static int getNetworkState(Context ctx) {
		if (isWifiEnable(ctx)) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/** 判断是否为wap方式上网
	 * @param context
	 * @return
	 */
	public static boolean isWap(Context context) {
		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			String type = cm.getActiveNetworkInfo().getTypeName().toLowerCase();
			if (type != null && (type.contains("wap")
					|| type.equals("#777"))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/** 获取wifi的ssid码 */
	public static String getWifiSSID(Context ctx) {
		String ssid = "";
		try {
			if (ctx == null)
				return ssid;
			WifiManager wifiManager = (WifiManager) ctx
					.getSystemService(ctx.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			ssid = wifiInfo.getSSID();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ssid;
	}
	
}
