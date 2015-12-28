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
			System.out.println("�������ݹ���--->>ѧԱ����");
			System.out.println("------------------");
			System.out.println("1,���õ�ǰ�༶������һ��ȫ����Ч,����������!��");
			System.out.println("2,�������ѧԱ");
			System.out.println("3,���ѧԱ");
			System.out.println("4,�޸�ѧԱ");
			System.out.println("5,ɾ��ѧԱ");
			System.out.println("6,�鿴ѧԱ�б�");
			System.out.println("7,������һ��");
			System.out.println("------------------");
			try {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 7) {
					isRight = true;
				} else {
					System.out.println("������1-7֮�������");
				}
			} catch (InputMismatchException e) {
				System.out.println("������1-7֮�������");
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
			System.out.println("��δ���õ�ǰ�༶���������õ�ǰ�༶");
		}else{
			System.out.println("�������ݹ���--->>ѧԱ����--->>�鿴ѧԱ�б�");
			System.out.println("-------------------------------");
			List<Student> list = studentDao.getList(Session.get("className"));
			if (list.size() == 0) {
				System.out.println("��ܰ��ʾ������ǰ��δ����κ�ѧԱ��Ϣ,��ȥ��Ӱ�");
			} else {
				System.out.println("����\t\t����\t\t�绰\t\t�����༶");
				for (Student s : list) {
					System.out.println(s.getName() + "\t\t" + s.getAge() + "\t\t" + s.getPhone()+"\t\t"+s.getClassName());
				}
			}
		}
		showStudentsMenu();
	}

	private static void delStudent() {
		if(!Session.checkIsExits("className")){
			System.out.println("��δ���õ�ǰ�༶���������õ�ǰ�༶");
		}else{
			System.out.println("�������ݹ���--->>ѧԱ����--->>ɾ��ѧԱ");
			System.out.println("-------------------------------");
			boolean isExits = false;
			String className = Session.get("className");
			do {
				System.out.println("������ѧԱ������");
				String name = input.next();
				// �ж��Ƿ����
				isExits = studentDao.checkExits(name,className);
				if (isExits) {
					boolean isSuccess = studentDao.del(new Student(name,className));
					if (isSuccess) {
						System.out.println("��ϲ��ɾ���ɹ���");
						break;
					} else {
						System.out.println("ɾ��ʧ�ܣ�����ϵϵͳ����Ա");
					}
				} else {
					System.out.println("��ܰ��ʾ����ݔ���ѧԱ���Q�����ڣ��������������롮y���������򷵻��ϼ��˵���");
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
			System.out.println("��δ���õ�ǰ�༶���������õ�ǰ�༶");
		}else{
			String className = Session.get("className");
			boolean isExits = false;
			System.out.println("�������ݹ���--->>ѧԱ����--->>�޸�ѧԱ");
			System.out.println("-----------------------------");
			do {
				System.out.println("������Ҫ�޸ĵ�ѧԱ������");
				String name = input.next();
				// �ж��Ƿ����
				isExits = studentDao.checkExits(name,className);
				if (isExits) {
					System.out.println("�������µ�ѧԱ���䣺");
					int age = input.nextInt();
					String phone;
					boolean isOk = true;
					do{
						System.out.println("�������µ���ϵ�绰��");
						phone = input.next();
						if(!CheckUtil.checkPhone(phone)){
							System.out.println("�绰��ʽ����ȷ��������¼��");
							isOk = false;
						}
					}while(!isOk);
					boolean result = studentDao.udate(new Student(name,age,phone,className));
					if(result){
						System.out.println("�޸ĳɹ���");
					}else{
						System.out.println("�޸�ʧ�ܣ�����ϵ����Ա");
					}
				} else {
					System.out.println("��ܰ��ʾ����ݔ���ѧԱ���������ڣ��������������롮y���������򷵻��ϼ��˵���");
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
			System.out.println("��δ���õ�ǰ�༶���������õ�ǰ�༶");
		}else{
			String className = Session.get("className");
			System.out.println("�������ݹ���--->>ѧԱ����--->>���ѧԱ");
			System.out.println("-------------------------------");
			System.out.println("������ѧԱ������");
			String name = input.next();
			System.out.println("������ѧԱ���䣺");
			int age = input.nextInt();
			String phone;
			boolean isOk = true;
			do{
				System.out.println("��������ϵ�绰��");
				phone = input.next();
				if(!CheckUtil.checkPhone(phone)){
					System.out.println("�绰��ʽ����ȷ��������¼��");
					isOk = false;
				}
			}while(!isOk);
			boolean result = studentDao.add(new Student(name,age,phone,className));
			if(result){
				System.out.println("��ӳɹ���");
			}else{
				System.out.println("���ʧ�ܣ�����ϵ����Ա");
			}
		}
		showStudentsMenu();
	}

	private static void batchAddStudent() {
		if(!Session.checkIsExits("className")){
			System.out.println("��δ���õ�ǰ�༶���������õ�ǰ�༶");
		}else{
			System.out.println("�������ݹ���--->>ѧԱ����--->>�������ѧԱ");
			System.out.println("-------------------------------");
			System.out.println("����˵����1�����ո�ʽҪ����д���ϴ����ļ����ݣ�2�������ϴ����ļ�·��");
			System.out.println("��ʽҪ�����£�");
			System.out.println("���� | 20 | 13578912345");
			System.out.println("���� | 20 | 13578912345");
			boolean isRight = false;
			do {
				System.out.println("������Ҫ�ϴ���ѧԱ�ļ�·����");
				System.out.println("����:D:\\\\students.txt");
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
						System.out.println("ѧԱ����������ӳɹ�!");
					} else {
						System.out.println("�����������ʧ�ܣ������ļ���ʽ�������Ƿ����󣬻�ֱ����ϵ����Ա");
						System.out.println("�������������롮y���������򷵻���һ���˵�");
						String isContinue = input.next();
						if (!"y".equalsIgnoreCase(isContinue)) {
							break;
						}
					}
				} catch (Exception e) {
					System.out.println("�ļ�·��������ļ�������");
					System.out.println("�������������롮y���������򷵻���һ���˵�");
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
