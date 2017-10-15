package com.allstack.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "CourseSections", uniqueConstraints = {
		@UniqueConstraint(columnNames = "extSectionId") })
public class CourseSection {
	private Integer courseSectionId; 
	private String sectionName;
	private Course course;
	private String extSectionId;
	private List<LessonPage> lessonPages = new ArrayList<LessonPage>();
	private List<QuizCollection> quizCollections = new ArrayList<QuizCollection>();
	
	public CourseSection(){}
	
	public CourseSection(Integer courseSectionId){
		this.courseSectionId = courseSectionId;
	}
	
	public CourseSection(Integer courseSectionId, String sectionName, Course course, String extSectionId,
			List<LessonPage> lessonPages, List<QuizCollection> quizCollections) {
		super();
		this.courseSectionId = courseSectionId;
		this.sectionName = sectionName;
		this.course = course;
		this.extSectionId = extSectionId;
		this.lessonPages = lessonPages;
		this.quizCollections = quizCollections;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "courseSectionId", unique = true, nullable = false)
	public Integer getCourseSectionId() {
		return courseSectionId;
	}
	public void setCourseSectionId(Integer courseSectionId) {
		this.courseSectionId = courseSectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	//@JsonBackReference
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="courseId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="courseId")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Column(name = "extSectionId", unique = true)
	public String getExtSectionId() {
		return extSectionId;
	}
	public void setExtSectionId(String extSectionId) {
		this.extSectionId = extSectionId;
	}	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "courseSection")
	public List<LessonPage> getLessonPages() {
		return lessonPages;
	}
	public void setLessonPages(List<LessonPage> lessonPages) {
		this.lessonPages = lessonPages;
	}
	//@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "courseSection")
	public List<QuizCollection> getQuizCollections() {
		return quizCollections;
	}
	public void setQuizCollections(List<QuizCollection> quizCollections) {
		this.quizCollections = quizCollections;
	}
}
