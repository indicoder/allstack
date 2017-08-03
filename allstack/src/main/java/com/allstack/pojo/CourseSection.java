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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "coursesections", uniqueConstraints = {
		@UniqueConstraint(columnNames = "extSectionId") })
public class CourseSection {
	private int courseSectionId; 
	private String sectionName;
	private Course course;
	private String extSectionId;
	private List<LessonPage> lessonPages = new ArrayList<LessonPage>();
	//private ArrayList<QuizCollection> quizCollection = new ArrayList<QuizCollection>();
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "courseSectionId", unique = true, nullable = false)
	public int getCourseSectionId() {
		return courseSectionId;
	}
	public void setCourseSectionId(int courseSectionId) {
		this.courseSectionId = courseSectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	/*public ArrayList<LessonPage> getLessonPages() {
		return lessonPages;
	}
	public void setLessonPages(ArrayList<LessonPage> lessonPages) {
		this.lessonPages = lessonPages;
	}
	public ArrayList<QuizCollection> getQuizCollection() {
		return quizCollection;
	}
	public void setQuizCollection(ArrayList<QuizCollection> quizCollection) {
		this.quizCollection = quizCollection;
	}*/
	@JsonBackReference
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
	public List<LessonPage> getLessonPages() {
		return lessonPages;
	}
	public void setLessonPages(List<LessonPage> lessonPages) {
		this.lessonPages = lessonPages;
	}
}
