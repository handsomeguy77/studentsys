package com.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.domain.Attendance;
import com.domain.Course;
import com.domain.Sign;
import com.domain.Student;

public interface AttendDao {

	List<Course> findCourseByTeach(String teachName) throws SQLException;

	List<Student> findTeamByCourse(String courseName) throws SQLException;

	Integer findAllAttend_total(String teachName) throws SQLException;

	List<Attendance> findAllAttend_rows(int i, int rows, String teachName) throws SQLException;

	Integer findAttend_total(String teachName, String courseName, String teamName) throws SQLException;

	List<Attendance> findAttend_rows(int i, int rows, String teachName, String courseName, String teamName) throws SQLException;

	List<Student> findStuByTeam(String teamName) throws SQLException;

	void insertSign(Sign sign) throws SQLException;

	void stuSign(String stuNum, Date startTime,String status,Date date) throws SQLException;

	Integer showSign_total(String stuNum, String courseName, String teachName) throws SQLException;

	List<Sign> showSign_rows(int i, int rows, String stuNum, String courseName, String teachName) throws SQLException;

	Integer findsumNum(Date startTime) throws SQLException;

	Integer findAttendNum(Date startTime, String statusOne) throws SQLException;

	List<String> findMissName(Date startTime, String statusTwo) throws SQLException;

	void insetAttendance(Attendance attendance) throws SQLException;

	Integer showAttend_xq_total(String attendId) throws SQLException;

	List<Sign> showAttend_xq_rows(int i, int rows, String attendId) throws SQLException;

}
