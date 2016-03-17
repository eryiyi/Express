package com.eln.lib.common.volly;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Gson 解析帮助类
 * 
 * @author ZhengXiaoBin CreateTime: 2014年8月6日
 */
 
public class GsonUtil {


	public static <T> T fromJson(String json, Class<T> clazz) {
		Gson gson = new Gson();
		T t = gson.fromJson(json, clazz);
		return t;
	}

	public static <T> Map<String, T> fromJson2Map(String json) {
		Gson gson = new Gson();
		Map<String, T> map = gson.fromJson(json,
				new TypeToken<Map<String, T>>() {
				}.getType());
		return map;
	}
	
	public static  Map<String,JsonElement> fromJson2MapJson(String json) {
		Gson gson = new Gson();
		Map<String, JsonElement> map = gson.fromJson(json,
				new TypeToken<Map<String, JsonElement>>() {
				}.getType());
		return map;
	}

	public static <T> ArrayList<T> fromJson2List(String json, Class<T> clazz) {
		Gson gson = new Gson();
		ArrayList<JsonObject> list = null;
		list = gson.fromJson(json, new TypeToken<List<JsonObject>>() {
		}.getType());
		ArrayList<T> resultList = new ArrayList<T>();
		for (JsonObject t : list) {
			resultList.add(new Gson().fromJson(t, clazz));
		}
		return resultList;
	}
	
	public static <T> ArrayList<T> fromJson2List(JsonElement json, Class<T> clazz) {
		Gson gson = new Gson();
		ArrayList<JsonObject> list = null;
		list = gson.fromJson(json, new TypeToken<List<JsonObject>>() {
		}.getType());
		ArrayList<T> resultList = new ArrayList<T>();
		for (JsonObject t : list) {
			resultList.add(new Gson().fromJson(t, clazz));
		}
		return resultList;
	}

}
