package com.qianfeng.automarking.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.qianfeng.automarking.Main;
import com.qianfeng.automarking.dao.ClassesDao;
import com.qianfeng.automarking.entity.Classes;

public class ClassesBiz {
	private static Scanner input = new Scanner(System.in);
	private static ClassesDao classesDao = new ClassesDao();

	public static void showClassesMenu() {
		boolean isRight = false;
		int choice = 0;
		do {
			System.out.println("�������ݹ���--->>�༶����");
			System.out.println("------------------");
			System.out.println("��ѡ������Ҫ�Ĳ�����");
			System.out.println("1,�����༶");
			System.out.println("2,�޸İ༶");
			System.out.println("3,ɾ���༶");
			System.out.println("4,�鿴�༶�б�");
			System.out.println("5,������һ��");
			System.out.println("------------------");
			try {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 5) {
					isRight = true;
				} else {
					System.out.println("������1-5֮�������");
				}
			} catch (InputMismatchException e) {
				System.out.println("������1-5֮�������");
				input.next();
			}
		} while (!isRight);
		switch (choice) {
		case 1:
			addClasses();
			break;
		case 2:
			modifyClasses();
			break;
		case 3:
			delClasses();
			break;
		case 4:
			showClassesList();
			break;
		case 5:
			Main.showMainMenu();
			break;
		default:
			break;
		}
	}

	public static void showClassesList() {
		System.out.println("�������ݹ���--->>�༶����--->>��ʾ�༶�б�");
		System.out.println("-------------------------------");
		List<Classes> list = classesDao.getList();
		if (list.size() == 0) {
			System.out.println("��ܰ��ʾ������ǰ��δ����κΰ༶��Ϣ,��ȥ��Ӱ�");
		} else {
			System.out.println("�༶����\t\t�༶���\t\t");
			for (Classes classes : list) {
				System.out.println(classes.getClassName() + "\t\t" + classes.getIntroduce() + "\t\t");
			}
		}
		showClassesMenu();
	}

	public static void delClasses() {
		boolean isExits = false;
		System.out.println("�������ݹ���--->>�༶����--->>ɾ���༶");
		System.out.println("-----------------------------");
		do {
			System.out.println("������༶���ƣ�");
			String className = input.next();
			// �ж��Ƿ����
			isExits = classesDao.checkExits(className);
			if (isExits) {
				boolean isSuccess = classesDao.del(className);
				if (isSuccess) {
					System.out.println("��ϲ��ɾ���ɹ���");
					break;
				} else {
					System.out.println("ɾ��ʧ�ܣ�����ϵϵͳ����Ա");
				}
			} else {
				System.out.println("��ܰ��ʾ����ݔ��İ༉���Q�����ڣ��������������롮y���������򷵻��ϼ��˵���");
				String isContinue = input.next();
				if (!"y".equalsIgnoreCase(isContinue)) {
					break;
				}
			}
		} while (!isExits);
		showClassesMenu();
	}

	public static void modifyClasses() {
		boolean isExits = false;
		System.out.println("�������ݹ���--->>�༶����--->>�޸İ༶");
		System.out.println("-----------------------------");
		do {
			System.out.println("������Ҫ�޸ĵİ༶���ƣ�");
			String className = input.next();
			// �ж��Ƿ����
			isExits = classesDao.checkExits(className);
			if (isExits) {
				System.out.println("�������µİ༶��飺");
				String introduce = input.next();
				boolean isSuccess = classesDao.update(new Classes(className, introduce));
				if (isSuccess) {
					System.out.println("��ϲ���޸ĳɹ���");
					break;
				} else {
					System.out.println("�޸�ʧ�ܣ�����ϵϵͳ����Ա");
				}
			} else {
				System.out.println("��ܰ��ʾ����ݔ��İ༉���Q�����ڣ��������������롮y���������򷵻��ϼ��˵���");
				String isContinue = input.next();
				if (!"y".equalsIgnoreCase(isContinue)) {
					break;
				}
			}
		} while (!isExits);

		showClassesMenu();
	}

	public static void addClasses() {
		boolean isExits = false;
		do {
			System.out.println("�������ݹ���--->>�༶����--->>��Ӱ༶");
			System.out.println("-----------------------------");
			System.out.println("������༶���ƣ�");
			String className = input.next();
			// �ж�Ψһ��
			isExits = classesDao.checkExits(className);
			if (!isExits) {
				System.out.println("������༶��飺");
				String introduce = input.next();
				boolean isSuccess = classesDao.add(new Classes(className, introduce));
				if (isSuccess) {
					System.out.println("��ϲ����ӳɹ���");
					break;
				} else {
					System.out.println("���ʧ�ܣ�����ϵϵͳ����Ա");
				}
			} else {
				System.out.println("���ź����ð༉�����Ѵ��ڣ��������������ƣ�лл��");
			}
		} while (isExits);
		showClassesMenu();
	}
	
	
}
