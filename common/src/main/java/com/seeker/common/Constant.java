package com.seeker.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import android.os.Environment;

import com.eln.lib.util.log.MLog;

/**
 * 常量存放区
 */
public class Constant {
	
	//正式服务器
//	public static String APP_SERVER_URL="ksfapp1.91yong.com";
//	public static String MASTER_SERVER_URL="58.215.183.201";
	
	//外网测试环境  
	public static  String URL_PRE_ACCOUNT="http://192.168.70.70:8092/elearning-account-local/";
	public static  String URL_PRE_TRAIN="http://192.168.70.70:8086/elearning-training-local/";
	public static  String URL_PRE_COLLEAGE="http://192.168.70.70:8088/elearning-colleaguecircle-local/";
	
	/**厦门测试服务器地址**/
//	public static  String URL_PRE_ACCOUNT="http://192.168.70.74:8092/elearning-account-dev/";
//	public static  String URL_PRE_TRAIN="http://192.168.70.74:8086/elearning-training-dev/";
//	public static  String URL_PRE_COLLEAGE="http://192.168.70.74:8088/elearning-colleaguecircle-dev/";

	
	public static String PAGE_COUNT="limit";
	public static  int PAGE_SIZE=20;
	public static String LAST_ITEM_ID="skip";
	public static final  String URL_HTML="file:///android_asset/html/";

	//AVOS KEY 
	public final static String APP_ID="wjm04batbnzn0rigyo6zzwcz5rzntlyvgv3l6y7bsy2bncy9";
	public final static String APP_KEY="3d437nbjhrwd6ys610km914mowl5puz2zit2f7t7815rhpcv";
	public final static  String  URL_AVOS="https://cn.avoscloud.com/1.1/classes/";

	/**************************************** 时间常量 ******************************************/

	/** 一秒 */
	public static long SECOND = 1000L;
	/** 一分 */
	public static long MINUTE = 60 * SECOND;
	/** 一小时 */
	public static long HOUR = 60 * MINUTE;
	/** 一天 */
	public static long DAY = 24 * HOUR;
	/** 一周 */
	public static long WEEK = DAY * 7;
	/** 半天 */
	public static long HALF_DAY = 12 * HOUR;
	/** 一月 */
	public static long MONTH = 30 * DAY;
	/** 一年 */
	public static long YEAR = 12 * MONTH;
	
	/**************************************** 加载配置文件 ****************************************/
//	static{
//		try {
//			String path = Environment.getExternalStorageDirectory()
//					+ File.separator + "ndhost.properties";
//			if ((new File(path).exists())) {
//				InputStream inputStream = new FileInputStream(path);
//				Properties p = new Properties();
//				p.load(inputStream);
//				Constant.URL_PRE_ACCOUNT = p.getProperty("URL_PRE_ACCOUNT",
//						Constant.URL_PRE_ACCOUNT);
//				Constant.URL_PRE_TRAIN = p.getProperty("URL_PRE_TRAIN",
//						Constant.URL_PRE_TRAIN);
//				Constant.URL_PRE_COLLEAGE = p.getProperty("URL_PRE_COLLEAGE",
//						Constant.URL_PRE_COLLEAGE);
//				
//				inputStream.close();
//				MLog.e("Account Server: "+Constant.URL_PRE_ACCOUNT);
//				MLog.e("Train Server: "+Constant.URL_PRE_TRAIN);
//				MLog.e("College Server: "+Constant.URL_PRE_COLLEAGE);
//			}else{
//				MLog.e("Host File Not Found ! ");
//			}
//		} catch (Exception e) {
//			MLog.e("Fail in initProperties");
//		}
//	}
	
}