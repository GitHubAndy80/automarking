package com.qianfeng.automarking.util;
/**
 * 多种数据格式校验工具类
 * @author HuangGuiZhao
 *
 */
public class CheckUtil {
	public static boolean checkPhone(String phone) {
		String regex = "1\\d{10}";
		return phone.matches(regex);
	}
	
	public static boolean checkEmail(String email) {
		String regex = "\\w+@\\w+(\\.\\w+)+";
		return email.matches(regex);
	}
	
	public static boolean checkDir(String dir){
		String regex = "[CDEFGHIJKLMcdefghijklm]:?\\\\\\\\\\w+\\.txt";
		return dir.matches(regex);
	}
}
