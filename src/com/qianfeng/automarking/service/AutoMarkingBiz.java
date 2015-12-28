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
		System.out.println("常用功能--->>自动阅卷");
		System.out.println("-------------------");
		boolean result;
		// 第一步：下载格式文件
		result = downloadStandardFormat();
		if(result){
			// 第二步：使用系统读取答案文件
			List<String> answers = loadAnswer();
			// 第三步：读取学员答案文件-并开启阅卷
			loadStudentAnswerAndMarking(answers);
		}
		// 返回操作
		boolean isRight = false;
		int choice = 0;
		do {
			System.out.println("返回上一级请输入1，重新操作请输入2");
			try {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 2) {
					isRight = true;
				} else {
					System.out.println("请输入1-2之间的数字");
				}
			} catch (InputMismatchException e) {
				System.out.println("请输入1-2之间的数字");
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
		System.out.println("第三步：加载学生答案");
		File[] files;
		boolean hasFiles = false;
		String isContinue;
		do{
			System.out.println("请输入学生答案存放的文件夹目录，注意是文件夹目录，格式为：格式为：E:\\\\学生答案");
			System.out.println("学生答案文件名格式为：学生姓名.txt");
			String path = input.next();
			File dir = new File(path);
			files = dir.listFiles();
			if(files==null){
				System.out.println("目录不存在，请检查！");
			}else if(files.length==0){
				System.out.println("目录里面没有文件，请检查！");
			}else{
				hasFiles=true;
				break;
			}
			System.out.println("继续操作，请输入y，其他则退出");
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
					System.out.println("温馨提示：" + name + "未检测到答案信息，请检查其格式是否正确");
				}
				System.out.println(name + "---总得分：" + score);
			}
		}
		
	}

	public static List<String> loadAnswer() {
		List<String> answers = new ArrayList<String>();
		boolean isRight = false;
		do {
			System.out.println("第二步：加载本次考试的标准答案");
			System.out.println("請輸入本次考试答案的存放路径，例如:D:\\\\template.txt");
			String answerPath = input.next();
			File file = new File(answerPath);
			try {
				answers = FileUtil.loadToList(file);
				isRight = true;
				System.out.println("温馨提示：答案加载完毕!");
			} catch (IOException e) {
				System.out.println("文件路径有误或文件不存在，请确认是否输入正确!");
			}
		} while (!isRight);
		return answers;
	}

	/**
	 * 下載標準格式文件
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean downloadStandardFormat() {
		boolean isRight = false;
		String isContinue;
		System.out.println("第一步：下载标准的格式文件");
		String path;
		do {
			do{
				System.out.println("請輸入文件要保存的路徑，例如：D:\\\\template.txt");
				path = input.next();
				if(CheckUtil.checkDir(path)){
					break;
				}else{
					System.out.println("输入的不是一个正确的目录，请检查！");
					System.out.println("重新输入，请输入y，其他操作则退出");
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
				System.out.println("温馨提示：文件已经下载完毕，請到" + path + "查看,并將正確答案填入到该文件中");
				isRight = true;
			} catch (FileNotFoundException e) {
				System.out.println("服务器版本文件不存在，请联系系统管理员，谢谢！");
			} catch (IOException e) {
				System.out.println("设置的保存路径有误，请确保路径输入正确");
			} finally {
				ResourceUtil.close(writer, reader);
			}
		} while (!isRight);
		return isRight;
	}
}
