package com.evan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.evan.entity.File;
import com.evan.util.DBHelper;

/**
 * @author scx
 * 文件服务类
 */
public class FileService {
	//上传文件
	public boolean uploadFile(File file) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DBHelper.getConnection();
		String sql = "INSERT file (username,filename,filecontent) VALUES(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, file.getUsername().trim());
			ps.setString(2, file.getFname().trim());
			ps.setBytes(3, file.getFcontent());
			int n=ps.executeUpdate();
			System.out.println("上传成功！");
			if(n>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)
					conn.close();
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
