package com.qianfeng.automarking.context;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private Session(){}
	
	private static Map<String,String> map = new HashMap<String,String>();
	
	public static void put(String key,String value){
		map.put(key, value);
	}
	
	public static String get(String key){
		return map.get(key);
	}
	
	public static boolean checkIsExits(String key) {
		String value = Session.get(key);
		if ("".equals(value) || value == null) {
			return false;
		}
		return true;
	}
	
}
