package com.allstack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.pojo.CourseSection;
import com.allstack.pojo.QuizCollection;

public class QuizCollectionDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public QuizCollection getQuizCollectionById(int quizCollectionId){
		Session session = sessionFactory.openSession();
		QuizCollection quizCollection = null;
		List<QuizCollection> singleQuizCollectionList = session.createQuery("select q from QuizCollection q where q.quizCollectionId=:quizCollectionId").setParameter("quizCollectionId", quizCollectionId).list();
		if(!singleQuizCollectionList.isEmpty()){
			quizCollection = singleQuizCollectionList.get(0);
		}
		return quizCollection;
	}
	
	/*
	 * Quiz collection can be added against a CourseSection
	 */
	public QuizCollection addQuizCollection(int courseSectionId, String quizCollectionName, String extQuizCollectionId){
		Session session = sessionFactory.openSession();
		QuizCollection quizCollection = null;
		CourseSection section = null;
		List<CourseSection> singleCourseSectionList = session.createQuery("select c from CourseSection c where c.courseSectionId=:courseSectionId").setParameter("courseSectionId", courseSectionId).list();
		if(!singleCourseSectionList.isEmpty()){
			//If coursesection is found, then insert the courseSection
			session.beginTransaction();
			section = singleCourseSectionList.get(0);
		    quizCollection = new QuizCollection();
		    quizCollection.setCourseSection(section);
		    quizCollection.setQuizCollectionName(quizCollectionName);
		    quizCollection.setExtQuizCollectionId(extQuizCollectionId);
			
			session.saveOrUpdate(quizCollection);
			
			session.getTransaction().commit();
		}else{
			quizCollection = null;//Because course is not found
		}
		return quizCollection;
	}
	
	public boolean deleteQuizCollection(int quizCollectionId){
		boolean isDeleteSuccess = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "delete from QuizCollection where quizCollectionId=:quizCollectionId";
			int rowsUpdated = session.createQuery(hql).setParameter("quizCollectionId", quizCollectionId).executeUpdate();
			transaction.commit();
			isDeleteSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return isDeleteSuccess;
	}
	
	public QuizCollection updateQuizCollection(QuizCollection quizCollection){
		Session session = sessionFactory.openSession();
		if(quizCollection == null) return quizCollection;
		CourseSection section = quizCollection.getCourseSection();
		//Does the section exist?
		if(section == null){
			return null; //QuizCollection can't be updated without a CourseSection
		}
		List<CourseSection> singleCourseSectionList = session.createQuery("select c from CourseSection c where c.courseSectionId=:courseSectionId").setParameter("courseSectionId", section.getCourseSectionId()).list();
		if(!singleCourseSectionList.isEmpty()){
			//If course is found, then add the right courseSection
			CourseSection dbCourseSection = singleCourseSectionList.get(0);
			quizCollection.setCourseSection(dbCourseSection);
			session.beginTransaction();			
			session.saveOrUpdate(quizCollection);			
			session.getTransaction().commit();
		}else{
			quizCollection = null;//Because courseSection is not found
		}
		return quizCollection;
	}
}