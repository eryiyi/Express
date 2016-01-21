package com.seeker.common.volly;

/**
 * 自定义的HeaderParser,可以强制缓存，忽略服务器的设置
 */
public class NdHttpHeaderParser{
//extends HttpHeaderParser {
//    /**
//     * Extracts a {@link com.android.volley.Cache.Entry} from a {@link com.android.volley.NetworkResponse}.
//     *
//     * @param response The network response to parse headers from
//     * @param cacheTime 缓存时间，如果设置了这个值，不管服务器返回是否可以缓存，都会缓存,一天为1000*60*60*24
//     * @return a cache entry for the given response, or null if the response is not cacheable.
//     */
//    public static Cache.Entry parseCacheHeaders(NetworkResponse response,long cacheTime) {
//        Cache.Entry entry=parseCacheHeaders(response);
//        if(entry==null) return null;
//        long now = System.currentTimeMillis();
//        long softExpire=now+cacheTime;
//        entry.softTtl = softExpire;
//        entry.ttl = entry.softTtl;
//        return entry;
//    }
//    
//	/** 一秒 */
//	public static long SECOND = 1000L;
//	/** 一分 */
//	public static long MINUTE = 60 * SECOND;
//	/** 一小时 */
//	public static long HOUR = 60 * MINUTE;
//	/** 一天 */
//	public static long DAY = 24 * HOUR;
//	/** 一周 */
//	public static long WEEK = DAY * 7;
//	/** 半天 */
//	public static long HALF_DAY = 12 * HOUR;
//	/** 一月 */
//	public static long MONTH = 30 * DAY;
//	/** 一年 */
//	public static long YEAR = 12 * MONTH;
}
