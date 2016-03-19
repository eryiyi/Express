/* ========================================================
 * ClassName: MLog
 * Description:日志输出类
 * Copyright (C) 2013 geolo
 * ========================================================
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *===================================================================*
 * Revision History
 *
 * Modification                    Tracking
 * Date             Author          Number       Description of changes
 *____________________________________________________________________
 * 
 * 2013-06-05		geolo		     #00001		    日志加密
 */
package com.eln.lib.util.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.eln.lib.BuildConfig;
import com.eln.lib.util.sdCard.Configuration;
import com.eln.lib.util.sdCard.StorageUtil;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LOG日志工具类；如果需要指定是否需要输入，指定的TAG，知道的输出目录，调用之前需要调用init()方法
 * 
 * @author geolo
 * @email
 * @date 2011-11-16
 */
public class MLog {
	private static String TAG = "MLog.java";
	public static boolean ENCRYPT = false;	
	private static SimpleDateFormat sdf;
	private static String LOG_FILE = "";
	private static String RSP_ERR_LOG_FILE = "";
	private static Context mContext;
	private static MLog instance;

	public static final boolean getDebug(){
		return BuildConfig.DEBUG;
	}

	public static void init(Context context ,boolean encrypt) {
		ENCRYPT = encrypt;	//#00001 +
		sdf = new SimpleDateFormat("MM-dd HH:mm:ss.ms");
		SimpleDateFormat curSDF = new SimpleDateFormat("yyyyMMddHHmmss");
		String curTimeString = curSDF.format(new Date());
		String storedDiretory = StorageUtil.getWritePathIgnoreError(context,Configuration.logRunName+ curTimeString + "_" + ".log");
		String storedDiretory2 = StorageUtil.getWritePathIgnoreError(context,Configuration.logErrorName+ curTimeString + "_" + ".log");

		if(TextUtils.isEmpty(storedDiretory) || TextUtils.isEmpty(storedDiretory2)) {
			return;
		}
		File mFile = new File(storedDiretory);
		File pFile = mFile.getParentFile();
		if (!pFile.exists()) {// 如果文件夹不存在，则先创建文件夹
			pFile.mkdirs();
		}

		LOG_FILE = storedDiretory;
		RSP_ERR_LOG_FILE = storedDiretory2;
		MLog.d(TAG, "日志初始化");
	}
	
	public static void toFile(String msg) {
		if (getDebug()) {
			outMessage(TAG, msg, null);
		}
	}

	public static void i(String msg) {
		if (getDebug()) {
			i(TAG, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (getDebug()) {
			i(tag, msg, null);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (getDebug()) {
			Log.i(tag, msg, tr);
			outMessage(tag, msg, tr);
		}
	}

	public static void v(String msg) {
		if (getDebug()) {
			v(TAG, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (getDebug()) {
			v(tag, msg, null);
		}
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (getDebug()) {
			Log.v(tag, msg, tr);
			outMessage(tag, msg, tr);
		}
	}

	public static void e(String msg) {
		if (getDebug()) {
//			e(TAG, msg);
			e(getInstance().getFunctionName(), msg);
		}
	}

	public static void e(String tag, String msg) {
		if (getDebug()) {
			e(tag, msg, null);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (getDebug()) {
			Log.e(tag, msg, tr);
			outMessage(tag, msg, tr);
		}
	}

	public static void d(String msg) {
		if (getDebug()) {
			d(TAG, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (getDebug()) {
			d(tag, msg, null);
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (getDebug()) {
			Log.d(tag, msg, tr);
			outMessage(tag, msg, tr);
		}
	}

	public static void w(String msg) {
		if (getDebug()) {
			w(TAG, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (getDebug()) {
			w(tag, msg, null);
		}
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (getDebug()) {
			Log.w(tag, msg, tr);
			outMessage(tag, msg, tr);
		}
	}



	private static void outMessage(String tag, String msg, Throwable tr) {
		if (getDebug()) {
			if (TextUtils.isEmpty(LOG_FILE)) {
				return;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(sdf.format(new Date()));
			sb.append(": ");
			sb.append(tag);
			sb.append(": ");
			sb.append(msg);
			sb.append("\n");
			if (tr != null) {
				sb.append(Log.getStackTraceString(tr));
				sb.append("\n");
			}
			outPutToFile(sb.toString(), LOG_FILE);
		}
	}

	/**
	 * 写文件到SDCARD指定的位置
	 * 
	 * @param str
	 * @param filePath
	 * @return
	 */
	private static boolean outPutToFile(String str, String filePath) {
		if (!StorageUtil.isSDcardExist(mContext)) {
			return false;
		}
		if (TextUtils.isEmpty(str)) {
			return false;
		}
		if (TextUtils.isEmpty(filePath)) {
			return false;
		}
		try {
			FileWriter fw = new FileWriter(filePath, true);
			//			fw.write(str);	//#00001 -
			//#00001 + {
			if(!ENCRYPT) {
				fw.write(str);
			} else {
				//				fw.write(encryptionString(str));
			}
			//#00001 +}
			fw.flush();
			fw.close();
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static void markRspErrLog(String msg) {
		//if (getDebug()) { 错误报告就不设置getDebug()了
		if (TextUtils.isEmpty(RSP_ERR_LOG_FILE)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(sdf.format(new Date()));
		sb.append(",");
		sb.append(msg);
		sb.append("\n");
		outPutToFile(sb.toString(), RSP_ERR_LOG_FILE);
		//}
	}
	
	/**
	 * 生成抛出异常的类 和方法
	 * @return
	 */
	private String getFunctionName() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		if (sts == null) {
			return null;
		}
		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}
			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}
			if (st.getClassName().equals(this.getClass().getName())) {
				continue;
			}
			TAG = st.getClassName();
			return "[ " + Thread.currentThread().getName() + ": "
					+ st.getFileName() + ":" + st.getLineNumber() + " "
					+ st.getMethodName() + " ]";
		}
		return null;
	}
	
	private static MLog getInstance() {
		if (instance == null) {
			instance = new MLog();
		}
		return instance;
	}
}
