package com.qianfeng.automarking;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.qianfeng.automarking.service.AutoMarkingBiz;
import com.qianfeng.automarking.service.ClassesBiz;
import com.qianfeng.automarking.service.QABiz;
import com.qianfeng.automarking.service.StudentBiz;
import com.qianfeng.automarking.service.UploadFile;

/**
 * 程序执行入口
 * 
 * @author HuangGuiZhao
 *
 */
public class Main {
	static Scanner input = new Scanner(System.in);
	
	/**
	 * 启动管理系统
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		showMainMenu();
	}

	/**
	 * 显示系统主菜单
	 */
	public static void showMainMenu() {
		boolean isRight = false;
		int choice = 0;
		do {
			System.out.println("感谢使用千锋教学管理系统");
			System.out.println("----------------");
			System.out.println("基础数据管理");
			System.out.println("1,班级管理");
			System.out.println("2,学员管理");
			System.out.println("----------------");
			System.out.println("常用功能");
			System.out.println("3,幸运问答");
			System.out.println("4,幸运问答龙虎榜");
			System.out.println("5,自动阅卷");
			System.out.println("6,开启远程接收文件");
			System.out.println("----------------");
			try {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 6) {
					isRight = true;
				} else {
					System.out.println("请输入1-6之间的数字");
				}
			} catch (InputMismatchException e) {
				System.out.println("请输入1-6之间的数字");
				input.next();
			}
		} while (!isRight);
		switch (choice) {
		case 1:
			ClassesBiz.showClassesMenu();
			break;
		case 2:
			StudentBiz.showStudentsMenu();
			break;
		case 3:
			QABiz.showLuckyQA();
			break;
		case 4:
			QABiz.showScoreBoard();
			break;
		case 5:
			AutoMarkingBiz.showAutoMarking();
			break;
		case 6:
			UploadFile.start();
			break;
		default:
			break;
		}
	}
}
