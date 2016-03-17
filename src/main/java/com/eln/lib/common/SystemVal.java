package com.eln.lib.common;

import java.util.ArrayList;
import java.util.List;

import com.eln.lib.util.device.DeviceUtil;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

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
//	public static String channelid = "";
	public static int[] resolutionXY = new int[2];
	public static String private_file_dir;
	public static String extSdcardPath = null;
	public static String channel = "";

	public static float sysDensity = 0;
	public static int sysDensityDpi = 0;
	public static int sysWidth = 0;
	public static int sysHeight = 0;
	// 硬广，媒体汇banner高
	public static int appHeadBannerHeight = 0;
	
	public static Rect rootViewRect = new Rect(0, 0, 0, 0);

	// 业务相关的其它全局变量
	public static String sessionID = "";

	// 刷新时间 用于跨页面刷新,重新设置refreshTime，各个webvew在OnResume时判定时间来决定是否刷新页面
	public static long refreshTime = System.currentTimeMillis();
	// 自动登录sessionId，仅用于获取新闻和上传用户行为等与IMEI相关的操作。与账户登录获得sessionID不同
	public static String autoLogin_sessionId = "";

	// 全局数据
	public static List<String> ipList = new ArrayList<String>(); // 助手wifi状态
	public static String username = ""; // 登录账号
	public static String uin = ""; // 用户标识
	public static boolean isFinish = false; // true表示未关闭程序，仅仅finish掉activity。下次进入不调用applicaton
	public static boolean isInstallAtOnce = false; // 是否强制安装
	public static boolean isEnableXLEngine = true; // 全局配置是否可用使用迅雷下载
	
	//临时变量－用于暂时存储的全局数据
	public static boolean cacheCleaning = false;//是否正在清理缓存中
	
	/**
	 * 外部程序传过来的数据 11 应用-软件，12，应用-游戏，14， 搜索，1100 壁纸首页 ，2200壁纸管理
	 */
	public static String actId="";
	public static String linkUrl = "";
	//第三方搜索URI 
	public static Uri dataUri=null;
	// 91助手resID
	public final static int RESID_91 = 40095721;
	
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
//			resolution = DeviceUtil.getScreenResolution(ctx);
//			resolutionXY = DeviceUtil.getScreenResolutionXY(ctx);
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
