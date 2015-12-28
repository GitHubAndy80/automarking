package com.qianfeng.automarking.util;

/**
 * 資源管理工具類
 * 
 * @author HuangGuiZhao
 *
 */
public class ResourceUtil {
	private ResourceUtil() {
	}

	/**
	 * 關閉多個資源
	 * @param cs
	 */
	public static void close(AutoCloseable... cs) {
		for (AutoCloseable c : cs) {
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
