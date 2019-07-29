package com.domain;

import java.util.List;

public class Course {
	private Integer id;
	private String className;
	private String teachName;
	private String term;
	private Integer subjectId;
	private String subjectName;
	
	//存放所有的教师名
	private String[] teachs;
	
	
	public String[] getTeachs() {
		return teachs;
	}
	public void setTeachs(String[] teachs) {
		this.teachs = teachs;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTeachName() {
		return teachName;
	}
	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", className=" + className + ", teachName=" + teachName + ", term=" + term
				+ ", subjectId=" + subjectId + ", subjectName=" + subjectName + "]";
	}
	
	
}
