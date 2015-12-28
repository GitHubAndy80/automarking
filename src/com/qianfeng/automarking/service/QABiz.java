package com.qianfeng.automarking.service;

import java.util.List;
import java.util.Scanner;

import com.qianfeng.automarking.Main;
import com.qianfeng.automarking.context.Session;
import com.qianfeng.automarking.dao.StudentDao;
import com.qianfeng.automarking.entity.Student;
import com.qianfeng.automarking.util.CommonUtil;

/**
 * 问答业务
 * 
 * @author HuangGuiZhao
 *
 */
public class QABiz {
	private static Scanner input = new Scanner(System.in);
	private static StudentDao studentDao = new StudentDao();

	public static void showLuckyQA() {
		System.out.println("常用功能--->>幸运问答");
		System.out.println("-------------------");
		if (Session.checkIsExits("className")) {
			List<Student> list = studentDao.getList(Session.get("className"));
			if(list.size()==0){
				System.out.println("当前班级不存在学员信息，快去设置学员信息吧");
				Main.showMainMenu();
				return;
			}
			String isContinue;
			do {
				int luckyNum = CommonUtil.getRandom(0, list.size()-1);
				String name = list.get(luckyNum).getName();
				System.out.println("本次抽中的幸运学员：" + name);
				System.out.println("回答是否正确？正确请输入y，其他视为不正确");
				String isRight = input.next();
				if ("y".equals(isRight)) {
					studentDao.saveRightTime(name, Session.get("className"));
					list.remove(luckyNum);
					System.out.println("恭喜" + name + "同学，答对了，要等到下一轮才有机会回答问题了");
				} else {
					System.out.println(name + "同学，注意了，下一次有可能还是抽到你");
					studentDao.saveWrongTime(name, Session.get("className"));
				}
				System.out.println("是否继续抽学员回答问题？继续请输入y，其他视为结束");
				isContinue = input.next();
			} while ("y".equalsIgnoreCase(isContinue));
			Main.showMainMenu();
		} else {
			System.out.println("尚未设置全局班级信息，请设置当前班级：");
			boolean result = GlobalSetBiz.setGlobalClasses();
			if (result) {
				showLuckyQA();
			} else {
				System.out.println("未成功设置班级信息，将退回到主菜单");
				Main.showMainMenu();
			}
		}
	}

	public static void showScoreBoard() {
		System.out.println("常用功能--->>幸运问答龙虎榜");
		System.out.println("------------------------");
		if (Session.checkIsExits("className")) {
			List<Student> list = studentDao.getList(Session.get("className"));
			System.out.println("排行榜规则：");
			System.out.println("默认以答对次数来作为首要依据，如果答对次数一样，则答错次数更少的排在前面");
			System.out.println("姓名\t答对次数\t答错次数");
			for(int i=0;i<list.size();i++){
				Student s = list.get(i);
				System.out.println(s.getName()+"\t"+s.getRights()+"\t"+s.getErrors());
			}
			Main.showMainMenu();
		} else {
			System.out.println("尚未设置全局班级信息，请设置当前班级：");
			boolean result = GlobalSetBiz.setGlobalClasses();
			if (result) {
				showLuckyQA();
			} else {
				System.out.println("未成功设置班级信息，将退回到主菜单");
				Main.showMainMenu();
			}
		}
	}

}
