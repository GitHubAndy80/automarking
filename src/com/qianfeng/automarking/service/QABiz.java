package com.qianfeng.automarking.service;

import java.util.List;
import java.util.Scanner;

import com.qianfeng.automarking.Main;
import com.qianfeng.automarking.context.Session;
import com.qianfeng.automarking.dao.StudentDao;
import com.qianfeng.automarking.entity.Student;
import com.qianfeng.automarking.util.CommonUtil;

/**
 * �ʴ�ҵ��
 * 
 * @author HuangGuiZhao
 *
 */
public class QABiz {
	private static Scanner input = new Scanner(System.in);
	private static StudentDao studentDao = new StudentDao();

	public static void showLuckyQA() {
		System.out.println("���ù���--->>�����ʴ�");
		System.out.println("-------------------");
		if (Session.checkIsExits("className")) {
			List<Student> list = studentDao.getList(Session.get("className"));
			if(list.size()==0){
				System.out.println("��ǰ�༶������ѧԱ��Ϣ����ȥ����ѧԱ��Ϣ��");
				Main.showMainMenu();
				return;
			}
			String isContinue;
			do {
				int luckyNum = CommonUtil.getRandom(0, list.size()-1);
				String name = list.get(luckyNum).getName();
				System.out.println("���γ��е�����ѧԱ��" + name);
				System.out.println("�ش��Ƿ���ȷ����ȷ������y��������Ϊ����ȷ");
				String isRight = input.next();
				if ("y".equals(isRight)) {
					studentDao.saveRightTime(name, Session.get("className"));
					list.remove(luckyNum);
					System.out.println("��ϲ" + name + "ͬѧ������ˣ�Ҫ�ȵ���һ�ֲ��л���ش�������");
				} else {
					System.out.println(name + "ͬѧ��ע���ˣ���һ���п��ܻ��ǳ鵽��");
					studentDao.saveWrongTime(name, Session.get("className"));
				}
				System.out.println("�Ƿ������ѧԱ�ش����⣿����������y��������Ϊ����");
				isContinue = input.next();
			} while ("y".equalsIgnoreCase(isContinue));
			Main.showMainMenu();
		} else {
			System.out.println("��δ����ȫ�ְ༶��Ϣ�������õ�ǰ�༶��");
			boolean result = GlobalSetBiz.setGlobalClasses();
			if (result) {
				showLuckyQA();
			} else {
				System.out.println("δ�ɹ����ð༶��Ϣ�����˻ص����˵�");
				Main.showMainMenu();
			}
		}
	}

	public static void showScoreBoard() {
		System.out.println("���ù���--->>�����ʴ�������");
		System.out.println("------------------------");
		if (Session.checkIsExits("className")) {
			List<Student> list = studentDao.getList(Session.get("className"));
			System.out.println("���а����");
			System.out.println("Ĭ���Դ�Դ�������Ϊ��Ҫ���ݣ������Դ���һ��������������ٵ�����ǰ��");
			System.out.println("����\t��Դ���\t������");
			for(int i=0;i<list.size();i++){
				Student s = list.get(i);
				System.out.println(s.getName()+"\t"+s.getRights()+"\t"+s.getErrors());
			}
			Main.showMainMenu();
		} else {
			System.out.println("��δ����ȫ�ְ༶��Ϣ�������õ�ǰ�༶��");
			boolean result = GlobalSetBiz.setGlobalClasses();
			if (result) {
				showLuckyQA();
			} else {
				System.out.println("δ�ɹ����ð༶��Ϣ�����˻ص����˵�");
				Main.showMainMenu();
			}
		}
	}

}
