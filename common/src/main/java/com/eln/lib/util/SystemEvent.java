package com.eln.lib.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Message;

import com.eln.lib.util.log.MLog;

/**
 * 自定义的全局事件,观察者模式
 * @author 郑晓彬
 */
public class SystemEvent {

	private static final String TAG = "SystemEvent";

	private static Map<Integer, ArrayList<WeakReference<IEventListener>>> mEventMap = new HashMap<Integer, ArrayList<WeakReference<IEventListener>>>();
	private static Map<Integer, ArrayList<WeakReference<IEventListener>>> mTmpEventMap = new HashMap<Integer, ArrayList<WeakReference<IEventListener>>>();

	/**
	 * 自定义的事件监听器
	 */
	public interface IEventListener {
		void onEvent(Message msg);
	}

	/**
	 * 暂存监听器
	 * 
	 * @param listener
	 */
	public static void storeListener() {
		mTmpEventMap.putAll(mEventMap);
		mEventMap.clear();
	}

	/**
	 * 还原监听器
	 * 
	 * @param listener
	 */
	public static void restoreListener() {
		mEventMap.putAll(mTmpEventMap);
		mTmpEventMap.clear();
	}
	
	public static void removeListenerAll(){
		mEventMap.clear();
	}
	/**
	 * 加入监听器
	 * 
	 * @param listener
	 */
	public static void addListener(int eventType, IEventListener listener) {
		ArrayList<WeakReference<IEventListener>> list = mEventMap
				.get(eventType);
		if (null == list)
			list = new ArrayList<WeakReference<IEventListener>>();
		WeakReference<IEventListener> wrf = new WeakReference<IEventListener>(
				listener);
		// 如果已经存在同一个监听者，就不添加
		if (list.contains(wrf)) {
			return;
		}
		list.add(wrf);
		mEventMap.put(eventType, list);

	}

	/**
	 * 移除监听器
	 * 
	 * @param listener
	 */
	public static void removeListener(int eventType, IEventListener listener) {
		ArrayList<WeakReference<IEventListener>> list = mEventMap
				.get(eventType);
		if (null == list)
			return;
		for (int i = 0; i < list.size(); i++) {
			IEventListener l = list.get(i).get();
			if (l == listener) {
				list.remove(i);
				break;
			}
		}
	}

	/**
	 * 移除当前Event的所有Listener
	 * 
	 * @param eventType
	 */
	public static void removeListener(int eventType) {
		MLog.d(TAG, "removeListener = " + eventType);
		mEventMap.remove(eventType);
	}

	/**
	 * 激活监听器
	 * 
	 * @param eventType
	 * @param data
	 */
	public static void fireEvent(Message msg) {
//		long time3 = System.currentTimeMillis();
		ArrayList<WeakReference<IEventListener>> list = mEventMap
				.get(msg.what);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				IEventListener listener = list.get(i).get();
				if (listener != null) {
					// long time1 = System.currentTimeMillis();
					listener.onEvent(msg);
				}
			}
		}

	}

	// /**
	// * 响应监听器
	// * @param eventType
	// */
	// public static void fireEvent(int eventType) {
	// fireEvent(eventType);
	// }

}
