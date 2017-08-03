package com.allstack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.pojo.Course;

public class CourseDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Course> getAllCourses(){
		Session session = sessionFactory.openSession();	
		List<Course> courses = session.createQuery("select c from Course c").list(); 
		return courses;
	}
	
	public Course getCourseById(int courseId){
		Session session = sessionFactory.openSession();
		Course course = null;
		List<Course> singleCourseList = session.createQuery("select c from Course c where c.courseId=:courseId").setParameter("courseId", courseId).list();
		if(!singleCourseList.isEmpty()){
			course = singleCourseList.get(0);
		}
		return course;
	}
}
