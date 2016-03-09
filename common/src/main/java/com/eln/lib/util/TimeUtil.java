package com.eln.lib.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 时间工具类
 */
public class TimeUtil {
	/** 年月日时分秒格式 */
	public static final String TIME_FORMAT_HAODAI_YMDHMS = "yyyyMMddHHmmss";
	/** 年-月-日 时:分的格式 */
	public static final String TIME_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
	/** 年-月-日 的格式(年份有4位) */
	public static final String TIME_FORMAT_YMD = "yyyy-MM-dd";
	/** 年-月-日 的格式(年份只有2位) */
	public static final String TIME_FORMAT_yMD = "yy-MM-dd";
	/** 月-日 时:分的格式 */
	public static final String TIME_FORMAT_MDHM = "MM-dd HH:mm";
	/** 时:分 格式 */
	public static final String TIME_FORMAT_HM = "HH:mm";
	/** 年月日 时:分格式 */
	public static final String TIME_FORMAT_YMD_HM = "yyyy年MM月dd日 HH:mm";
	/** 年月日 时:分格式 */
	public static final String TIME_FORMAT_YMD_HM_SS = "yyyy年MM月dd日 HH:mm:ss";
	/** 年-月-日 的格式(年份有4位) */
	public static final String TIME_FORMAT_YMD_DOT = "yyyy.MM.dd";
	
	public static final String TIME_FORMAT_LEANCLOUD="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	/** 1分钟 */
	private static final int ONE_MIUNUTE = 60;
	/** 1小时 */
	private static final int ONE_HOUR = ONE_MIUNUTE * 60;
	/** 24小时 */
	private static final int ONE_DAY = ONE_HOUR * 24;

