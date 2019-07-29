package com.domain;

public class Teacher {
	private Integer id;
	private String teachName;
	private String username;
	private String password;
	private String zw;
	private String teamName;
	private String courseName;
	
	
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTeachName() {
		return teachName;
	}
	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", teachName=" + teachName + ", username=" + username + ", password=" + password
				+ ", zw=" + zw + ", teamName=" + teamName + ", courseName=" + courseName + "]";
	}
	
}
