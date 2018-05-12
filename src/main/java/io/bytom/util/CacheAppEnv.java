package io.bytom.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 读取properties文件工具�?
 * 
 * @author wt
 * 
 */
public class CacheAppEnv {
	private static ResourceBundle resourceBundle;
	/**
	 * 缓存参数map
	 */
	private static Map<String, String> cacheAppMap = new HashMap<String, String>();

	static {
		resourceBundle = ResourceBundle.getBundle("resources");
	}

	/**
	 * Description:返回当前版本信息 Date:2013-6-7
	 * 
	 * @author Administrator
	 * @return String
	 */
	private static String getValue(String key) {
		return resourceBundle.getString(key);
	}

	/**
	 * 返回参数消息
	 * @param cachekey
	 * @return
	 */
	public static String getCacheValue(String cachekey) {
		if (cacheAppMap.containsKey(cachekey)) {
			return cacheAppMap.get(cachekey);
		} else {
			String value = getValue(cachekey);
			cacheAppMap.put(cachekey, value);
			return value;
		}
	}

	public static Map<String, String> getCacheAppMap() {
		return cacheAppMap;
	}

	public static void setCacheAppMap(Map<String, String> cacheAppMap) {
		CacheAppEnv.cacheAppMap = cacheAppMap;
	}
	
}