	public static int getCurrentTimeStamp() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * @describe:根据timeFormat获取当前的时间
	 * @return 当前时间字符串
	 */
	public static String getCurrentTime(String timeFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.CHINA);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return sdf.format(curDate);
	}

	// /**
	// * 将时间戳转为字符串
	// *
	// * @param timeStamp
	// * @param timeFormat
	// * @return
	// */
	// public static String getStrTime(String timeStamp, String timeFormat) {
	// if (TextUtils.isEmpty(timeStamp) || TextUtils.isEmpty(timeFormat)) {
	// return null;
	// }
	// return getStrTime(Long.valueOf(timeStamp), timeFormat);
	// }

	/**
	 * 将时间戳转为字符串
	 * 
	 * @param timeStamp
	 * @param timeFormat
	 * @return
	 */
	public static String getStrTime(long timeStamp, String timeFormat) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.CHINA);
		// 例如：cc_time=1291778220
		re_StrTime = sdf.format(new Date(timeStamp * 1000L));
		return re_StrTime;
	}
	
	public static String getLeancloudTime(String timeStamp){
		//"2016-01-25T08:21:31.164Z"
		//yyyy-MM-ddTHH:mm:ss.SSSZ
		//"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
		return getFormatTime(timeStamp,TIME_FORMAT_LEANCLOUD,TIME_FORMAT_YMD_HM_SS);
	}
	
	public static String getFormatTime(String timeStamp,String fromTimeFormat,String toTimeFormat){
		SimpleDateFormat fromFormat = new SimpleDateFormat(fromTimeFormat);
		fromFormat. setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat toFormat = new SimpleDateFormat(toTimeFormat);
		try {
			return toFormat.format(fromFormat.parse(timeStamp));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 
	 * 2.时间显示规则（适用APP全部功能） a)1小时内显示：09：38 b)超过1小时显示：2小时前 c)最近1天显示：昨天、前天
	 * d)超过最近三天显示：5月27日 获取好贷通用事件规则
	 * 
	 * @return
	 */
	public static final String getHaodaiTimeRule(String timestampStr) {
		if (TextUtils.isEmpty(timestampStr)
				|| !RegexUtil.isNumber(timestampStr))
			return null;
		return getHaodaiTimeRule(Long.parseLong(timestampStr));
	}

	/**
	 * 
	 * 2.时间显示规则（适用APP全部功能） a)1小时内显示：09：38 b)超过1小时显示：2小时前 c)最近1天显示：昨天、前天
	 * d)超过最近三天显示：5月27日 获取好贷通用事件规则
	 * 
	 * @return
	 */
	public static final String getHaodaiTimeRule(long timestamp) {
		return getHaodaiTimeRule(timestamp, TIME_FORMAT_yMD);
	}

	/**
	 * 消息 中心事件显示规则 返回年-月-日 时:分的格式
	 */
	public static final String getJiafenqiMessaTimeRules(long currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT_YMDHM);
		long time = currentTime * 1000;
		Date date = new Date(time);
		return formatter.format(date);
	}

	public static final String getJiafenqiTimeRules(long timestamp) {
		long day = 0;// 天数
		long hour = 0;// 小时
		String time = "";
		String curTime = getCurrentTime(TIME_FORMAT_yMD);

		try {
			long cur_time = System.currentTimeMillis() / 1000;
			long diff = Math.abs(timestamp - cur_time);
			day = diff / (24 * 60 * 60);
			hour = (diff / (60 * 60));

			if (day == 0) {
				time = TimeUtil.getStrTime(timestamp, TIME_FORMAT_yMD);
				if (curTime.equals(time)) {
					time = "今天";
				} else {
					time = hour + "小时前";

				}
			} else if (day > 0) {
				time = day + "天前";
			}
			if (timestamp == 0) {
				time = "从未巡视过";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return time;
	}

	/**
	 * 
	 * 2.时间显示规则（适用APP全部功能） a)1小时内显示：09：38 b)超过1小时显示：2小时前 c)最近1天显示：昨天、前天
	 * d)超过最近三天显示：5月27日 获取好贷通用事件规则
	 * 
	 * @return
	 */
	public static final String getHaodaiTimeRule(long timestamp,
			String timeFormat) {
		long day = 0;// 天数
		long hour = 0;// 小时
		long min = 0;// 分钟
		try {
			long cur_time = System.currentTimeMillis() / 1000;
			long diff = Math.abs(timestamp - cur_time);
			day = diff / (24 * 60 * 60);
			hour = (diff / (60 * 60));
			min = ((diff / 60) - day * 24 * 60 - hour * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String time = "";
		if (day >= 7) {
			time = TimeUtil.getStrTime(timestamp, timeFormat);
		} else if (day >= 1) {
			time = day + "天前";
		} else if (hour > 1) {
			time = hour + "小时前";
		} else if (min > 1) {
			time = min + "分钟前";
		} else {
			time = "刚刚";
		}
		return time;
	}

	/**
	 * 时间显示逻辑：自动计算与当前时间的差值，超过24小时的，显示具体的时间，精确到分钟。 示例： 20秒内：刚刚 1分钟内：xx秒前
	 * 1个小时内：XX分钟前 超过1个小时：XX小时XX分钟前 超过24小时：2015-8-1 11:12 sx
	 * 
	 * @param timestamp
	 *            秒数
	 * @return
	 */
	public static final String getNodeTimeRule(long timestamp) {
		long day = 0;// 天数
		long hour = 0;// 小时
		long min = 0;// 分钟
		long second = 0;// S
		try {
			long cur_time = System.currentTimeMillis() / 1000;
			long diff = Math.abs(timestamp - cur_time);
			day = diff / (24 * 60 * 60);
			hour = (diff / (60 * 60));
			min = ((diff / 60) - day * 24 * 60 - hour * 60);
			second = diff % 60;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String time = "";
		if (day >= 1) {
			time = TimeUtil.getStrTime(timestamp, TIME_FORMAT_YMDHM);
		} else if (hour > 1) {
			time = hour + "小时" + min + "分钟前";
		} else if (min > 1) {
			time = min + "分钟前";
		} else if (second > 20) {
			time = "second" + "秒前";
		} else {
			time = "刚刚";
		}
		return time;
	}

	/**
	 * 返回时间戳所在的小时
	 * 
	 * @param time
	 *            时间戳，单位毫秒
	 * @return
	 */
	public static int getHourOfDay(long time) {
		Date date = new Date(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	/**
	 * 获取数据类型的 时、分、秒数据
	 * 
	 * @param timestamp
	 * @return [0]时、[1]分、[2]秒
	 */
	public static final int[] getHourMinuteSecond(int timestamp) {
		if (timestamp > 0) {
			int hour = timestamp / ONE_HOUR;
			int minute = timestamp % ONE_HOUR / ONE_MIUNUTE;
			int second = timestamp % ONE_HOUR % ONE_MIUNUTE;
			int[] i = new int[3];
			i[0] = hour;
			i[1] = minute;
			i[2] = second;
			return i;
		}
		return null;
	}

	/**
	 * @param timestamp
	 * @return
	 */
	public static final Map<String, Long> getBuyOrSellListTime(long timestamp) {
		if (timestamp > 0) {
			long hour = timestamp / ONE_HOUR;
			long minute = timestamp % ONE_HOUR / ONE_MIUNUTE;
			Map<String, Long> times = new HashMap<String, Long>();
			times.put("hour", hour);
			times.put("minute", minute);
			return times;
		}
		return null;
	}
}