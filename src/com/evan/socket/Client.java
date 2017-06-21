package com.evan.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.evan.util.CommandTransfer;

/**
 * @author scx
 *	客户端
 */
public class Client {
	public static Socket socket=null;
	public static int port=8111;
	public static String address="127.0.0.1";
	//获得服务器的数据
	public static CommandTransfer getData() {
		// TODO Auto-generated method stub
		ObjectInputStream ois=null;
		CommandTransfer res=null;
		try {
			ois=new ObjectInputStream(Client.socket.getInputStream());
			res=(CommandTransfer) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	//向服务器发送数据
	public static void sendData(CommandTransfer cmd) {
		// TODO Auto-generated method stub
		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(Client.socket.getOutputStream());
			oos.writeObject(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
