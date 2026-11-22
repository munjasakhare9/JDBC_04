package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.demo.model.Student;
import com.demo.util.MyDatabase;

public class StudentDao {
	public int insertStudent(Student s) {
		int check = 0;
		String sql = "insert into Student(name, city, percentage) values (?,?,?)";
		try (Connection con = MyDatabase.createConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, s.getName());
			pst.setString(2, s.getCity());
			pst.setDouble(3, s.getPercentage());
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	public Student getStudentById(int id) {
		String sql = "select id, name, city, percentage from student where id=?";
		Student s = null;
		try (Connection con = MyDatabase.createConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			List<Student> list = MyDatabase.studentRowMapper(rs);
			if (!list.isEmpty()) {
				s = list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	public int deleteStudentById(int id) {
		int check = 0;
		String sql = "delete from student where id=?";
		try (Connection con = MyDatabase.createConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, id);
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	public List<Student> getAllStudent() {
		String sql = "select id, name, city, percentage from student";
		List<Student> list = null;
		try (Connection con = MyDatabase.createConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			list = MyDatabase.studentRowMapper(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateStudent(Student s) {
		int check = 0;
		String sql = "update student set name=?, city=?, percentage=? where id=?";
		try (Connection con = MyDatabase.createConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, s.getName());
			pst.setString(2, s.getCity());
			pst.setDouble(3, s.getPercentage());
			pst.setInt(4, s.getId());
			check = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
}
