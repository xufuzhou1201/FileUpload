package com.evan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.evan.entity.User;
import com.evan.util.DBHelper;

/**
 * @author scx
 *	用户服务类
 */
public class UserService {
	private Connection conn = null;
	private PreparedStatement ps = null;
	//检测账户在数据库是否存在
	public boolean checkUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from user where username =? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			rs.last();
			int n = rs.getRow();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	//在数据库中添加注册的用户
	public boolean addUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			conn = DBHelper.getConnection();
			String sql = "INSERT user (username,password) VALUES(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			int x =ps.executeUpdate();
			System.out.println(x);
			if(x>0){
				
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
