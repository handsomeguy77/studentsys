package com.service;

import java.sql.SQLException;
import java.util.List;

import com.domain.Student;
import com.domain.Task;
import com.utils.PageUtils;

public interface TaskService {

	int addTask(Task task) throws SQLException;

	List<Student> findAllStu(int className) throws SQLException;

	void insertTask_stu(Integer id, int taskId) throws SQLException;

	PageUtils<Task> findAllTask(int page, int rows, int teachId) throws SQLException;

}
