package com.qianfeng.automarking.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.qianfeng.automarking.util.ResourceUtil;


/**
 * ����ͻ���������߳�
 * @author HuangGuiZhao
 *
 */
public class LogicThread extends Thread{
	private Socket socket;
	
	public LogicThread(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedOutputStream bo = null;
		try {
			InputStream input = socket.getInputStream();
			BufferedInputStream bi = new BufferedInputStream(input);
			long time = System.currentTimeMillis();
			
			bo = new BufferedOutputStream(new FileOutputStream("d:\\upload"+time+".txt"));
			
			byte[] b = new byte[4096];
			int len;
			while((len=bi.read(b))!=-1){
				bo.write(b, 0, len);
				bo.flush();
			}
			//���ͻ�����Ӧ��Ϣ
			/*OutputStream out = socket.getOutputStream();
			out.write("�ļ��Ѵ������!".getBytes());*/
			System.out.println("�ļ��ѽ�����ϣ��ѱ��浽d:\\upload"+time+".txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally{
			ResourceUtil.close(socket,bo);
		}
	}
}
