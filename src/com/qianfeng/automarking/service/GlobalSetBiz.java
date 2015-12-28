package com.qianfeng.automarking.service;

import java.util.Scanner;

import com.qianfeng.automarking.context.Session;
import com.qianfeng.automarking.dao.ClassesDao;

/**
 * 全局共享设置，多处地方会公用的设置方法
 * @author HuangGuiZhao
 *
 */
public class GlobalSetBiz {
	private static Scanner input = new Scanner(System.in);
	private static ClassesDao classesDao = new ClassesDao();
	
	private GlobalSetBiz(){}
	
	public static boolean setGlobalClasses(){
		boolean isSuccess = false;
		System.out.println("基础数据管理--->>学员管理--->>设置当前班级");
		System.out.println("-------------------------------");
		boolean isExits;
		do {
			System.out.println("请录入班级名称，该班级将被设置当前班级且全局有效");
			String className = input.next();
			// 判断是否存在
			isExits = classesDao.checkExits(className);
			if (isExits) {
				Session.put("className", className);
				System.out.println("设置成功");
				isSuccess = true;
				break;
			} else {
				System.out.println("温馨提示：您輸入的班級名稱不存在，继续输入请输入‘y’，其他则返回上级菜单！");
				String isContinue = input.next();
				if (!"y".equalsIgnoreCase(isContinue)) {
					break;
				}
			}
		} while (!isExits);
		return isSuccess;
	}
	
}
