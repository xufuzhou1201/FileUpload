package com.evan.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.evan.entity.File;
import com.evan.entity.User;
import com.evan.service.FileService;
import com.evan.service.UserService;
import com.evan.util.CommandTransfer;

/**
 * @author evanxuhe
 *	�������߳�
 */
public class ServiceThread implements Runnable {
	private Socket socket;

	public ServiceThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			CommandTransfer cmd=(CommandTransfer) ois.readObject();
			//����ͻ��˷�����������
			cmd=execute(cmd);
			oos.writeObject(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ois!=null)
					ois.close();
				if(oos!=null){
					oos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	//����ͻ��˷�����������
	private CommandTransfer execute(CommandTransfer cmd) {
		// TODO Auto-generated method stub
		//�ͻ��˵�¼����
		if("login".equals(cmd.getCmd())){
			UserService userService=new UserService();
			User user=(User) cmd.getData();
			cmd.setFlag(userService.checkUser(user));
			if(cmd.isFlag()){
				cmd.setResult("��¼�ɹ�����");
			}else{
				cmd.setResult("oh my god !!��¼ʧ���ˡ���");
			}
		}
		//�ͻ���ע���û�����
		if("register".equals(cmd.getCmd())){
			UserService userService=new UserService();
			User user=(User) cmd.getData();
			System.out.println(user.toString());
			cmd.setFlag(userService.addUser(user));
			if(cmd.isFlag()){
				cmd.setResult("��ϲ�㡣ע��ɹ������¼!");
			}else{
				cmd.setResult("oh my god !!ע��ʧ����..");
			}
		}
		//�ͻ����ϴ��ļ�����
		if("uploadFile".equals(cmd.getCmd())){
			FileService fileService=new FileService();
			File file=(File) cmd.getData();
			cmd.setFlag(fileService.uploadFile(file));
			if(cmd.isFlag()){
				cmd.setResult("��ϲ�㣬�ϴ��ɹ�!");
			}else{
				cmd.setResult("���ź����ϴ�ʧ�ܣ�");
			}
		}
		return cmd;
	}

}
