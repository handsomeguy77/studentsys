package com.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.dao.TaskDao;
import com.daoImpl.TaskDaoImpl;
import com.domain.Student;
import com.domain.Task;
import com.service.TaskService;
import com.utils.PageUtils;

public class TaskServiceImpl implements TaskService {

	@Override
	public int addTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		TaskDao taskDao =new TaskDaoImpl();
		return taskDao.addTask(task);
	}

	@Override
	public List<Student> findAllStu(int className) throws SQLException {
		// TODO Auto-generated method stub
		TaskDao taskDao =new TaskDaoImpl();
		return taskDao.findAllStu(className);
	}

	@Override
	public void insertTask_stu(Integer stuId, int taskId) throws SQLException {
		// TODO Auto-generated method stub
		TaskDao taskDao =new TaskDaoImpl();
		taskDao.insertTask_stu(stuId,taskId);
	}

	@Override
	public PageUtils<Task> findAllTask(int page, int rows, int teachId) throws SQLException {
		TaskDao taskDao =new TaskDaoImpl();
		PageUtils<Task> pb = new PageUtils<Task>();
		pb.setTotal(taskDao.findAllTask_total(teachId));
		pb.setRows(taskDao.findAllTask_rows((page-1)*rows, rows, teachId));
		return pb;
	}

}
