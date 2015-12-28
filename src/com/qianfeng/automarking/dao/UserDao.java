package com.qianfeng.automarking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qianfeng.automarking.entity.User;
import com.qianfeng.automarking.util.ResourceUtil;

public class UserDao extends BaseDao {

	public boolean checkExits(String username) {
		Connection conn = getConnection();
		String sql = "select count(1) from user where username=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
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

	public boolean add(User user) {
		Connection conn = getConnection();
		String sql = "insert into user(username,password) values (?,?)";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
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

	public boolean checkExits(User user) {
		Connection conn = getConnection();
		String sql = "select count(1) from user where username=? and password=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
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

}
