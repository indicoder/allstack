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

import com.allstack.pojo.serializers.LessonPageDeserializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = LessonPageDeserializer.class)
@Entity
@Table(name = "LessonPages", uniqueConstraints = {
		@UniqueConstraint(columnNames = "extPageId") })
public class LessonPage {
	private int pageId;
	private String pageName;
	private String extPageId;
	private CourseSection courseSection;
	private int contentType;
	private String contentHTML;
	private QuizCollection quizCollection;
	
	//public LessonPage(int pageId2, String pageName2, String extPageId2, String contentHTML, CourseSection courseSection2){}
	public LessonPage(){}
	
	public LessonPage(int pageId, String pageName, String extPageId, String contentHTML, CourseSection courseSection, QuizCollection quizCollection) {
		this.pageId = pageId;
		this.pageName = pageName;
		this.extPageId = extPageId;
		this.courseSection = courseSection;
		this.contentHTML = contentHTML;
		this.quizCollection = quizCollection;
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
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="courseSectionId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="courseSectionId")
	public CourseSection getCourseSection() {
		return courseSection;
	}
	public void setCourseSection(CourseSection courseSection) {
		this.courseSection = courseSection;
	}
	public String getContentHTML() {
		return contentHTML;
	}

	public void setContentHTML(String contentHTML) {
		this.contentHTML = contentHTML;
	}

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="quizCollectionId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="quizCollectionId")
	public QuizCollection getQuizCollection() {
		return quizCollection;
	}

	public void setQuizCollection(QuizCollection quizCollection) {
		this.quizCollection = quizCollection;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
}
