package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.domain.Course;

public interface CourseDao {

	Integer findTotal() throws SQLException;

	List<Course> find(int i,int rows) throws SQLException;

	void add(Course course) throws SQLException;

	Integer findTotal_user(int subjectId) throws SQLException;

	List<Course> find_user(int i, int rows,int subjectId) throws SQLException;

	List<Course> findAllCourse() throws SQLException;

	void updateCourse(String courseName,String teachName) throws SQLException;

	Course findByCourseName(String courseName) throws SQLException;

	void addTeach(String courseName, StringBuffer sb_teacherName) throws SQLException;


}
