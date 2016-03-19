package com.eln.lib.common;

import android.content.Context;
import android.util.DisplayMetrics;

import com.eln.lib.util.device.DeviceUtil;
import com.eln.lib.util.device.ScreenUtil;

public class SystemVal {

	// 程序一启动就初始化的全局变量
	public static String firmwareVersion = "";
	public static int versionCode = 1;
	public static String versionName = "";
	public static String imei = "";
	public static String imsi = "";
	public static String nt = "0";
	public static String abi = "";
	public static String model = "";
	public static String resolution = "";
	public static int sdk = 7;
	public static int[] resolutionXY = new int[2];
	public static String private_file_dir;

	public static float sysDensity = 0;
	public static int sysDensityDpi = 0;
	public static int sysWidth = 0;
	public static int sysHeight = 0;
	
	
	/**
	 * 程序一启动就初始化的全局变量,如imei imsi ...
	 * 
	 * @param ctx
	 */
	public static void init(Context ctx) {
		try{
			DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
			
			sysDensity = metrics.density;
			sysDensityDpi = metrics.densityDpi;
			sysWidth = metrics.widthPixels;
			sysHeight = metrics.heightPixels;
			
			firmwareVersion =  DeviceUtil.getDetailFirmWareVersion();
			if(firmwareVersion== null){			    
			    firmwareVersion = DeviceUtil.getFirmWareVersion(); 
			}
			resolution = ScreenUtil.getScreenResolution(ctx);
			resolutionXY = ScreenUtil.getScreenResolutionXY(ctx);
			versionCode = DeviceUtil.getVersionCode(ctx);
			versionName = DeviceUtil.getVersionName(ctx);
			/*channel = TheUtility.getChannel(ctx);*/
			imei = DeviceUtil.getIMEI(ctx);
			imsi = DeviceUtil.getIMSI(ctx);
			abi = DeviceUtil.getCPUABI();
			sdk = DeviceUtil.getApiLevel();
			nt = getNT(ctx);
			model = DeviceUtil.getMachineName();
			private_file_dir = ctx.getFilesDir().getAbsolutePath();
		}catch(Exception e){}
	}

	/**
	 * 取网络类型
	 * 
	 * @return
	 */
	static String getNT(Context ctx) {
		/**
		 * 0 未知
		 * 
		 * 10 WIFI网络
		 * 
		 * 20 USB网络
		 * 
		 * 31 联通
		 * 
		 * 32 电信
		 * 
		 * 53 移动
		 * 
		 * IMSI是国际移动用户识别码的简称(International Mobile Subscriber Identity)
		 * 
		 * IMSI共有15位，其结构如下： MCC+MNC+MIN MNC:Mobile NetworkCode，移动网络码，共2位
		 * 在中国，移动的代码为电00和02，联通的代码为01，电信的代码为03
		 */
		String nt = "0";
//		if (DeviceUtil.isWifiEnable(ctx)) {
//			nt = "10";
//		} else if (imsi.length() > 5) {
//			String mnc = imsi.substring(3, 5);
//			if (mnc.equals("00") || mnc.equals("02")) {
//				nt = "53";
//			} else if (mnc.equals("01")) {
//				nt = "31";
//			} else if (mnc.equals("03")) {
//				nt = "32";
//			}
//		}
		return nt;
	}
	
}
