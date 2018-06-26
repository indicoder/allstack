package com.allstack.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.allstack.pojo.serializers.UserDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = UserDeserializer.class)
@Entity
@Table(name = "Users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "userHandle") })
public class User {
	private int userId;
	private String userHandle;
	//private Course course;
	private Integer courseId;
	private String fullName;
	private String email;
	private String mobile;
	
	public User(){}
	
	public User(int userId, String userHandle, Integer courseId, String fullName, String email, String mobile) {
		this.userId = userId;
		this.userHandle = userHandle;
		this.courseId = courseId;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserHandle() {
		return userHandle;
	}
	public void setUserHandle(String userHandle) {
		this.userHandle = userHandle;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
}
