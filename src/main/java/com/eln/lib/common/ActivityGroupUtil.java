package com.eln.lib.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;

/**
 * 功能说明:
 * 
 * 
 * @Author 陈小冬
 * @Date 2012-3-4
 * @Version 1.0
 */
public class ActivityGroupUtil {
	
	/**
	 * 使用反射完成destroyActivity操作，修复destroyActivity的bug
	 * 
	 * @param id
	 * @return
	 */
	public static boolean destroy(ActivityGroup group ,String id) {
		LocalActivityManager manager = group.getLocalActivityManager();
		if (manager != null) {
			manager.destroyActivity(id, true);
			try {
				final Field mActivitiesField = LocalActivityManager.class.getDeclaredField("mActivities");
				if (mActivitiesField != null) {
					mActivitiesField.setAccessible(true);
					@SuppressWarnings("unchecked")
					final Map<String, Object> mActivities = (Map<String, Object>) mActivitiesField.get(manager);
					if (mActivities != null) {
						mActivities.remove(id);
					}
					final Field mActivityArrayField = LocalActivityManager.class.getDeclaredField("mActivityArray");
					if (mActivityArrayField != null) {
						mActivityArrayField.setAccessible(true);
						@SuppressWarnings("unchecked")
						final ArrayList<Object> mActivityArray = (ArrayList<Object>) mActivityArrayField.get(manager);
						if (mActivityArray != null) {
							for (Object record : mActivityArray) {
								final Field idField = record.getClass().getDeclaredField("id");
								if (idField != null) {
									idField.setAccessible(true);
									final String _id = (String) idField.get(record);
									if (id.equals(_id)) {
										mActivityArray.remove(record);
										break;
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
