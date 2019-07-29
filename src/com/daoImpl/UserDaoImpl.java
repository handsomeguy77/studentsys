package com.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.UserDao;
import com.domain.Student;
import com.domain.Subject;
import com.domain.Task;
import com.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public Student login(Student student) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from students where username=? and password=?";
		return qr.query(sql, new BeanHandler<Student>(Student.class),student.getUsername(),student.getPassword());
	}
	
	@Override
	public List<Subject> find_subject() throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from subject";
		return qr.query(sql, new BeanListHandler<Subject>(Subject.class));
	}

	@Override
	public void registerStu(Student student) throws SQLException {
		// TODO Auto-generated method stubSt
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into students(stuName,gender,username,password,age,stuNum,teamName,subjectId) values(?,?,?,?,?,?,?,?)";
		qr.update(sql,student.getStuName(),student.getGender(),student.getUsername(),student.getPassword(),student.getAge(),student.getStuNum(),student.getTeamName(),student.getSubjectId());
	}

	@Override
	public Student find(Student student) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from students where username=? and password=?";
		return qr.query(sql, new BeanHandler<Student>(Student.class),student.getUsername(),student.getPassword());
	}

	@Override
	public void update_pwd(Student student) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="update students set password=? where username=?";
		qr.update(sql,student.getPassword(),student.getUsername());
	}

	@Override
	public void update(Student student) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="update students set stuName=?,gender=?,username=?,age=?,stuNum=?,teamName=?,subjectId=? where id=?";
		qr.update(sql,student.getStuName(),student.getGender(),student.getUsername(),student.getAge(),student.getStuNum(),student.getTeamName(),student.getSubjectId(),student.getId());
	}

	//通过专业id查询出专业名
	@Override
	public String find_subjectById(Integer subjectId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select subjectName from subject where id=?";
		return (String)qr.query(sql, new ScalarHandler(),subjectId);
	}

	@Override
	public Student findStudent(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from students where id=?";
		return qr.query(sql, new BeanHandler<Student>(Student.class),id);
	}

	@Override
	public List<Student> find_teamName() throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT DISTINCT teamName FROM students";
		List<Student> list = qr.query(sql, new BeanListHandler<Student>(Student.class));
		return list;
	}

	@Override
	public Integer findTask_total(int userId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT count(*) "
				+ "FROM task t,course c,teachers teach "
				+ "WHERE t.id IN (SELECT taskid FROM stu_task WHERE stuid = ?) "
				+ "AND t.className=c.id "
				+ "AND t.teachId=teach.id";
		Long l = qr.query(sql, new ScalarHandler<>(),userId);
		return l.intValue();
	}

	@Override
	public List<Task> finTask_rows(int i, int rows, int userId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT t.*,c.className AS courseName,teach.teachName AS teachName "
				+ "FROM task t,course c,teachers teach "
				+ "WHERE t.id IN (SELECT taskid FROM stu_task WHERE stuid = ?) "
				+ "AND t.className=c.id "
				+ "AND t.teachId=teach.id "
				+ "LIMIT ?,?";
		return qr.query(sql, new BeanListHandler<Task>(Task.class),userId,i,rows);
	}

	
}
