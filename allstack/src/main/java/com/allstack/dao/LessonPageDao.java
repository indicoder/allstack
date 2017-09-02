package com.allstack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.pojo.CourseSection;
import com.allstack.pojo.LessonPage;

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
	
	/*
	 * LessonPage can be added against a CourseSection
	 */
	public LessonPage addLessonPage(int courseSectionId, String pageName, String extPageId){
		Session session = sessionFactory.openSession();
		LessonPage lessonPage = null;
		CourseSection section = null;
		List<CourseSection> singleCourseSectionList = session.createQuery("select c from CourseSection c where c.courseSectionId=:courseSectionId").setParameter("courseSectionId", courseSectionId).list();
		if(!singleCourseSectionList.isEmpty()){
			//If coursesection is found, then insert the courseSection
			session.beginTransaction();
			section = singleCourseSectionList.get(0);
			lessonPage = new LessonPage();
			lessonPage.setCourseSection(section);
			lessonPage.setPageName(pageName);
			lessonPage.setExtPageId(extPageId);
			
			session.saveOrUpdate(lessonPage);
			
			session.getTransaction().commit();
		}else{
			lessonPage = null;//Because course is not found
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
	
	public LessonPage updateLessonPage(LessonPage lessonPage){
		Session session = sessionFactory.openSession();
		if(lessonPage == null) return lessonPage;
		CourseSection section = lessonPage.getCourseSection();
		//Does the section exist?
		if(section == null){
			return null; //LessonPage can't be updated without a CourseSection
		}
		List<CourseSection> singleCourseSectionList = session.createQuery("select c from CourseSection c where c.courseSectionId=:courseSectionId").setParameter("courseSectionId", section.getCourseSectionId()).list();
		if(!singleCourseSectionList.isEmpty()){
			//If course is found, then add the right courseSection
			CourseSection dbCourseSection = singleCourseSectionList.get(0);
			lessonPage.setCourseSection(dbCourseSection);
			session.beginTransaction();			
			session.saveOrUpdate(lessonPage);			
			session.getTransaction().commit();
		}else{
			lessonPage = null;//Because courseSection is not found
		}
		return lessonPage;
	}
}