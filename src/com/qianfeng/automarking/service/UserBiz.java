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
	 * ʵ�ֵ�¼�Ĺ���
	 */
	public static void dealLogin() {
		boolean isExits = false;
		do {
			System.out.println("�����������û�����");
			String username = input.next();
			System.out.println("�������������룺");
			String password = input.next();
			isExits = userDao.checkExits(new User(username, password));
			if (isExits) {
				System.out.println("��ϲ����¼�ɹ���");
				Session.put("username", username);
				Main.showMainMenu();
			} else {
				System.out.println("�û������������");
			}
		} while (!isExits);
	}

	/**
	 * ʵ��ע��Ĺ���
	 */
	public static void dealRegister() {
		boolean isExits = false;
		do {
			System.out.println("�����������û�����");
			String username = input.next();
			System.out.println("�������������룺");
			String password = input.next();
			// �ж��û�����Ψһ��
			isExits = userDao.checkExits(username);
			// ʵ���û���ע��
			if (!isExits) {
				boolean isSuccess = userDao.add(new User(username, password));
				if (isSuccess) {
					System.out.println("��ϲ��ע��ɹ����Ͻ���¼���԰�");
					dealLogin();
					break;
				} else {
					System.out.println("ע��ʧ�ܣ�����ϵϵͳ����Ա");
				}
			} else {
				System.out.println("��Ǹ�����û����Ѵ��ڣ������������û���");
			}
		} while (isExits);
	}
}
