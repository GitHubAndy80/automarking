package com.qianfeng.automarking.service;

import java.util.Scanner;

import com.qianfeng.automarking.context.Session;
import com.qianfeng.automarking.dao.ClassesDao;

/**
 * ȫ�ֹ������ã��ദ�ط��ṫ�õ����÷���
 * @author HuangGuiZhao
 *
 */
public class GlobalSetBiz {
	private static Scanner input = new Scanner(System.in);
	private static ClassesDao classesDao = new ClassesDao();
	
	private GlobalSetBiz(){}
	
	public static boolean setGlobalClasses(){
		boolean isSuccess = false;
		System.out.println("�������ݹ���--->>ѧԱ����--->>���õ�ǰ�༶");
		System.out.println("-------------------------------");
		boolean isExits;
		do {
			System.out.println("��¼��༶���ƣ��ð༶�������õ�ǰ�༶��ȫ����Ч");
			String className = input.next();
			// �ж��Ƿ����
			isExits = classesDao.checkExits(className);
			if (isExits) {
				Session.put("className", className);
				System.out.println("���óɹ�");
				isSuccess = true;
				break;
			} else {
				System.out.println("��ܰ��ʾ����ݔ��İ༉���Q�����ڣ��������������롮y���������򷵻��ϼ��˵���");
				String isContinue = input.next();
				if (!"y".equalsIgnoreCase(isContinue)) {
					break;
				}
			}
		} while (!isExits);
		return isSuccess;
	}
	
}
