package com.domain;

import java.util.Date;

public class Task {
	private Integer id;
	private String taskName;
	private Integer className;	//�γ�id
	private Date date;	//�ύ����
	private Integer teachId;	//��ʦ��
	private Date startDate;	//��ʼ����
	private Date stopDate;	//��������
	
	private String courseName; 	//�γ��������ݿ���в����ڸ��ֶ�
	private String teachName;	//��ʦ�������ݿ���в����ڸ��ֶ�
	
	
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
