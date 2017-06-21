package com.evan.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.evan.entity.File;
import com.evan.socket.Client;
import com.evan.util.CommandTransfer;

/**
 *	�ϴ��ļ�����
 */
public class UploadUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = -8830003248247613172L;
	private JFileChooser fileChooser;//�ļ�ѡ��Ի���
	private JButton upload_btn, choose_btn;//�ϴ���ť ��ѡ��ť
	private JTextField path_txt;//�ļ��ľ���·��
	private String username;//�ɹ���¼���û���

	public UploadUI(String username) {
		this.username = username;
		init();
		setTitle("�ϴ��ļ�");
		setSize(250, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new FlowLayout());
		choose_btn = new JButton("ѡ��");
		upload_btn = new JButton("�ϴ�");
		fileChooser = new JFileChooser();
		path_txt = new JTextField(20);
		add(path_txt);
		add(choose_btn);
		add(upload_btn);
		choose_btn.addActionListener(this);
		upload_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ��������ѡ���ļ�
		if (e.getSource() == choose_btn) {
			//��ѡ���ļ��Ի���
			int state = fileChooser.showSaveDialog(null);
			if (state == 0) {
				String pathChoose = fileChooser.getSelectedFile().getPath();
				path_txt.setText(pathChoose);
			}
		}
		// ���������ϴ���ť
		if (e.getSource() == upload_btn) {
			if(path_txt.getText().trim()==null||"".equals(path_txt.getText().trim())){
				JOptionPane.showMessageDialog(null, "����ѡ���ļ����ϴ�����");
				return ;
			}
			uploadFile();
		}
	}
	//�ϴ��ļ�
	private void uploadFile() {
		// TODO Auto-generated method stub
		File file = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		String path = path_txt.getText().trim();
		String fname = path.substring(path.lastIndexOf("\\") + 1);
		try {
			fis = new FileInputStream(path);
			// fis.available�����ļ����ܴ�С
			byte[] fcontent = new byte[fis.available()];
			bis = new BufferedInputStream(fis);
			//��ȡ�ļ�����
			bis.read(fcontent);
			//ʵ����file����
			file = new File(fname, username, fcontent);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//������������ļ�
		CommandTransfer cmd = new CommandTransfer();
		cmd.setData(file);
		cmd.setCmd("uploadFile");
		try {
			Client.socket = new Socket(Client.address,
					Client.port);
			//���������������
			Client.sendData(cmd);
			//��÷�����������Ϣ
			cmd = Client.getData();
			JOptionPane.showMessageDialog(null, cmd.getResult());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
