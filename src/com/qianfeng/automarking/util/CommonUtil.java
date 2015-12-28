package com.qianfeng.automarking.util;

/**
 * 通用工具类 
 * @author HuangGuiZhao
 *
 */
public class CommonUtil {
	private CommonUtil(){}
	
	public static int getRandom(int start,int end){
		return (int)(Math.random()*(end-start+1)+start);
	}
}
