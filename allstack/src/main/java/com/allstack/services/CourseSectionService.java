package com.allstack.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.dao.CourseSectionDao;
import com.allstack.pojo.CourseSection;

public class CourseSectionService {
	@Autowired
	CourseSectionDao courseSectionDao;
	
	public CourseSection getSectionById(int courseSectionId){
		if(courseSectionId != -1)
			return courseSectionDao.getCourseSectionById(courseSectionId);
		else
			return new CourseSection();
	}
	
	public CourseSection addCourseSection(int courseId, String sectionName, String extSectionId){
		return courseSectionDao.addCourseSection(courseId, sectionName, extSectionId);
	}
	
	public boolean deleteCourseSection(int courseSectionId){
		return courseSectionDao.deleteCourseSection(courseSectionId);
	}
	
	public CourseSection updateCourseSection(CourseSection section){
		return courseSectionDao.updateCourseSection(section);
	}
}
