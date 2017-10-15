package com.allstack.pojo;

public class QuestionType {
	//SingleChoice(1), MultipleChoice(2), CodeoutPut(3), FillTheBlanks(4);
	int questionTypeId;
	String questionTypeName;
	
	public int getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getQuestionTypeName() {
		return questionTypeName;
	}
	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
	
}
