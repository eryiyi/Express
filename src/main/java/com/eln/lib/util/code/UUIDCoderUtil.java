package com.eln.lib.util.code;

import java.util.UUID;

/**特殊编码工具类，如32位UUID，36位UUID等*/
public class UUIDCoderUtil {

	/**
	 * 获取32位uuid
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String get36UUID(){
		return UUID.randomUUID().toString();
	}
}
