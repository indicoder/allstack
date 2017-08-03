package com.allstack.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "LessonPages", uniqueConstraints = {
		@UniqueConstraint(columnNames = "extPageId") })
public class LessonPage {
	private int pageId;
	private String pageName;
	private String extPageId;
	private CourseSection section;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="courseSectionId")
	public CourseSection getSection() {
		return section;
	}
	public void setSection(CourseSection section) {
		this.section = section;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pageId", unique = true, nullable = false)
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	@Column(name = "extPageId", unique = true)
	public String getExtPageId() {
		return extPageId;
	}
	public void setExtPageId(String extPageId) {
		this.extPageId = extPageId;
	}
}
