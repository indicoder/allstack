package com.allstack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
}