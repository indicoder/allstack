package com.allstack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.pojo.Course;
import com.allstack.pojo.CourseSection;

public class CourseSectionDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public CourseSection getCourseSectionById(int courseSectionId){
		Session session = sessionFactory.openSession();
		CourseSection section = null;
		List<CourseSection> singleSectionList = session.createQuery("select c from CourseSection c where c.courseSectionId=:courseSectionId").setParameter("courseSectionId", courseSectionId).list();
		if(!singleSectionList.isEmpty()){
			section = singleSectionList.get(0);
		}
		return section;
	}
	
	public CourseSection addCourseSection(int courseId, String sectionName, String extSectionId){
		Session session = sessionFactory.openSession();
		CourseSection section = null;
		Course course = null;
		List<Course> singleCourseList = session.createQuery("select c from Course c where c.courseId=:courseId").setParameter("courseId", courseId).list();
		if(!singleCourseList.isEmpty()){
			//If course is found, then insert the courseSection
			session.beginTransaction();
			course = singleCourseList.get(0);
		    section = new CourseSection();
			section.setCourse(course);
			section.setSectionName(sectionName);
			section.setExtSectionId(extSectionId);
			
			session.saveOrUpdate(section);
			
			session.getTransaction().commit();
		}else{
			section = null;//Because course is not found
		}
		return section;
	}
	
	public boolean deleteCourseSection(int courseSectionId){
		boolean isDeleteSuccess = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "delete from CourseSection where courseSectionId=:courseSectionId";
			int rowsUpdated = session.createQuery(hql).setParameter("courseSectionId", courseSectionId).executeUpdate();
			transaction.commit();
			isDeleteSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return isDeleteSuccess;
	}
	
	public CourseSection updateCourseSection(CourseSection section){
		Session session = sessionFactory.openSession();
		if(section == null) return section;
		Course course = section.getCourse();
		//Does the course exist?
		if(course == null){
			return null; //CourseSection can't be updated without a Course
		}
		List<Course> singleCourseList = session.createQuery("select c from Course c where c.courseId=:courseId").setParameter("courseId", course.getCourseId()).list();
		if(!singleCourseList.isEmpty()){
			//If course is found, then add the right course
			Course dbCourse = singleCourseList.get(0);
			section.setCourse(dbCourse);
			session.beginTransaction();			
			session.saveOrUpdate(section);			
			session.getTransaction().commit();
		}else{
			section = null;//Because course is not found
		}
		return section;
	}
}