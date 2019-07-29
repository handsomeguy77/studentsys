package com.service;

import java.sql.SQLException;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.utils.PageUtils;

public interface TeachService {

	Teacher login(Teacher teacher) throws SQLException;

	Teacher find(Teacher teacher) throws SQLException;

	void register(Teacher teacher) throws SQLException;

	PageUtils<Student> findByTeam(int page, int rows, String parameter) throws SQLException;

	Teacher findById(String teacherId) throws SQLException;

	void addCourse(String teacherId, StringBuffer sb_courseName) throws SQLException;

	PageUtils<Course> findAllCourseByTeachName(int page, int rows, String teachName) throws SQLException;
}
