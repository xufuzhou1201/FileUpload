package com.evan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author scx
 *	���ݿ�
 */
public class DBHelper {
	private static final String driver="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/socket?useUnicode=true&charcterEncoding=UTF-8";
	private static final String username="root";
	private static final String password="123";
	private static  Connection con=null;
	//��̬����븺���������
	static
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){

		if(con==null){
			try {
				con=DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
}	

