package com.evan.util;

import java.io.Serializable;

/**
 * @author scx
 *	�û�ָ����
 */
public class CommandTransfer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cmd;//����ָ��
	private Object data;//���͵�����
	private boolean flag;//�����Ƿ�ɹ�
	private String result;//���صĽ��
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
