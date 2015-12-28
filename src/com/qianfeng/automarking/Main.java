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
 * ����ִ�����
 * 
 * @author HuangGuiZhao
 *
 */
public class Main {
	static Scanner input = new Scanner(System.in);
	
	/**
	 * ��������ϵͳ
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		showMainMenu();
	}

	/**
	 * ��ʾϵͳ���˵�
	 */
	public static void showMainMenu() {
		boolean isRight = false;
		int choice = 0;
		do {
			System.out.println("��лʹ��ǧ���ѧ����ϵͳ");
			System.out.println("----------------");
			System.out.println("�������ݹ���");
			System.out.println("1,�༶����");
			System.out.println("2,ѧԱ����");
			System.out.println("----------------");
			System.out.println("���ù���");
			System.out.println("3,�����ʴ�");
			System.out.println("4,�����ʴ�������");
			System.out.println("5,�Զ��ľ�");
			System.out.println("6,����Զ�̽����ļ�");
			System.out.println("----------------");
			try {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 6) {
					isRight = true;
				} else {
					System.out.println("������1-6֮�������");
				}
			} catch (InputMismatchException e) {
				System.out.println("������1-6֮�������");
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
