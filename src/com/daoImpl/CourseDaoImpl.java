package com.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.CourseDao;
import com.domain.Course;
import com.utils.JDBCUtils;

public class CourseDaoImpl implements CourseDao {

	@Override
	public Integer findTotal() throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from course";
		Long l = (Long)qr.query(sql, new ScalarHandler());
		return l.intValue();
	}

	@Override
	public List<Course> find(int i,int rows) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select A.*,B.subjectName from course as A,subject as B where A.subjectId = B.id limit ?,?";
		List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class),i,rows);
		return list;
	}

	@Override
	public void add(Course course) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into course(className,term,subjectId) values(?,?,?)";
		qr.update(sql, course.getClassName(),course.getTerm(),course.getSubjectId());
	}

	@Override
	public Integer findTotal_user(int subjectId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select count(*) from course where subjectId=?";
		Long l = (Long)qr.query(sql, new ScalarHandler(),subjectId);
		return l.intValue();
	}

	@Override
	public List<Course> find_user(int i, int rows,int subjectId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select A.*,B.subjectName from course as A,subject as B where A.subjectId = B.id and A.subjectId=? limit ?,?";
		List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class),subjectId,i,rows);
		return list;
	}

	@Override
	public List<Course> findAllCourse() throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT DISTINCT className,id,teachName,term,subjectId from course";
		List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class));
		return list;
	}

	@Override
	public void updateCourse(String courseName,String teachName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="UPDATE course SET teachName=? WHERE className=?";
		qr.update(sql,teachName,courseName);
	}

	@Override
	public Course findByCourseName(String courseName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from course where className=?";
		return qr.query(sql, new BeanHandler<Course>(Course.class),courseName);
	}

	@Override
	public void addTeach(String courseName, StringBuffer sb_teacherName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner();
		String sql="update course set teachName=? where className=?";
		qr.update((Connection)JDBCUtils.getThreadLocal().get(),sql,sb_teacherName.toString(),courseName);
	}

}
