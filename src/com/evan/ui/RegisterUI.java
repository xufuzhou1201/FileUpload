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
 *	�ͻ���ע�����
 */
public class RegisterUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -717590965372186957L;
	private Box box1, box2, baseBox;
	private JLabel username, password,password2;
	private JTextField username_txt;
	private JPasswordField password_txt,password_txt2;
	private JButton cancel_btn, register_btn;
	public RegisterUI(){
		setLayout(new FlowLayout());
		init();
		setTitle("ע��");
		setSize(250, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	private void init() {
		// TODO Auto-generated method stub
		username = new JLabel("�˻�");
		password = new JLabel("����");
		password2=new JLabel("�ٴ���������");
		username_txt = new JTextField(10);
		password_txt = new JPasswordField(10);
		password_txt2=new JPasswordField(10);
		cancel_btn = new JButton("ȡ��");
		register_btn = new JButton("ע��");
		box1=Box.createVerticalBox();
		box2=Box.createVerticalBox();
		box1.add(username);
		box1.add(Box.createVerticalStrut(10));
		box1.add(password);
		box1.add(Box.createVerticalStrut(10));
		box1.add(password2);
		box1.add(Box.createVerticalStrut(10));
		
		box2.add(username_txt);
		box2.add(Box.createVerticalStrut(10));
		box2.add(password_txt);
		box2.add(Box.createVerticalStrut(10));
		box2.add(password_txt2);
		box2.add(Box.createVerticalStrut(10));
		baseBox=Box.createHorizontalBox();
		baseBox.add(box1);
		baseBox.add(Box.createHorizontalStrut(5));
		baseBox.add(box2);
		add(baseBox);
		add(register_btn);
		add(cancel_btn);
		cancel_btn.addActionListener(this);
		register_btn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==register_btn){
			String username=username_txt.getText().trim();
			String password1=new String(password_txt.getPassword()).trim();
			String password2=new String(password_txt2.getPassword()).trim();
			if(username==null||"".equals(username)){
				JOptionPane.showMessageDialog(null, "�������û�������");
				return ;
			}
			if(password1==null||"".equals(password1)){
				JOptionPane.showMessageDialog(null, "���������룡��");
				return ;
			}
			if(!(password1.equals(password2))){
				JOptionPane.showMessageDialog(null, "������������벻һ�£���");
				return ;
			}
			//�����������ע����Ϣ
			User user=new User(username, password1);
			CommandTransfer cmd=new CommandTransfer();
			cmd.setCmd("register");
			cmd.setData(user);
			try {
				System.out.println(Client.address+Client.port);
				Client.socket=new Socket(Client.address, Client.port);
				//���������������
				Client.sendData(cmd);
				//��÷��������͵�����
				cmd =Client.getData();
				JOptionPane.showMessageDialog(null, cmd.getResult());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//��ʼ���ı���
		if(e.getSource()==cancel_btn){
			username_txt.setText(null);
			password_txt.setText(null);
			password_txt2.setText(null);
		}
	}
}
