package com.evan.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;



/**
 * @author scx
 *	�����
 */
public class Service {
	public Service(){
		try {
			ServerSocket ss=new ServerSocket(8111);
			Socket socket=null;
			JOptionPane.showMessageDialog(null, "������������!������...");
			//ѭ�������ͻ��˵�����
			while(true){
				socket=ss.accept();
				//Ϊÿ���ͻ�����һ���߳�
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
