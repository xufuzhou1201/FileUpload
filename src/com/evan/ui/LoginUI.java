package com.evan.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.evan.entity.User;
import com.evan.socket.Client;
import com.evan.util.CommandTransfer;

/**
 * @author scx
 *	�ͻ��˵�¼����
 */
public class LoginUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = -2686222552198867018L;
	private Box box1, box2, box3, baseBox;
	private JLabel username, password;
	private JTextField username_txt;
	private JPasswordField password_txt;
	private JButton login_btn, register_btn;
	public LoginUI() {
		setLayout(new FlowLayout());
		init();
		setTitle("�ͻ���");
		setSize(200, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void init() {
		username = new JLabel("�˻�");
		password = new JLabel("����");
		username_txt = new JTextField(10);
		password_txt = new JPasswordField(10);
		login_btn = new JButton("��¼");
		register_btn = new JButton("ע��");
		box1 = Box.createHorizontalBox();
		box2 = Box.createHorizontalBox();
		box3 = Box.createHorizontalBox();
		box1.add(username);
		box1.add(Box.createHorizontalStrut(8));
		box1.add(username_txt);
		box2.add(password);
		box2.add(Box.createHorizontalStrut(8));
		box2.add(password_txt);
		box3.add(login_btn);
		box3.add(Box.createHorizontalStrut(8));
		box3.add(register_btn);
		baseBox = Box.createVerticalBox();
		baseBox.add(box1);
		baseBox.add(Box.createVerticalStrut(5));
		baseBox.add(box2);
		baseBox.add(Box.createVerticalStrut(5));
		baseBox.add(box3);
		baseBox.add(Box.createVerticalStrut(5));
		add(baseBox);
		login_btn.addActionListener(this);
		register_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�������˵�¼��ť  �ж��˻��ڷ������Ƿ����
		if(e.getSource()==login_btn){
			User user=new User();
			user.setUsername(username_txt.getText().trim());
			user.setPassword(new String(password_txt.getPassword()).trim());
			CommandTransfer cmd =new CommandTransfer();
			cmd.setData(user);
			cmd.setCmd("login");
			try {
				Client.socket=new Socket(Client.address, Client.port);
				//���������������
				Client.sendData(cmd);
				//��÷��������͵�����
				cmd =Client.getData();
				JOptionPane.showMessageDialog(null, cmd.getResult());
				//�����¼�ɹ�  �رմ˴��� �����ϴ�����
				if(cmd.isFlag()){
					this.dispose();
					new UploadUI(user.getUsername());
				}
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "�����δ������");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "�����δ������");
			}
		}
		//��������ע�ᰴť  ��ע�����
		if(e.getSource()==register_btn){
			new RegisterUI();
		}
	}

	
}
