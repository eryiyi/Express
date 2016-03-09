package com.eln.lib.util.device;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class DeviceUtil {

	private static final String TAG = "DeviceUtil.java";

	
	public static void hideSoftInputFromWindow(Context context){
		((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE))
		.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
	}

	/** 判断是否被安装。
	 * @param otherPkgName 对方应用的包名*/
	public static boolean isInstallAPK(Context context,String otherPkgName){
		PackageInfo packageInfo;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(otherPkgName, 0);
		} catch (NameNotFoundException e) {
			packageInfo = null;
			e.printStackTrace();
		}
		if(packageInfo ==null){
			return false;
		}else{
			return true;
		}
	}
	
	/**安装位置*/
	/**auto*/
	public static final int APP_INSTALL_AUTO = 0; 
	/**internal */
    public static final int APP_INSTALL_DEVICE = 1;
    /**external eg:sdcard*/
    public static final int APP_INSTALL_SDCARD = 2;
    /**default install location */
    public static final String DEFAULT_INSTALL_LOCATION = "default_install_location";
	
    
    /**
	 * 获取当前系统 默认设置安装位置
	 * @param ctx
	 * @return
	 */
	public static int getAppInstallLocation(Context ctx){
		int selectedLocation =Settings.System.getInt(ctx.getContentResolver(), DEFAULT_INSTALL_LOCATION,APP_INSTALL_AUTO);
		switch(selectedLocation){
		case APP_INSTALL_AUTO:
			return APP_INSTALL_AUTO;
		case APP_INSTALL_DEVICE:
			return APP_INSTALL_DEVICE;
		case APP_INSTALL_SDCARD:
			return APP_INSTALL_SDCARD;
		default:
			return APP_INSTALL_AUTO;
		}
	}
	/**
	 * 设置 安装位置
	 * @param ctx
	 * @param id
	 */
	public static void setAppInstallLocation(Context ctx,int id){
        Settings.System.putInt(ctx.getContentResolver(),DEFAULT_INSTALL_LOCATION, id);
	}

	
	/**
	 * 获取CPU的ABI
	 *
	 * @return
	 */
	public static String getCPUABI() {
		String abi = Build.CPU_ABI;
		abi = (abi == null || abi.trim().length() == 0) ? "" : abi;
		// 检视是否有第二类型，1.6没有这个字段
		try {
			String cpuAbi2 = Build.class.getField("CPU_ABI2").get(null).toString();
			cpuAbi2 = (cpuAbi2 == null || cpuAbi2.trim().length() == 0) ? null : cpuAbi2;
			if (cpuAbi2 != null) {
				abi = abi + "," + cpuAbi2;
			}
		} catch (Exception e) {
		}
		return abi;
	}
	
	/**
	 * 取得IMEI号
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getIMEI(Context ctx) {
	TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		if(null == imei)
			return "";
		else
			return imei;
	}
	
	/**
	 * 取得IMSI号
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getIMSI(Context ctx) {
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
		String imsi = tm.getSubscriberId();
		if(null == imsi)
			return "";
		else
			return imsi;
	}
	
	
	/**
	 * 获取机器名称	如 milestone
	 * @return
	 */
	public static String getMachineName(){
		return android.os.Build.MODEL; 
	}
	
	
	/**
	 * 获取数字型API_LEVEL 如：4、6、7
	 * @return
	 */
	public static int getApiLevel(){
		int apiLevel = 7;
		try{
			apiLevel = Integer.parseInt(android.os.Build.VERSION.SDK);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return apiLevel;
	}

	
	/**
	 * 获取字符串型的固件版本，如1.5、1.6、2.1
	 * @return
	 */
	public static String getFirmWareVersion(){
		final String version_3 =  "1.5";
		final String version_4 =  "1.6";
		final String version_5 =  "2.0";
		final String version_6 =  "2.0.1";
		final String version_7 =  "2.1";
		final String version_8 =  "2.2";
		final String version_9 =  "2.3";
		final String version_10 =  "2.3.3";
		final String version_11 =  "3.0";
		final String version_12 =  "3.1";
		final String version_13 =  "3.2";
		final String version_14 =  "4.0";
		final String version_15 = "4.0.3";
		final String version_16 = "4.1";
		final String version_17 = "4.2";
		final String version_18 = "4.3";
		final String version_19 = "4.4";
		String versionName = "";
		try{
			// android.os.Build.VERSION.SDK_INT  Since: API Level 4
			// int version = android.os.Build.VERSION.SDK_INT;
			int version = Integer.parseInt(android.os.Build.VERSION.SDK);
			switch(version){
			case 3:
				versionName = version_3;
				break;
			case 4:
				versionName = version_4;
				break;
			case 5:
				versionName = version_5;
				break;
			case 6:
				versionName = version_6;
				break;
			case 7:
				versionName = version_7;
				break;
			case 8:
				versionName = version_8;
				break;
			case 9:
				versionName = version_9;
				break;
			case 10:;
				versionName = version_10;
				break;
			case 11:
				versionName = version_11;
				break;
			case 12:
				versionName = version_12;
				break;
			case 13:
				versionName = version_13;
				break;
			case 14:
				versionName = version_14;
				break;
			case 15:
				versionName = version_15;
				break;								
			case 16:
				versionName = version_16;
				break;
			case 17:
				versionName = version_17;
				break;
			case 18:
				versionName = version_18;
				break;
			case 19:
				versionName = version_19;
				break;
			default:
				versionName = version_16;
			}
		}
		catch(Exception e){
			versionName = version_16;
		}
		return versionName;
	}

	/**
	 * 获取详细的固件版本号
	 * @author Administrator 
	 * @return
	 */
	public static String getDetailFirmWareVersion(){
	    
	    String versionName = null;
	    String regEx ="([0-9]\\.[0-9])|([0-9]\\.[0-9]\\.[0-9])";
	    try{
	        versionName = android.os.Build.VERSION.RELEASE;
	        Pattern pattern = Pattern.compile(regEx);
	        Matcher matcher = pattern.matcher(versionName);
	        if(!matcher.matches()){
	           versionName = null;
	        }
	    }catch(Exception e){	        
	        e.printStackTrace();
	        versionName = null;
	    }
	    return versionName;
	}
	
	/**
	 * 获取软件版本名称
	 * @return
	 */
	public static String getVersionName(Context ctx){
		String versionName = "";
		try {
			PackageInfo packageinfo = ctx.getPackageManager().getPackageInfo(
					ctx.getPackageName(), PackageManager.GET_INSTRUMENTATION);
			versionName = packageinfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}
	/**
	 * 获取软件版本号 code
	 * @return
	 */
	public static int getVersionCode(Context ctx){
		int versionCode = 0;
		try {
			PackageInfo packageinfo = ctx.getPackageManager().getPackageInfo(
					ctx.getPackageName(), PackageManager.GET_INSTRUMENTATION);
			versionCode = packageinfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
     * 获取mac地址
     * */
    public static String getLocalMacAddress(Context context) {
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }
    
	/**
	 * sim卡是否存在
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isSimExist(Context ctx) {
		TelephonyManager manager = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
		if (manager.getSimState() == TelephonyManager.SIM_STATE_ABSENT)
			return false;
		else
			return true;
	}
	
	/**
	 * sd卡是否存在
	 *
	 * @return
	 */
	public static boolean isSdcardExist() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 查询系统广播
	 *
	 * @param ctx
	 * @param packageName
	 * @return
	 */
	public static boolean queryBroadcastReceiver(Context ctx, String actionName) {
		PackageManager pm = ctx.getPackageManager();
		try {
			Intent intent = new Intent(actionName);
			List<ResolveInfo> apps = pm.queryBroadcastReceivers(intent, 0);
			if(apps.isEmpty())
				return false;
			else
				return true;
		} catch (Exception e) {
			Log.d(TAG, "queryBroadcastReceivers: " + e.toString());
			return false;
		}
	}

	
	/**
	 * 
	* @Title: isServiceRunning 
	* @Description:判断服务是否运行
	* @param ctx
	* @param className
	* @return     
	* @throws
	 */
	public static boolean isServiceRunning(Context ctx,String className){
	        ActivityManager activityManager = (ActivityManager)ctx.getSystemService("activity");
	        List<RunningServiceInfo> serviceList = activityManager.getRunningServices(30);
	        if(serviceList == null)
	            return false;
	        for(int i = 0; i < serviceList.size(); i++){
	            if(((android.app.ActivityManager.RunningServiceInfo)serviceList.get(i)).service.getClassName().equals(className))
	                return true;
	        }
	        return false;
    }
	/**
	 * Check to see if a recognition activity is present
	 * @param ctx
	 * @return
	 */
	public static boolean isSupportRecognizer(Context ctx){
		  // Check to see if a recognition activity is present
        PackageManager pm = ctx.getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() != 0) {
            return true;
        } else {
            return false;
        }
	}
	
    /**
     * 	获取内部存储器可用大小
     */
    public static long getAvailableInternalMemorySize() {  
    	long availableExternalMemorySize = 0;  
        File path = Environment.getDataDirectory();  
        StatFs stat = new StatFs(path.getPath());  
        long blockSize = stat.getBlockSize();  
        long availableBlocks = stat.getAvailableBlocks();  
        availableExternalMemorySize = availableBlocks * blockSize;  
        return availableExternalMemorySize;
    }  
  
    /**
     * 获取外部存储器可用大小
     */
    public static long getAvailableExternalMemorySize() {  
        long availableExternalMemorySize = 0;  
        if (Environment.getExternalStorageState().equals(  
                Environment.MEDIA_MOUNTED)) {  
            File path = Environment.getExternalStorageDirectory();  
            StatFs stat = new StatFs(path.getPath());  
            long blockSize = stat.getBlockSize();  
            long availableBlocks = stat.getAvailableBlocks();  
            availableExternalMemorySize = availableBlocks * blockSize;  
        }else if (Environment.getExternalStorageState().equals(  
                Environment.MEDIA_REMOVED)) {  
            availableExternalMemorySize = -1;  
        }  
        return availableExternalMemorySize;
    }  
  
    /**
     * 获取外部存储器总大小
     */
    public static long getTotalExternalMemorySize() {  
        long availableExternalMemorySize = 0;  
        if (Environment.getExternalStorageState().equals(  
                Environment.MEDIA_MOUNTED)) {  
            File path = Environment.getExternalStorageDirectory();  
            StatFs stat = new StatFs(path.getPath());  
            long blockSize = stat.getBlockSize();  
            long Blocks = stat.getBlockCount();  
            availableExternalMemorySize = Blocks * blockSize;  
        }else if (Environment.getExternalStorageState().equals(  
                Environment.MEDIA_REMOVED)) {  
            availableExternalMemorySize = -1;  
        }  
        return availableExternalMemorySize;
    }  

}
