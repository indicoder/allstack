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

import com.allstack.pojo.serializers.QuizCollectionDeserializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = QuizCollectionDeserializer.class)
@Entity
@Table(name = "QuizCollections", uniqueConstraints = {
		@UniqueConstraint(columnNames = "extQuizCollectionId") })
public class QuizCollection {
	private int quizCollectionId;
	private String extQuizCollectionId;
	private String quizCollectionName;
	private Integer isDefault = 0;
	private CourseSection courseSection;
	private List<Question> questions = new ArrayList<Question>();
	
	public QuizCollection(){}
	
	public QuizCollection(Integer quizCollectionId){
		this.quizCollectionId = quizCollectionId;
	}
	
	public QuizCollection(int quizCollectionId, String extQuizCollectionId, String quizCollectionName, Integer isDefault,
			CourseSection courseSection) {
		this.quizCollectionId = quizCollectionId;
		this.extQuizCollectionId = extQuizCollectionId;
		this.quizCollectionName = quizCollectionName;
		this.isDefault = isDefault;
		this.courseSection = courseSection;
	}

	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "quizCollectionId", unique = true, nullable = false)
	public int getQuizCollectionId() {
		return quizCollectionId;
	}
	public void setQuizCollectionId(int quizCollectionId) {
		this.quizCollectionId = quizCollectionId;
	}
	@Column(name = "extQuizCollectionId", unique = true)
	public String getExtQuizCollectionId() {
		return extQuizCollectionId;
	}
	public void setExtQuizCollectionId(String extQuizCollectionId) {
		this.extQuizCollectionId = extQuizCollectionId;
	}
	@Column(name = "quizCollectionName", nullable = false)
	public String getQuizCollectionName() {
		return quizCollectionName;
	}
	public void setQuizCollectionName(String quizCollectionName) {
		this.quizCollectionName = quizCollectionName;
	}
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "quizCollection")
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
    //@JsonBackReference
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="courseSectionId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="courseSectionId")
	public CourseSection getCourseSection() {
		return courseSection;
	}
	public void setCourseSection(CourseSection courseSection) {
		this.courseSection = courseSection;
	}
}
