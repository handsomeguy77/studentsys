package com.serviceImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.dao.AttendDao;
import com.daoImpl.AttendDaoImpl;
import com.domain.Attendance;
import com.domain.Course;
import com.domain.Sign;
import com.domain.Student;
import com.service.AttendService;
import com.utils.PageUtils;


public class AttendServiceImp implements AttendService {

	@Override
	public List<Course> findCourseByTeach(String teachName) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		return attendDao.findCourseByTeach(teachName);
	}

	@Override
	public List<Student> findTeamByCourse(String courseName) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		return attendDao.findTeamByCourse(courseName);
	}

	@Override
	public PageUtils<Attendance> findAllAttend(int page, int rows, String teachName) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		PageUtils<Attendance> pb = new PageUtils<Attendance>();
		pb.setTotal(attendDao.findAllAttend_total(teachName));
		pb.setRows(attendDao.findAllAttend_rows((page-1)*rows,rows,teachName));
		return pb;
	}

	@Override
	public PageUtils<Attendance> findAttend(int page, int rows, String teachName, String courseName, String teamName) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		PageUtils<Attendance> pb = new PageUtils<Attendance>();
		pb.setTotal(attendDao.findAttend_total(teachName,courseName,teamName));
		pb.setRows(attendDao.findAttend_rows((page-1)*rows,rows,teachName,courseName,teamName));
		return pb;
	}

	@Override
	public List<Student> findStuByTeam(String teamName) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		return attendDao.findStuByTeam(teamName);
	}

	@Override
	public void insertSign(Sign sign) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		attendDao.insertSign(sign);
	}

	@Override
	public void stuSign(String stuNum, Date startTime,String status,Date date) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		attendDao.stuSign(stuNum,startTime,status,date);
	}

	@Override
	public PageUtils<Sign> showSign(int page, int rows, String stuNum, String courseName, String teachName) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		PageUtils<Sign> pb = new PageUtils<Sign>();
		pb.setTotal(attendDao.showSign_total(stuNum,courseName,teachName));
		pb.setRows(attendDao.showSign_rows((page-1)*rows,rows,stuNum,courseName,teachName));
		return pb;
	}

	@Override
	public Integer findsumNum(Date startTime) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		return attendDao.findsumNum(startTime);
	}

	@Override
	public Integer findAttendNum(Date startTime, String statusOne) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		return attendDao.findAttendNum(startTime,statusOne);
	}

	@Override
	public List<String> findMissName(Date startTime, String statusTwo) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		return attendDao.findMissName(startTime,statusTwo);
	}

	@Override
	public void insetAttendance(Attendance attendance) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		attendDao.insetAttendance(attendance);
	}

	@Override
	public PageUtils<Sign> showAttend_xq(int page, int rows, String attendId) throws SQLException {
		// TODO Auto-generated method stub
		AttendDao attendDao=new AttendDaoImpl();
		PageUtils<Sign> pb = new PageUtils<Sign>();
		pb.setTotal(attendDao.showAttend_xq_total(attendId));
		pb.setRows(attendDao.showAttend_xq_rows((page-1)*rows,rows,attendId));
		return pb;
	}

}
