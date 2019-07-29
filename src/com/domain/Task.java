package com.domain;

import java.util.Date;

public class Task {
	private Integer id;
	private String taskName;
	private Integer className;	//课程id
	private Date date;	//提交日期
	private Integer teachId;	//教师号
	private Date startDate;	//开始日期
	private Date stopDate;	//结束日期
	
	private String courseName; 	//课程名，数据库表中不存在该字段
	private String teachName;	//教师名，数据库表中不存在该字段
	
	
	public String getTeachName() {
		return teachName;
	}
	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Integer getClassName() {
		return className;
	}
	public void setClassName(Integer className) {
		this.className = className;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getTeachId() {
		return teachId;
	}
	public void setTeachId(Integer teachId) {
		this.teachId = teachId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date starDate) {
		this.startDate = starDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", className=" + className + ", date=" + date
				+ ", teachId=" + teachId + ", startDate=" + startDate + ", stopDate=" + stopDate + ", courseName="
				+ courseName + ", teachName=" + teachName + "]";
	}
	
}
