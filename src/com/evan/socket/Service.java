package com.evan.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;



/**
 * @author scx
 *	服务端
 */
public class Service {
	public Service(){
		try {
			ServerSocket ss=new ServerSocket(8111);
			Socket socket=null;
			JOptionPane.showMessageDialog(null, "服务器已启动!请连接...");
			//循环监听客户端的连接
			while(true){
				socket=ss.accept();
				//为每个客户开启一个线程
				ServiceThread serviceThread=new ServiceThread(socket);
				Thread thread=new Thread(serviceThread);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
