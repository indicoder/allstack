package com.allstack.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "courses", uniqueConstraints = {
		@UniqueConstraint(columnNames = "extCourseId") })
public class Course {
	private int courseId;
	private String extCourseId;
	private String courseName;
	private List<CourseSection> courseSections = new ArrayList<CourseSection>();
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "courseId", unique = true, nullable = false)
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	@Column(name = "courseName")
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	public List<CourseSection> getCourseSections() {
		return courseSections;
	}
	public void setCourseSections(List<CourseSection> courseSections) {
		this.courseSections = courseSections;
	}	
	
	@Column(name = "extCourseId", unique = true)
	public String getExtCourseId() {
		return extCourseId;
	}
	public void setExtCourseId(String extCourseId) {
		this.extCourseId = extCourseId;
	}	
}
