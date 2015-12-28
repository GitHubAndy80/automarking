package com.qianfeng.automarking.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.qianfeng.automarking.Main;
import com.qianfeng.automarking.util.CheckUtil;
import com.qianfeng.automarking.util.FileUtil;
import com.qianfeng.automarking.util.ResourceUtil;

public class AutoMarkingBiz {
	private static Scanner input = new Scanner(System.in);

	public static void showAutoMarking() {
		System.out.println("���ù���--->>�Զ��ľ�");
		System.out.println("-------------------");
		boolean result;
		// ��һ�������ظ�ʽ�ļ�
		result = downloadStandardFormat();
		if(result){
			// �ڶ�����ʹ��ϵͳ��ȡ���ļ�
			List<String> answers = loadAnswer();
			// ����������ȡѧԱ���ļ�-�������ľ�
			loadStudentAnswerAndMarking(answers);
		}
		// ���ز���
		boolean isRight = false;
		int choice = 0;
		do {
			System.out.println("������һ��������1�����²���������2");
			try {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 2) {
					isRight = true;
				} else {
					System.out.println("������1-2֮�������");
				}
			} catch (InputMismatchException e) {
				System.out.println("������1-2֮�������");
				input.next();
			}
		} while (!isRight);
		switch (choice) {
		case 1:
			Main.showMainMenu();
			break;
		case 2:
			showAutoMarking();
			break;
		default:
			break;
		}

	}

	public static void loadStudentAnswerAndMarking(List<String> answers) {
		System.out.println("������������ѧ����");
		File[] files;
		boolean hasFiles = false;
		String isContinue;
		do{
			System.out.println("������ѧ���𰸴�ŵ��ļ���Ŀ¼��ע�����ļ���Ŀ¼����ʽΪ����ʽΪ��E:\\\\ѧ����");
			System.out.println("ѧ�����ļ�����ʽΪ��ѧ������.txt");
			String path = input.next();
			File dir = new File(path);
			files = dir.listFiles();
			if(files==null){
				System.out.println("Ŀ¼�����ڣ����飡");
			}else if(files.length==0){
				System.out.println("Ŀ¼����û���ļ������飡");
			}else{
				hasFiles=true;
				break;
			}
			System.out.println("����������������y���������˳�");
			isContinue = input.next();
		}while("y".equalsIgnoreCase(isContinue));
		
		if(hasFiles){
			for (File f : files) {
				String name = f.getName().split("\\.")[0];
				List<String> students = null;
				try {
					students = FileUtil.loadToList(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
				int score = 0;
				if (students != null) {
					for (int i = 0; i < answers.size(); i++) {
						if (answers.get(i).equalsIgnoreCase(students.get(i))) {
							score++;
						}
					}
				} else {
					System.out.println("��ܰ��ʾ��" + name + "δ��⵽����Ϣ���������ʽ�Ƿ���ȷ");
				}
				System.out.println(name + "---�ܵ÷֣�" + score);
			}
		}
		
	}

	public static List<String> loadAnswer() {
		List<String> answers = new ArrayList<String>();
		boolean isRight = false;
		do {
			System.out.println("�ڶ��������ر��ο��Եı�׼��");
			System.out.println("Ոݔ�뱾�ο��Դ𰸵Ĵ��·��������:D:\\\\template.txt");
			String answerPath = input.next();
			File file = new File(answerPath);
			try {
				answers = FileUtil.loadToList(file);
				isRight = true;
				System.out.println("��ܰ��ʾ���𰸼������!");
			} catch (IOException e) {
				System.out.println("�ļ�·��������ļ������ڣ���ȷ���Ƿ�������ȷ!");
			}
		} while (!isRight);
		return answers;
	}

	/**
	 * ���d�˜ʸ�ʽ�ļ�
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean downloadStandardFormat() {
		boolean isRight = false;
		String isContinue;
		System.out.println("��һ�������ر�׼�ĸ�ʽ�ļ�");
		String path;
		do {
			do{
				System.out.println("Ոݔ���ļ�Ҫ�����·�������磺D:\\\\template.txt");
				path = input.next();
				if(CheckUtil.checkDir(path)){
					break;
				}else{
					System.out.println("����Ĳ���һ����ȷ��Ŀ¼�����飡");
					System.out.println("�������룬������y�������������˳�");
					isContinue = input.next();
					if(!"y".equalsIgnoreCase(isContinue)){
						return isRight;
					}
				}
			}while("y".equalsIgnoreCase(isContinue));
			
			BufferedReader reader = null;
			BufferedWriter writer = null;
			try {
				reader = new BufferedReader(new FileReader("file\\template.txt"));
				writer = new BufferedWriter(new FileWriter(path));
				String line;
				while ((line = reader.readLine()) != null) {
					writer.write(line);
					writer.newLine();
				}
				System.out.println("��ܰ��ʾ���ļ��Ѿ�������ϣ�Ո��" + path + "�鿴,�������_�����뵽���ļ���");
				isRight = true;
			} catch (FileNotFoundException e) {
				System.out.println("�������汾�ļ������ڣ�����ϵϵͳ����Ա��лл��");
			} catch (IOException e) {
				System.out.println("���õı���·��������ȷ��·��������ȷ");
			} finally {
				ResourceUtil.close(writer, reader);
			}
		} while (!isRight);
		return isRight;
	}
}
