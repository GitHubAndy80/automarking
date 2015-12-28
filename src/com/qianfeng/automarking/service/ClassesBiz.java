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
			System.out.println("基础数据管理--->>班级管理");
			System.out.println("------------------");
			System.out.println("请选择您需要的操作：");
			System.out.println("1,新增班级");
			System.out.println("2,修改班级");
			System.out.println("3,删除班级");
			System.out.println("4,查看班级列表");
			System.out.println("5,返回上一级");
			System.out.println("------------------");
			try {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 5) {
					isRight = true;
				} else {
					System.out.println("请输入1-5之间的数字");
				}
			} catch (InputMismatchException e) {
				System.out.println("请输入1-5之间的数字");
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
		System.out.println("基础数据管理--->>班级管理--->>显示班级列表");
		System.out.println("-------------------------------");
		List<Classes> list = classesDao.getList();
		if (list.size() == 0) {
			System.out.println("温馨提示：您当前尚未添加任何班级信息,快去添加吧");
		} else {
			System.out.println("班级名称\t\t班级简介\t\t");
			for (Classes classes : list) {
				System.out.println(classes.getClassName() + "\t\t" + classes.getIntroduce() + "\t\t");
			}
		}
		showClassesMenu();
	}

	public static void delClasses() {
		boolean isExits = false;
		System.out.println("基础数据管理--->>班级管理--->>删除班级");
		System.out.println("-----------------------------");
		do {
			System.out.println("请输入班级名称：");
			String className = input.next();
			// 判断是否存在
			isExits = classesDao.checkExits(className);
			if (isExits) {
				boolean isSuccess = classesDao.del(className);
				if (isSuccess) {
					System.out.println("恭喜，删除成功！");
					break;
				} else {
					System.out.println("删除失败，请联系系统管理员");
				}
			} else {
				System.out.println("温馨提示：您輸入的班級名稱不存在，继续输入请输入‘y’，其他则返回上级菜单！");
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
		System.out.println("基础数据管理--->>班级管理--->>修改班级");
		System.out.println("-----------------------------");
		do {
			System.out.println("请输入要修改的班级名称：");
			String className = input.next();
			// 判断是否存在
			isExits = classesDao.checkExits(className);
			if (isExits) {
				System.out.println("请输入新的班级简介：");
				String introduce = input.next();
				boolean isSuccess = classesDao.update(new Classes(className, introduce));
				if (isSuccess) {
					System.out.println("恭喜，修改成功！");
					break;
				} else {
					System.out.println("修改失败，请联系系统管理员");
				}
			} else {
				System.out.println("温馨提示：您輸入的班級名稱不存在，继续输入请输入‘y’，其他则返回上级菜单！");
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
			System.out.println("基础数据管理--->>班级管理--->>添加班级");
			System.out.println("-----------------------------");
			System.out.println("请输入班级名称：");
			String className = input.next();
			// 判断唯一性
			isExits = classesDao.checkExits(className);
			if (!isExits) {
				System.out.println("请输入班级简介：");
				String introduce = input.next();
				boolean isSuccess = classesDao.add(new Classes(className, introduce));
				if (isSuccess) {
					System.out.println("恭喜，添加成功！");
					break;
				} else {
					System.out.println("添加失败，请联系系统管理员");
				}
			} else {
				System.out.println("很遗憾，该班級名称已存在，请输入其他名称，谢谢！");
			}
		} while (isExits);
		showClassesMenu();
	}
	
	
}
