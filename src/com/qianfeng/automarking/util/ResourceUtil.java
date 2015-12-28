package com.qianfeng.automarking.util;

/**
 * �YԴ�������
 * 
 * @author HuangGuiZhao
 *
 */
public class ResourceUtil {
	private ResourceUtil() {
	}

	/**
	 * �P�]�����YԴ
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
