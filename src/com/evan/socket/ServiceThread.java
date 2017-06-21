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
 *	服务器线程
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
			//处理客户端发送来的数据
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
	//处理客户端发送来的数据
	private CommandTransfer execute(CommandTransfer cmd) {
		// TODO Auto-generated method stub
		//客户端登录操作
		if("login".equals(cmd.getCmd())){
			UserService userService=new UserService();
			User user=(User) cmd.getData();
			cmd.setFlag(userService.checkUser(user));
			if(cmd.isFlag()){
				cmd.setResult("登录成功！！");
			}else{
				cmd.setResult("oh my god !!登录失败了。。");
			}
		}
		//客户端注册用户操作
		if("register".equals(cmd.getCmd())){
			UserService userService=new UserService();
			User user=(User) cmd.getData();
			System.out.println(user.toString());
			cmd.setFlag(userService.addUser(user));
			if(cmd.isFlag()){
				cmd.setResult("恭喜你。注册成功，请登录!");
			}else{
				cmd.setResult("oh my god !!注册失败了..");
			}
		}
		//客户端上传文件操作
		if("uploadFile".equals(cmd.getCmd())){
			FileService fileService=new FileService();
			File file=(File) cmd.getData();
			cmd.setFlag(fileService.uploadFile(file));
			if(cmd.isFlag()){
				cmd.setResult("恭喜你，上传成功!");
			}else{
				cmd.setResult("很遗憾，上传失败！");
			}
		}
		return cmd;
	}

}
