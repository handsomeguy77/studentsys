
package com.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.domain.Student;
import com.domain.Subject;
import com.domain.Task;
import com.service.UserService;
import com.utils.PageUtils;

public class UserServiceImpl implements UserService {

	@Override
	public Student login(Student student) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		return userDao.login(student); 
	}
	
	@Override
	public List<Subject> find_subject() throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		return userDao.find_subject();
	}

	@Override
	public void registerStu(Student student) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		userDao.registerStu(student); 
	}

	@Override
	public Student find(Student student) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		return userDao.find(student);
	}

	@Override
	public void update_pwd(Student student) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		userDao.update_pwd(student);
	}

	@Override
	public void update(Student student) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		userDao.update(student);
	}

	//通过subjectId获取专业名
	@Override
	public String find_subjectById(Integer subjectId) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		return userDao.find_subjectById(subjectId);
	}

	//通过id查找该学生
	@Override
	public Student findStudent(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		return userDao.findStudent(id);
	}

	@Override
	public List<Student> find_teamName() throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		return userDao.find_teamName();
	}

	@Override
	public PageUtils<Task> findTask(int page, int rows, int userId) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImpl();
		PageUtils<Task> pb=new PageUtils<Task>();
		pb.setTotal(userDao.findTask_total(userId));
		pb.setRows(userDao.finTask_rows((page-1)*rows,rows,userId));
		return pb;
	}

	

	

	

}
