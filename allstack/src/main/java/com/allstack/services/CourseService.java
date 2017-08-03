package com.allstack.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.dao.CourseDao;
import com.allstack.pojo.Course;

public class CourseService {
	@Autowired
	CourseDao courseDao;
	
	public List<Course> getAllCourses(){
		return courseDao.getAllCourses();
	}
	
	public Course getCourseById(int courseId){
		return courseDao.getCourseById(courseId);
	}
}
