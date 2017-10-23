package com.allstack.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.allstack.common.EnumCollection;
import com.allstack.pojo.serializers.QuestionDeserializer;
import com.allstack.pojo.serializers.QuizCollectionDeserializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = QuestionDeserializer.class)
@Entity
@Table(name = "Questions", uniqueConstraints = {
		@UniqueConstraint(columnNames = "extQuestionId") })
public class Question {
	private int questionId;
	private String extQuestionId;
	//private EnumCollection.QuestionType questionType;
	private int questionType;
	private QuizCollection quizCollection;
	private String quizQuestionHTML;
	private String choice1HTML;
	private String choice2HTML;
	private String choice3HTML;
	private String choice4HTML;
	private Integer isChoice1Correct;
	private Integer isChoice2Correct;
	private Integer isChoice3Correct;
	private Integer isChoice4Correct;
	private String answerHTML;
	private String answerHintHTML;
	private String answerExplanationHTML;
	private Integer pointsForQuestion;
	
	public Question(){}
	
	public Question(Integer questionId, String extQuestionId, int questionType, QuizCollection quizCollection,
			String quizQuestionHTML, String choice1html, String choice2html, String choice3html, String choice4html,
			Integer isChoice1Correct, Integer isChoice2Correct, Integer isChoice3Correct, Integer isChoice4Correct, String answerHTML,
			String answerHintHTML, String answerExplanationHTML, Integer pointsForQuestion) {
		this.questionId = questionId;
		this.extQuestionId = extQuestionId;
		this.questionType = questionType;
		this.quizCollection = quizCollection;
		this.quizQuestionHTML = quizQuestionHTML;
		choice1HTML = choice1html;
		choice2HTML = choice2html;
		choice3HTML = choice3html;
		choice4HTML = choice4html;
		this.isChoice1Correct = isChoice1Correct;
		this.isChoice2Correct = isChoice2Correct;
		this.isChoice3Correct = isChoice3Correct;
		this.isChoice4Correct = isChoice4Correct;
		this.answerHTML = answerHTML;
		this.answerHintHTML = answerHintHTML;
		this.answerExplanationHTML = answerExplanationHTML;
		this.pointsForQuestion = pointsForQuestion;
	}

	public Question(int questionId, QuizCollection quizCollection, String quizQuestionHTML, Integer pointsForQuestion) {
		this.questionId = questionId;
		this.quizCollection = quizCollection;
		this.quizQuestionHTML = quizQuestionHTML;
		this.pointsForQuestion = pointsForQuestion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "questionId", unique = true, nullable = false)
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getExtQuestionId() {
		return extQuestionId;
	}
	public void setExtQuestionId(String extQuestionId) {
		this.extQuestionId = extQuestionId;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
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
	public String getQuizQuestionHTML() {
		return quizQuestionHTML;
	}
	public void setQuizQuestionHTML(String quizQuestionHTML) {
		this.quizQuestionHTML = quizQuestionHTML;
	}
	public String getChoice1HTML() {
		return choice1HTML;
	}
	public void setChoice1HTML(String choice1html) {
		choice1HTML = choice1html;
	}
	public String getChoice2HTML() {
		return choice2HTML;
	}
	public void setChoice2HTML(String choice2html) {
		choice2HTML = choice2html;
	}
	public String getChoice3HTML() {
		return choice3HTML;
	}
	public void setChoice3HTML(String choice3html) {
		choice3HTML = choice3html;
	}
	public String getChoice4HTML() {
		return choice4HTML;
	}
	public void setChoice4HTML(String choice4html) {
		choice4HTML = choice4html;
	}
	public Integer getIsChoice1Correct() {
		return isChoice1Correct;
	}
	public void setIsChoice1Correct(Integer isChoice1Correct) {
		this.isChoice1Correct = isChoice1Correct;
	}
	public Integer getIsChoice2Correct() {
		return isChoice2Correct;
	}
	public void setIsChoice2Correct(Integer isChoice2Correct) {
		this.isChoice2Correct = isChoice2Correct;
	}
	public Integer getIsChoice3Correct() {
		return isChoice3Correct;
	}
	public void setIsChoice3Correct(Integer isChoice3Correct) {
		this.isChoice3Correct = isChoice3Correct;
	}
	public Integer getIsChoice4Correct() {
		return isChoice4Correct;
	}
	public void setIsChoice4Correct(Integer isChoice4Correct) {
		this.isChoice4Correct = isChoice4Correct;
	}
	public String getAnswerHTML() {
		return answerHTML;
	}
	public void setAnswerHTML(String answerHTML) {
		this.answerHTML = answerHTML;
	}
	public Integer getPointsForQuestion() {
		return pointsForQuestion;
	}
	public void setPointsForQuestion(Integer pointsForQuestion) {
		this.pointsForQuestion = pointsForQuestion;
	}

	public String getAnswerExplanationHTML() {
		return answerExplanationHTML;
	}

	public void setAnswerExplanationHTML(String answerExplanationHTML) {
		this.answerExplanationHTML = answerExplanationHTML;
	}

	public String getAnswerHintHTML() {
		return answerHintHTML;
	}

	public void setAnswerHintHTML(String answerHintHTML) {
		this.answerHintHTML = answerHintHTML;
	}
}
