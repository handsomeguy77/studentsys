package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.domain.Student;
import com.domain.Task;

public interface TaskDao {

	int addTask(Task task) throws SQLException;

	List<Student> findAllStu(int className) throws SQLException;

	void insertTask_stu(Integer stuId, int taskId) throws SQLException;

	Integer findAllTask_total(int teachId) throws SQLException;

	List<Task> findAllTask_rows(int i,int rows,int teachId) throws SQLException;

}
