package com.qianfeng.automarking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qianfeng.automarking.entity.Student;
import com.qianfeng.automarking.util.ResourceUtil;

public class StudentDao extends BaseDao {

	public boolean add(List<Student> list) {
		Connection conn = getConnection();
		String sql = "insert into student(name,age,phone,classname) values (?,?,?,?)";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			conn.setAutoCommit(false);

			for (int i = 0; i < list.size(); i++) {
				Student s = list.get(i);
				pstm.setString(1, s.getName());
				pstm.setInt(2, s.getAge());
				pstm.setString(3, s.getPhone());
				pstm.setString(4, s.getClassName());
				pstm.addBatch();
			}
			pstm.executeBatch();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return false;
	}

	public List<Student> getList() {

		List<Student> list = new ArrayList<Student>();

		Connection conn = getConnection();
		String sql = "select * from student";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setPhone(rs.getString("phone"));
				s.setErrors(rs.getInt("errors"));
				s.setRights(rs.getInt("rights"));
				s.setClassName(rs.getString("classname"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return list;

	}

	public boolean add(Student student) {
		Connection conn = getConnection();
		String sql = "insert into student(name,age,phone,classname) values (?,?,?,?)";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, student.getName());
			pstm.setInt(2, student.getAge());
			pstm.setString(3, student.getPhone());
			pstm.setString(4, student.getClassName());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return false;
	}

	public boolean checkExits(String name, String className) {
		Connection conn = getConnection();
		String sql = "select count(1) from student where classname=? and name=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, className);
			pstm.setString(2, name);
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

	public boolean del(Student student) {
		Connection conn = getConnection();
		String sql = "delete from student where classname=? and name=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, student.getClassName());
			pstm.setString(2, student.getName());
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

	public boolean udate(Student student) {
		Connection conn = getConnection();
		String sql = "update student set age=?,phont=? where name=? and classname=? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, student.getAge());
			pstm.setString(2, student.getPhone());
			pstm.setString(3, student.getName());
			pstm.setString(4, student.getClassName());
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

	public List<Student> getList(String className) {
		List<Student> list = new ArrayList<Student>();

		Connection conn = getConnection();
		String sql = "select * from student where classname=? order by rights desc,errors asc";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, className);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setPhone(rs.getString("phone"));
				s.setErrors(rs.getInt("errors"));
				s.setRights(rs.getInt("rights"));
				s.setClassName(rs.getString("classname"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceUtil.close(rs, pstm, conn);
		}
		return list;

	}

	public boolean saveRightTime(String name,String className) {
		Connection conn = getConnection();
		String sql = "update student set rights=rights+1 where name=? and classname=? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, className);
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

	public boolean saveWrongTime(String name,String className) {
		Connection conn = getConnection();
		String sql = "update student set errors=errors+1 where name=? and classname=? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, className);
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
