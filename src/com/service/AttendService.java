package com.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.domain.Attendance;
import com.domain.Course;
import com.domain.Sign;
import com.domain.Student;
import com.utils.PageUtils;

public interface AttendService {

	List<Course> findCourseByTeach(String teachName) throws SQLException;

	List<Student> findTeamByCourse(String courseName) throws SQLException;

	PageUtils<Attendance> findAllAttend(int page, int rows, String teachName) throws SQLException;

	PageUtils<Attendance> findAttend(int page, int rows, String teachName, String courseName, String teamName) throws SQLException;

	List<Student> findStuByTeam(String teamName) throws SQLException;

	void insertSign(Sign sign) throws SQLException;

	void stuSign(String stuNum, Date startTime,String status,Date date) throws SQLException;

	PageUtils<Sign> showSign(int page, int rows, String stuNum, String courseName, String teachName) throws SQLException;

	Integer findsumNum(Date startTime) throws SQLException;

	Integer findAttendNum(Date startTime, String statusOne) throws SQLException;

	List<String> findMissName(Date startTime, String statusTwo) throws SQLException;

	void insetAttendance(Attendance attendance) throws SQLException;

	PageUtils<Sign> showAttend_xq(int page, int rows, String attendId) throws SQLException;

}
