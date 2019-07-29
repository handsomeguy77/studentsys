package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.domain.Student;
import com.domain.Subject;
import com.domain.Task;

public interface UserDao {

	Student login(Student student) throws SQLException;

	void registerStu(Student student) throws SQLException;

	Student find(Student student) throws SQLException;

	void update_pwd(Student student) throws SQLException;

	void update(Student student) throws SQLException;

	List<Subject> find_subject() throws SQLException;

	String find_subjectById(Integer subjectId) throws SQLException;

	Student findStudent(Integer id) throws SQLException;

	List<Student> find_teamName() throws SQLException;

	Integer findTask_total(int userId) throws SQLException;

	List<Task> finTask_rows(int i, int rows, int userId) throws SQLException;


}
