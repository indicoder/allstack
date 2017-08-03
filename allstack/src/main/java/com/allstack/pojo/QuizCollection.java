package com.allstack.pojo;

import java.util.ArrayList;

public class QuizCollection {
	private int quizCollectionId;
	private String extQuizCollectionId;
	private String quizCollectionName;
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	public int getQuizCollectionId() {
		return quizCollectionId;
	}
	public void setQuizCollectionId(int quizCollectionId) {
		this.quizCollectionId = quizCollectionId;
	}
	public String getExtQuizCollectionId() {
		return extQuizCollectionId;
	}
	public void setExtQuizCollectionId(String extQuizCollectionId) {
		this.extQuizCollectionId = extQuizCollectionId;
	}
	public String getQuizCollectionName() {
		return quizCollectionName;
	}
	public void setQuizCollectionName(String quizCollectionName) {
		this.quizCollectionName = quizCollectionName;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
}
