package com.daoImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.AttendDao;
import com.domain.Attendance;
import com.domain.Course;
import com.domain.Sign;
import com.domain.Student;
import com.utils.JDBCUtils;

public class AttendDaoImpl implements AttendDao {

	@Override
	public List<Course> findCourseByTeach(String teachName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT className FROM course WHERE teachName LIKE ?";
		return qr.query(sql, new BeanListHandler<Course>(Course.class),"%"+teachName+"%");
	}

	@Override
	public List<Student> findTeamByCourse(String courseName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT DISTINCT teamName FROM students WHERE subjectId =(SELECT subjectId FROM course WHERE className=?)";
		return qr.query(sql, new BeanListHandler<Student>(Student.class),courseName);
	}

	@Override
	public Integer findAllAttend_total(String teachName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT COUNT(*) FROM attendance WHERE teachName = ?";
		Long l = (Long)qr.query(sql, new ScalarHandler(),teachName);
		return l.intValue();
	}

	@Override
	public List<Attendance> findAllAttend_rows(int i, int rows, String teachName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT * FROM attendance WHERE teachName = ? LIMIT ?,?";
		return qr.query(sql, new BeanListHandler<Attendance>(Attendance.class),teachName,i,rows);
	}

	@Override
	public Integer findAttend_total(String teachName, String courseName, String teamName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT COUNT(*) FROM attendance WHERE teachName = ? AND courseName= ? AND className =?";
		Long l = (Long)qr.query(sql, new ScalarHandler(),teachName,courseName,teamName);
		return l.intValue();
	}

	@Override
	public List<Attendance> findAttend_rows(int i, int rows, String teachName, String courseName, String teamName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT * FROM attendance WHERE teachName = ? AND courseName= ? AND className =? LIMIT ?,?";
		return qr.query(sql,new BeanListHandler<Attendance>(Attendance.class),teachName,courseName,teamName,i,rows);
	}

	@Override
	public List<Student> findStuByTeam(String teamName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT * FROM students WHERE teamName=?";
		return qr.query(sql, new BeanListHandler<Student>(Student.class),teamName);
	}

	@Override
	public void insertSign(Sign sign) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into sign(stuName,className,status,courseName,teachName,stuNum,startTime) values(?,?,?,?,?,?,?)";
		qr.update(sql,sign.getStuName(),sign.getClassName(),sign.getStatus(),sign.getCourseName(),sign.getTeachName(),sign.getStuNum(),sign.getStartTime());
	}

	@Override
	public void stuSign(String stuNum, Date startTime,String status,Date date) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="update sign set status = ?, date=? where startTime=? and stuNum = ?";
		qr.update(sql,status,date,startTime,stuNum);
	}

	@Override
	public Integer showSign_total(String stuNum, String courseName, String teachName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from sign where stuNum=? and courseName=? and teachName=?";
		Long l = (Long) qr.query(sql, new ScalarHandler(),stuNum,courseName,teachName);
		return l.intValue();
	}

	@Override
	public List<Sign> showSign_rows(int i, int rows, String stuNum, String courseName, String teachName) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from sign where stuNum=? and courseName=? and teachName=? limit ?,?";
		return qr.query(sql, new BeanListHandler<Sign>(Sign.class),stuNum,courseName,teachName,i,rows);
	}

	@Override
	public Integer findsumNum(Date startTime) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from sign where startTime=?";
		Long l = (Long)qr.query(sql, new ScalarHandler(),startTime);
		return l.intValue();
	}

	@Override
	public Integer findAttendNum(Date startTime, String statusOne) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from sign where startTime=? and status=?";
		Long l = (Long)qr.query(sql, new ScalarHandler(),startTime,statusOne);
		return l.intValue();
	}

	@Override
	public List<String> findMissName(Date startTime, String statusTwo) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select stuName from sign where startTime =? and status = ?";
		return qr.query(sql, new ColumnListHandler<String>(),startTime,statusTwo);
	}

	@Override
	public void insetAttendance(Attendance attendance) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into attendance(date,courseName,teachName,missNum,attendNum,sumNum,missName,className) values(?,?,?,?,?,?,?,?)";
		qr.update(sql,attendance.getDate(),attendance.getCourseName(),attendance.getTeachName(),attendance.getMissNum(),attendance.getAttendNum(),attendance.getSumNum(),attendance.getMissName(),attendance.getClassName());
	}

	@Override
	public Integer showAttend_xq_total(String attendId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT COUNT(*) FROM SIGN WHERE startTime=(SELECT DATE FROM attendance WHERE id =?)";
		Long l = (Long)qr.query(sql, new ScalarHandler(),attendId);
		return l.intValue();
	}

	@Override
	public List<Sign> showAttend_xq_rows(int i, int rows, String attendId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT * FROM SIGN WHERE startTime=(SELECT DATE FROM attendance WHERE id =?) LIMIT ?,?";
		return qr.query(sql, new BeanListHandler<Sign>(Sign.class),attendId,i,rows);
	}

}
