package com.qianfeng.automarking.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.qianfeng.automarking.context.Session;
import com.qianfeng.automarking.dao.StudentDao;
import com.qianfeng.automarking.entity.Student;
import com.qianfeng.automarking.util.CheckUtil;
import com.qianfeng.automarking.util.ResourceUtil;

public class StudentBiz {
	private static Scanner input = new Scanner(System.in);
	private static StudentDao studentDao = new StudentDao();

	public static void showStudentsMenu() {
		boolean isRight = false;
		int choice = 0;
		do {
			System.out.println("基础数据管理--->>学员管理");
			System.out.println("------------------");
			System.out.println("1,设置当前班级（设置一次全局有效,请先设置我!）");
			System.out.println("2,批量添加学员");
			System.out.println("3,添加学员");
			System.out.println("4,修改学员");
			System.out.println("5,删除学员");
			System.out.println("6,查看学员列表");
			System.out.println("7,返回上一级");
			System.out.println("------------------");
			try {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 7) {
					isRight = true;
				} else {
					System.out.println("请输入1-7之间的数字");
				}
			} catch (InputMismatchException e) {
				System.out.println("请输入1-7之间的数字");
				input.next();
			}
		} while (!isRight);
		switch (choice) {
		case 1:
			setClasses();
			break;
		case 2:
			batchAddStudent();
			break;
		case 3:
			addStudent();
			break;
		case 4:
			modifyStudent();
			break;
		case 5:
			delStudent();
			break;
		case 6:
			showStudentList();
			break;
		case 7:
			ClassesBiz.showClassesMenu();
			break;
		default:
			break;
		}
	}

	private static void setClasses() {
		GlobalSetBiz.setGlobalClasses();
		showStudentsMenu();
	}

	private static void showStudentList() {
		if(!Session.checkIsExits("className")){
			System.out.println("尚未设置当前班级，请先设置当前班级");
		}else{
			System.out.println("基础数据管理--->>学员管理--->>查看学员列表");
			System.out.println("-------------------------------");
			List<Student> list = studentDao.getList(Session.get("className"));
			if (list.size() == 0) {
				System.out.println("温馨提示：您当前尚未添加任何学员信息,快去添加吧");
			} else {
				System.out.println("姓名\t\t年龄\t\t电话\t\t所属班级");
				for (Student s : list) {
					System.out.println(s.getName() + "\t\t" + s.getAge() + "\t\t" + s.getPhone()+"\t\t"+s.getClassName());
				}
			}
		}
		showStudentsMenu();
	}

	private static void delStudent() {
		if(!Session.checkIsExits("className")){
			System.out.println("尚未设置当前班级，请先设置当前班级");
		}else{
			System.out.println("基础数据管理--->>学员管理--->>删除学员");
			System.out.println("-------------------------------");
			boolean isExits = false;
			String className = Session.get("className");
			do {
				System.out.println("请输入学员姓名：");
				String name = input.next();
				// 判断是否存在
				isExits = studentDao.checkExits(name,className);
				if (isExits) {
					boolean isSuccess = studentDao.del(new Student(name,className));
					if (isSuccess) {
						System.out.println("恭喜，删除成功！");
						break;
					} else {
						System.out.println("删除失败，请联系系统管理员");
					}
				} else {
					System.out.println("温馨提示：您輸入的学员名稱不存在，继续输入请输入‘y’，其他则返回上级菜单！");
					String isContinue = input.next();
					if (!"y".equalsIgnoreCase(isContinue)) {
						break;
					}
				}
			} while (!isExits);
		}
		showStudentsMenu();
	}

	private static void modifyStudent() {
		if(!Session.checkIsExits("className")){
			System.out.println("尚未设置当前班级，请先设置当前班级");
		}else{
			String className = Session.get("className");
			boolean isExits = false;
			System.out.println("基础数据管理--->>学员管理--->>修改学员");
			System.out.println("-----------------------------");
			do {
				System.out.println("请输入要修改的学员姓名：");
				String name = input.next();
				// 判断是否存在
				isExits = studentDao.checkExits(name,className);
				if (isExits) {
					System.out.println("请输入新的学员年龄：");
					int age = input.nextInt();
					String phone;
					boolean isOk = true;
					do{
						System.out.println("请输入新的联系电话：");
						phone = input.next();
						if(!CheckUtil.checkPhone(phone)){
							System.out.println("电话格式不正确，请重新录入");
							isOk = false;
						}
					}while(!isOk);
					boolean result = studentDao.udate(new Student(name,age,phone,className));
					if(result){
						System.out.println("修改成功！");
					}else{
						System.out.println("修改失败，请联系管理员");
					}
				} else {
					System.out.println("温馨提示：您輸入的学员姓名不存在，继续输入请输入‘y’，其他则返回上级菜单！");
					String isContinue = input.next();
					if (!"y".equalsIgnoreCase(isContinue)) {
						break;
					}
				}
			} while (!isExits);
		}
		showStudentsMenu();
	}

	private static void addStudent() {
		if(!Session.checkIsExits("className")){
			System.out.println("尚未设置当前班级，请先设置当前班级");
		}else{
			String className = Session.get("className");
			System.out.println("基础数据管理--->>学员管理--->>添加学员");
			System.out.println("-------------------------------");
			System.out.println("请输入学员姓名：");
			String name = input.next();
			System.out.println("请输入学员年龄：");
			int age = input.nextInt();
			String phone;
			boolean isOk = true;
			do{
				System.out.println("请输入联系电话：");
				phone = input.next();
				if(!CheckUtil.checkPhone(phone)){
					System.out.println("电话格式不正确，请重新录入");
					isOk = false;
				}
			}while(!isOk);
			boolean result = studentDao.add(new Student(name,age,phone,className));
			if(result){
				System.out.println("添加成功！");
			}else{
				System.out.println("添加失败，请联系管理员");
			}
		}
		showStudentsMenu();
	}

	private static void batchAddStudent() {
		if(!Session.checkIsExits("className")){
			System.out.println("尚未设置当前班级，请先设置当前班级");
		}else{
			System.out.println("基础数据管理--->>学员管理--->>批量添加学员");
			System.out.println("-------------------------------");
			System.out.println("操作说明：1，按照格式要求填写好上传的文件内容；2，设置上传的文件路径");
			System.out.println("格式要求如下：");
			System.out.println("张三 | 20 | 13578912345");
			System.out.println("李四 | 20 | 13578912345");
			boolean isRight = false;
			do {
				System.out.println("请输入要上传的学员文件路径：");
				System.out.println("例如:D:\\\\students.txt");
				String path = input.next();
				BufferedReader reader = null;
				try {
					File file = new File(path);
					reader = new BufferedReader(new FileReader(file));
					List<Student> list = new ArrayList<Student>();
					String line;
					String className = Session.get("className");
					while ((line = reader.readLine()) != null) {
						String[] infos = line.split("\\|");
						String name = infos[0].trim();
						int age = Integer.parseInt(infos[1].trim());
						String phone = infos[2].trim();
						list.add(new Student(name, age, phone,className));
					}
					boolean result = studentDao.add(list);
					if (result) {
						isRight = true;
						System.out.println("学员数据批量添加成功!");
					} else {
						System.out.println("批量添加数据失败，请检查文件格式或数据是否有误，或直接联系管理员");
						System.out.println("重新输入请输入‘y’，其他则返回上一级菜单");
						String isContinue = input.next();
						if (!"y".equalsIgnoreCase(isContinue)) {
							break;
						}
					}
				} catch (Exception e) {
					System.out.println("文件路径有误或文件不存在");
					System.out.println("重新输入请输入‘y’，其他则返回上一级菜单");
					String isContinue = input.next();
					if (!"y".equalsIgnoreCase(isContinue)) {
						break;
					}
				} finally {
					ResourceUtil.close(reader);
				}
			} while (!isRight);
		}
		showStudentsMenu();
	}

	
}
