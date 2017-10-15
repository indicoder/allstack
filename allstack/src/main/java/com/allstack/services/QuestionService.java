package com.allstack.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.dao.QuestionDao;
import com.allstack.pojo.Question;

public class QuestionService {
	@Autowired
	QuestionDao questionDao;
	
	public Question getQuestionById(int questionId){
		if(questionId != -1)
			return questionDao.getQuestionById(questionId);
		else
			return new Question();
	}
	
	/*public Question addQuestion(int quizCollectionId, String extQuestionId, QuestionType questionType,
			String quizQuestionHTML, String choice1HTML, String choice2HTML, String choice3HTML,
			String choice4HTML, int isChoice1Correct, int isChoice2Correct, int isChoice3Correct, int isChoice4Correct,
			String answerHTML, int pointsForQuestion){
		return questionDao.addQuestion(quizCollectionId, extQuestionId, questionType, quizQuestionHTML, choice1HTML, choice2HTML, choice3HTML, choice4HTML, isChoice1Correct, isChoice2Correct, isChoice3Correct, isChoice4Correct, answerHTML, pointsForQuestion);
	}*/
	
	public boolean deleteQuestion(int questionId){
		return questionDao.deleteQuestion(questionId);
	}
	
	public Question addOrUpdateQuestion(Question question){
		return questionDao.addOrUpdateQuestion(question);
	}
}
