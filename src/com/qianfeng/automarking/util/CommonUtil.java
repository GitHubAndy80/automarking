package com.qianfeng.automarking.util;

/**
 * ͨ�ù����� 
 * @author HuangGuiZhao
 *
 */
public class CommonUtil {
	private CommonUtil(){}
	
	public static int getRandom(int start,int end){
		return (int)(Math.random()*(end-start+1)+start);
	}
}
