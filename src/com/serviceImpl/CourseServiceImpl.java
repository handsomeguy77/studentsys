package com.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.dao.CourseDao;
import com.daoImpl.CourseDaoImpl;
import com.domain.Course;
import com.service.CourseService;
import com.utils.PageUtils;

public class CourseServiceImpl implements CourseService {

	@Override
	public PageUtils<Course> find(int page,int rows) throws SQLException {
		// TODO Auto-generated method stub
		CourseDao courseDao=new CourseDaoImpl();
		PageUtils<Course> pu = new PageUtils<Course>();
		pu.setTotal(courseDao.findTotal());
		pu.setRows(courseDao.find((page-1)*rows,rows));
		return pu;
	}

	@Override
	public void add(Course course) throws SQLException {
		// TODO Auto-generated method stub
		CourseDao courseDao=new CourseDaoImpl();
		courseDao.add(course);
	}

	@Override
	public PageUtils<Course> find_user(int page, int rows, int subjectId) throws SQLException {
		// TODO Auto-generated method stub
		CourseDao courseDao=new CourseDaoImpl();
		PageUtils<Course> pu = new PageUtils<Course>();
		pu.setTotal(courseDao.findTotal_user(subjectId));
		pu.setRows(courseDao.find_user((page-1)*rows,rows,subjectId));
		return pu;
	}

	@Override
	public List<Course> findAllCourse() throws SQLException {
		// TODO Auto-generated method stub
		CourseDao courseDao=new CourseDaoImpl();
		return courseDao.findAllCourse();
	}

	@Override
	public void updateCourse(String courseName,String teachName) throws SQLException {
		// TODO Auto-generated method stub
		CourseDao courseDao=new CourseDaoImpl();
		courseDao.updateCourse(courseName,teachName);
	}

	@Override
	public Course findByCourseName(String courseName) throws SQLException {
		// TODO Auto-generated method stub
		CourseDao courseDao=new CourseDaoImpl();
		return courseDao.findByCourseName(courseName);
	}

	@Override
	public void addTeach(String courseName, StringBuffer sb_teacherName) throws SQLException {
		// TODO Auto-generated method stub
		CourseDao courseDao=new CourseDaoImpl();
		courseDao.addTeach(courseName,sb_teacherName);
	}

}
