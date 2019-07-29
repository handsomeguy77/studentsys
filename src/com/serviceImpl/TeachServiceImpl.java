package com.serviceImpl;


import java.sql.SQLException;

import com.dao.TeachDao;
import com.dao.UserDao;
import com.daoImpl.TeachDaoImpl;
import com.daoImpl.UserDaoImpl;
import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.service.TeachService;
import com.utils.PageUtils;

public class TeachServiceImpl implements TeachService {

	@Override
	public Teacher login(Teacher teacher) throws SQLException {
		TeachDao teachDao=new TeachDaoImpl();
		return teachDao.login(teacher);
	}

	@Override
	public Teacher find(Teacher teacher) throws SQLException {
		// TODO Auto-generated method stub
		TeachDao teachDao=new TeachDaoImpl();
		return teachDao.login(teacher);
	}

	@Override
	public void register(Teacher teacher) throws SQLException {
		// TODO Auto-generated method stub
		TeachDao teachDao=new TeachDaoImpl();
		teachDao.register(teacher);
	}
	
	@Override
	public PageUtils<Student> findByTeam(int page, int rows, String teamName) throws SQLException {
		// TODO Auto-generated method stub
		TeachDao teachDao=new TeachDaoImpl();
		PageUtils<Student> pu = new PageUtils<Student>();
		//查询出总条数s
		pu.setTotal(teachDao.findTotal(teamName));
		pu.setRows(teachDao.findByTeam((page-1)*rows,rows,teamName));
		return pu;
	}

	@Override
	public Teacher findById(String teacherId) throws SQLException {
		// TODO Auto-generated method stub
		TeachDao teachDao=new TeachDaoImpl();
		return teachDao.findById(teacherId);
	}

	@Override
	public void addCourse(String teacherId, StringBuffer sb_courseName) throws SQLException {
		// TODO Auto-generated method stub
		TeachDao teachDao=new TeachDaoImpl();
		teachDao.addCourse(teacherId,sb_courseName);
	}

	@Override
	public PageUtils<Course> findAllCourseByTeachName(int page, int rows, String teachName) throws SQLException {
		// TODO Auto-generated method stub
		TeachDao teachDao=new TeachDaoImpl();
		PageUtils<Course> pu = new PageUtils<Course>();
		pu.setTotal(teachDao.findAllCourseByTeachName_total(teachName));
		pu.setRows(teachDao.findAllCourseByTeachName((page-1)*rows,rows,teachName));
		return pu;
	}

}
