package com.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.TeachDao;
import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.utils.JDBCUtils;

public class TeachDaoImpl implements TeachDao {

	@Override
	public Teacher login(Teacher teacher) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from teachers where username=? and password=?";
		return qr.query(sql, new BeanHandler<Teacher>(Teacher.class),teacher.getUsername(),teacher.getPassword());
	}

	@Override
	public void register(Teacher teacher) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into teachers(teachName,username,password,zw,teamName,courseName) values(?,?,?,?,?,?)";
		qr.update(sql,teacher.getTeachName(),teacher.getUsername(),teacher.getPassword(),teacher.getZw(),teacher.getTeamName(),teacher.getCourseName());
	}
	
	@Override
	public List<Student> findByTeam(int i, int rows, String teamName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT A.*, B.subjectName FROM students AS A ,SUBJECT AS B WHERE A.teamName=? AND A.subjectId = B.id LIMIT ?,?";
		return qr.query(sql, new BeanListHandler<Student>(Student.class),teamName,i,rows);
	}

	@Override
	public Integer findTotal(String teamName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT COUNT(*) FROM students WHERE teamName=?";
		Long l = (Long)qr.query(sql, new ScalarHandler(),teamName);
		return l.intValue();
	}

	@Override
	public Teacher findById(String teacherId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from teachers where id=?";
		return qr.query(sql, new BeanHandler<Teacher>(Teacher.class),teacherId);
	}

	@Override
	public void addCourse(String teacherId, StringBuffer sb_courseName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner();
		String sql="update teachers set courseName=? where id=?";
		qr.update((Connection)JDBCUtils.getThreadLocal().get(),sql,sb_courseName.toString(),teacherId);
	}

	@Override
	public Integer findAllCourseByTeachName_total(String teachName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT COUNT(*) FROM course WHERE teachName LIKE ?";
		Long l = (Long)qr.query(sql, new ScalarHandler(),'%'+teachName+'%');
		return l.intValue();
	}

	@Override
	public List<Course> findAllCourseByTeachName(int i, int rows, String teachName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT A.*,B.subjectName FROM course AS A,subject AS B WHERE teachName LIKE ? AND A.subjectId=B.id LIMIT ?,?";
		return qr.query(sql, new BeanListHandler<Course>(Course.class),'%'+teachName+'%',i,rows);
	}

}
