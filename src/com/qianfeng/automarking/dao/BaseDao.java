package com.qianfeng.automarking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库操作的基础类
 * @author HuangGuiZhao
 *
 */
public class BaseDao {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/auto_marking?user=root&password=root";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
