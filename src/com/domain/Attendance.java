package com.domain;

import java.util.Date;

public class Attendance {
	private Integer id;
	private Date date;	//考勤日期
	private String courseName;	//	课程id
	private String teachName;	//教师id
	private Integer missNum;	//缺勤人数
	private Integer attendNum;	//到课人数
	private Integer sumNum;		//总人数
	private String missName;	//缺勤学生姓名
	
	private String className;	//班级名
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getMissNum() {
		return missNum;
	}
	public void setMissNum(Integer missNum) {
		this.missNum = missNum;
	}
	public Integer getAttendNum() {
		return attendNum;
	}
	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}
	public Integer getSumNum() {
		return sumNum;
	}
	public void setSumNum(Integer sumNum) {
		this.sumNum = sumNum;
	}
	public String getMissName() {
		return missName;
	}
	public void setMissName(String missName) {
		this.missName = missName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeachName() {
		return teachName;
	}
	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "Attendance [id=" + id + ", date=" + date + ", courseName=" + courseName + ", teachName=" + teachName
				+ ", missNum=" + missNum + ", attendNum=" + attendNum + ", sumNum=" + sumNum + ", missName=" + missName
				+ ", className=" + className + "]";
	}
	
	
}
