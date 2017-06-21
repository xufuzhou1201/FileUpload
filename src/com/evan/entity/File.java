package com.evan.entity;

import java.io.Serializable;

public class File implements Serializable{

	/**
	 * @author evanxuhe
	 * 文件的实体类
	 */
	private static final long serialVersionUID = 1L;
	private String fname;
	private String username;
	private byte[] fcontent;
	public File() {
		super();
		// TODO Auto-generated constructor stub
	}

	public File(String fname, String username, byte[] fcontent) {
		super();
		this.fname = fname;
		this.username = username;
		this.fcontent = fcontent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public byte[] getFcontent() {
		return fcontent;
	}
	public void setFcontent(byte[] fcontent) {
		this.fcontent = fcontent;
	}
	
}
