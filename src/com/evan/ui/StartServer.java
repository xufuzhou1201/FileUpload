package com.evan.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.evan.socket.Service;
/**
 * ��������˽���
 */
public class StartServer extends JFrame implements ActionListener{
	private static final long serialVersionUID = 3254784569816648178L;
	private JButton startServer_btn;
	private JButton endServer_btn;
	private Service startService;
	public StartServer(){
		setLayout(new FlowLayout());
		startServer_btn=new JButton("��������");
		endServer_btn=new JButton("�رշ���");
		add(startServer_btn);
		add(endServer_btn);
		setTitle("�����");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		startServer_btn.addActionListener(this);
		endServer_btn.addActionListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StartServer();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==startServer_btn){
			if(startService==null){
				/*
				 * ���߳��п���������  ����ʹ��main�߳� ������һֱ���� 
				 * main�߳�һֱ����  �޷�������������д���
				 */
				new startServerThread().start();
			}
		}
		//�˳�������
		if(e.getSource()==endServer_btn){
			startService=null;
			System.exit(0);
		}
	}
	private class startServerThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			startService=new Service();
		}
	}
}
