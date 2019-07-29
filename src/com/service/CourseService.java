package com.service;

import java.sql.SQLException;
import java.util.List;

import com.domain.Course;
import com.utils.PageUtils;

public interface CourseService {

	PageUtils<Course> find(int page, int rows) throws SQLException;

	void add(Course course) throws SQLException;

	PageUtils<Course> find_user(int page, int rows, int subjectId) throws SQLException;

	List<Course> findAllCourse() throws SQLException;

	void updateCourse(String courseName,String teachName) throws SQLException;

	Course findByCourseName(String courseName) throws SQLException;

	void addTeach(String courseName, StringBuffer sb_teacherName) throws SQLException;

}
