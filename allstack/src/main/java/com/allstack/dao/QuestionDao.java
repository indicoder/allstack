package com.allstack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.pojo.Question;
import com.allstack.pojo.QuestionType;
import com.allstack.pojo.QuizCollection;

public class QuestionDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public Question getQuestionById(int questionId){
		Session session = sessionFactory.openSession();
		Question question = null;
		List<Question> singleQuestionList = session.createQuery("select q from Question q where q.questionId=:questionId").setParameter("questionId", questionId).list();
		if(!singleQuestionList.isEmpty()){
			question = singleQuestionList.get(0);
		}
		return question;
	}
	
	/*
	 * Question can be added against a QuizCollection
	 *
	public Question addQuestion(int quizCollectionId, String extQuestionId, QuestionType questionType,
								String quizQuestionHTML, String choice1HTML, String choice2HTML, String choice3HTML,
								String choice4HTML, int isChoice1Correct, int isChoice2Correct, int isChoice3Correct, int isChoice4Correct,
								String answerHTML, String  int pointsForQuestion){*/
	public Question addQuestion(Question question){
		Session session = sessionFactory.openSession();
		QuizCollection quizCollection = question.getQuizCollection();
		//Is it associated with a valid QuizCollection?
		if(quizCollection == null) return null;
		List<QuizCollection> singleQuizCollectionList = session.createQuery("select q from QuizCollection q where q.quizCollectionId =:quizCollectionId").setParameter("quizCollectionId", quizCollection.getQuizCollectionId()).list();
		if(!singleQuizCollectionList.isEmpty()){
			//If quizcollection is found, then insert the quizcollection
			session.beginTransaction();
			quizCollection = singleQuizCollectionList.get(0);
			//question = new Question(0, extQuestionId, questionType, quizCollection, quizQuestionHTML, choice1HTML, choice2HTML, choice3HTML, choice4HTML, isChoice1Correct, isChoice2Correct, isChoice3Correct, isChoice4Correct, answerHTML, pointsForQuestion);
			
			session.saveOrUpdate(question);			
			session.getTransaction().commit();
		}else{
			question = null;//Because collection is not found
		}
		return question;
	}
	
	public boolean deleteQuestion(int questionId){
		boolean isDeleteSuccess = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "delete from Question where questionId=:questionId";
			int rowsUpdated = session.createQuery(hql).setParameter("questionId", questionId).executeUpdate();
			transaction.commit();
			isDeleteSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return isDeleteSuccess;
	}
	
	public Question addOrUpdateQuestion(Question question){
		Session session = sessionFactory.openSession();
		if(question == null) return question;
		QuizCollection quizCollection = question.getQuizCollection();
		//Does the collection exist?
		if(quizCollection == null){
			return null; //LessonPage can't be updated without a QuizCollection
		}
		List<QuizCollection> singleQuizCollectionList = session.createQuery("select c from QuizCollection c where c.quizCollectionId=:quizCollectionId").setParameter("quizCollectionId", quizCollection.getQuizCollectionId()).list();
		if(!singleQuizCollectionList.isEmpty()){
			//If course is found, then add the right QuizCollection
			QuizCollection dbQuizCollection = singleQuizCollectionList.get(0);
			question.setQuizCollection(dbQuizCollection);
			session.beginTransaction();			
			session.saveOrUpdate(question);			
			session.getTransaction().commit();
		}else{
			question = null;//Because QuizCollection is not found
		}
		return question;
	}
}