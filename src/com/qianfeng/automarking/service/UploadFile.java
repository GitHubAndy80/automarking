package com.qianfeng.automarking.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.qianfeng.automarking.Main;

public class UploadFile {
	public static void start(){
		Scanner input = new Scanner(System.in);
		try {
			ServerSocket server = new ServerSocket(8888);
			System.out.println("开启8888端口，准备接收客户端文件");
			Socket socket = server.accept();
			new LogicThread(socket).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Main.showMainMenu();
	}
}
