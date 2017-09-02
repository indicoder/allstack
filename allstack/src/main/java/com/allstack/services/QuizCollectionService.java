package com.allstack.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.dao.QuizCollectionDao;
import com.allstack.pojo.QuizCollection;

public class QuizCollectionService {
	@Autowired
	QuizCollectionDao quizCollectionDao;
	
	public QuizCollection getQuizCollectionById(int quizCollectionId){
		if(quizCollectionId != -1)
			return quizCollectionDao.getQuizCollectionById(quizCollectionId);
		else
			return new QuizCollection();
	}
	
	public QuizCollection addQuizCollection(int quizCollectionId, String quizCollectionName, String extQuizCollectionId){
		return quizCollectionDao.addQuizCollection(quizCollectionId, quizCollectionName, extQuizCollectionId);
	}
	
	public boolean deleteQuizCollection(int quizCollectionId){
		return quizCollectionDao.deleteQuizCollection(quizCollectionId);
	}
	
	public QuizCollection updateQuizCollection(QuizCollection quizCollection){
		return quizCollectionDao.updateQuizCollection(quizCollection);
	}
}
