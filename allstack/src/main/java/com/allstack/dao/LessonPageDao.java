package com.allstack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.pojo.CourseSection;
import com.allstack.pojo.LessonPage;
import com.allstack.pojo.Question;
import com.allstack.pojo.QuizCollection;

public class LessonPageDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public LessonPage getLessonPageById(int pageId){
		Session session = sessionFactory.openSession();
		LessonPage lessonPage = null;
		List<LessonPage> singleLessonPageList = session.createQuery("select l from LessonPage l where l.pageId=:pageId").setParameter("pageId", pageId).list();
		if(!singleLessonPageList.isEmpty()){
			lessonPage = singleLessonPageList.get(0);
		}
		return lessonPage;
	}
	
	public LessonPage addOrUpdateLessonPage(LessonPage lessonPage){
		Session session = sessionFactory.openSession();
		if(lessonPage == null) return lessonPage;
		CourseSection courseSection = lessonPage.getCourseSection();
		//Does the courseSection exist?
		if(courseSection == null){
			return null; //LessonPage can't be updated without a courseSection
		}
		List<CourseSection> singleCourseSectionList = session.createQuery("select c from CourseSection c where c.courseSectionId=:courseSectionId").setParameter("courseSectionId", courseSection.getCourseSectionId()).list();
		if(!singleCourseSectionList.isEmpty()){
			//If courseSection is found, then add the right LessonPage
			CourseSection dbCourseSection = singleCourseSectionList.get(0);
			lessonPage.setCourseSection(dbCourseSection);
			//If QuizCollection is found, add the right QuizCollection
			if(lessonPage.getQuizCollection() != null){
				List<QuizCollection> singleQuizCollectionList = session.createQuery("select c from QuizCollection c where c.quizCollectionId=:quizCollectionId").setParameter("quizCollectionId", lessonPage.getQuizCollection().getQuizCollectionId()).list();
				if(!singleQuizCollectionList.isEmpty()){
					QuizCollection dbQuizcollection = singleQuizCollectionList.get(0);
					lessonPage.setQuizCollection(dbQuizcollection);
				}else{
					return null;//As QuizCollection not found
				}
			}
			session.beginTransaction();			
			session.saveOrUpdate(lessonPage);			
			session.getTransaction().commit();
		}else{
			lessonPage = null;//Because QuizCollection is not found
		}
		return lessonPage;
	}
	
	public boolean deleteLessonPage(int pageId){
		boolean isDeleteSuccess = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "delete from LessonPage where pageId=:pageId";
			int rowsUpdated = session.createQuery(hql).setParameter("pageId", pageId).executeUpdate();
			transaction.commit();
			isDeleteSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return isDeleteSuccess;
	}
}