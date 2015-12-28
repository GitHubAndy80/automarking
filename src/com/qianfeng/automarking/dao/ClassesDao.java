package com.qianfeng.automarking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qianfeng.automarking.entity.Classes;
import com.qianfeng.automarking.util.ResourceUtil;

public class ClassesDao extends BaseDao {
	public boolean add(Classes classes) {
		Connection conn = getConnection();
		String sql = "insert into classes(classname,introduce) values (?,?)";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, classes.getClassName());
			pstm.setString(2, classes.getIntroduce());
			int result = pstm.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return false;
	}
	
	public boolean checkExits(String className) {
		Connection conn = getConnection();
		String sql = "select count(1) from classes where classname=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, className);
			rs = pstm.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return false;
	}

	public List<Classes> getListByUserName(String username) {
		List<Classes> list = new ArrayList<Classes>();
		
		Connection conn = getConnection();
		String sql = "select * from classes where username=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Classes c = new Classes();
				c.setId(rs.getInt("id"));
				c.setClassName(rs.getString("classname"));
				c.setUserName(rs.getString("username"));
				c.setIntroduce(rs.getString("introduce"));
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	public List<Classes> getList() {
		List<Classes> list = new ArrayList<Classes>();
		
		Connection conn = getConnection();
		String sql = "select * from classes";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Classes c = new Classes();
				c.setId(rs.getInt("id"));
				c.setClassName(rs.getString("classname"));
				c.setIntroduce(rs.getString("introduce"));
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return list;
	}

	public boolean checkExits(String className, String username) {
		Connection conn = getConnection();
		String sql = "select count(1) from classes where classname=? and username=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, className);
			pstm.setString(2, username);
			rs = pstm.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return false;
	}
	
	

	public boolean del(String className, String username) {
		Connection conn = getConnection();
		String sql = "delete from classes where classname=? and username=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, className);
			pstm.setString(2, username);
			int result = pstm.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return false;
	}
	
	public boolean del(String className) {
		Connection conn = getConnection();
		String sql = "delete from classes where classname=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, className);
			int result = pstm.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return false;
	}

	public boolean update(Classes classes) {
		Connection conn = getConnection();
		String sql = "update classes set introduce=? where classname=? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, classes.getIntroduce());
			pstm.setString(2, classes.getClassName());
			int result = pstm.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return false;
	}
}
