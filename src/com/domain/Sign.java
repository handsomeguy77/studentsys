package com.domain;

import java.util.Date;

//ǩ����
public class Sign {
	private Integer id;
	private String stuName;	//ѧ����
	private String className;	//�༶��
	private Date date;	//ǩ��ʱ�� 
	private String status;	//״̬:  ���ڡ�ȱ�ڡ����
	private String courseName;	//�γ���
	private String teachName;	//��ʦ��
	private String stuNum;	//ѧ��ѧ��
	private Date startTime;	//��ʼʱ��
	
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "Sign [id=" + id + ", stuName=" + stuName + ", className=" + className + ", date=" + date + ", status="
				+ status + ", courseName=" + courseName + ", teachName=" + teachName + ", stuNum=" + stuNum
				+ ", startTime=" + startTime + "]";
	}
	
	
}
