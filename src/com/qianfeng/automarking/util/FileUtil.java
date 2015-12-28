package com.qianfeng.automarking.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类，负责加载解析文件内容
 * @author HuangGuiZhao
 *
 */
public class FileUtil {
	
	public static List<String> loadToList(File file) throws IOException {
		List<String> answers = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String answer = reader.readLine();
		while ((answer = reader.readLine()) != null) {
			answer = answer.split(",")[1].trim();
			answers.add(answer);
		}
		reader.close();
		return answers;
	}
}
