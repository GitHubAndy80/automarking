package com.qianfeng.automarking.service;

import java.util.Scanner;

import com.qianfeng.automarking.Main;
import com.qianfeng.automarking.context.Session;
import com.qianfeng.automarking.dao.UserDao;
import com.qianfeng.automarking.entity.User;

public class UserBiz {
	private static Scanner input = new Scanner(System.in);
	private static UserDao userDao = new UserDao();
	/**
	 * 实现登录的功能
	 */
	public static void dealLogin() {
		boolean isExits = false;
		do {
			System.out.println("请输入您的用户名：");
			String username = input.next();
			System.out.println("请输入您的密码：");
			String password = input.next();
			isExits = userDao.checkExits(new User(username, password));
			if (isExits) {
				System.out.println("恭喜，登录成功！");
				Session.put("username", username);
				Main.showMainMenu();
			} else {
				System.out.println("用户名或密码错误");
			}
		} while (!isExits);
	}

	/**
	 * 实现注册的功能
	 */
	public static void dealRegister() {
		boolean isExits = false;
		do {
			System.out.println("请输入您的用户名：");
			String username = input.next();
			System.out.println("请输入您的密码：");
			String password = input.next();
			// 判断用户名的唯一性
			isExits = userDao.checkExits(username);
			// 实现用户的注册
			if (!isExits) {
				boolean isSuccess = userDao.add(new User(username, password));
				if (isSuccess) {
					System.out.println("恭喜，注册成功！赶紧登录试试吧");
					dealLogin();
					break;
				} else {
					System.out.println("注册失败，请联系系统管理员");
				}
			} else {
				System.out.println("抱歉，该用户名已存在，请输入其他用户名");
			}
		} while (isExits);
	}
}
