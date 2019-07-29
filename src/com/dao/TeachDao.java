package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;

public interface TeachDao {

	Teacher login(Teacher teacher) throws SQLException;

	void register(Teacher teacher) throws SQLException;

	List<Student> findByTeam(int i, int rows, String teamName) throws SQLException;

	Integer findTotal(String teamName) throws SQLException;

	Teacher findById(String teacherId) throws SQLException;

	void addCourse(String teacherId, StringBuffer sb_courseName) throws SQLException;

	Integer findAllCourseByTeachName_total(String teachName) throws SQLException;

	List<Course> findAllCourseByTeachName(int i, int rows, String teachName) throws SQLException;
}
