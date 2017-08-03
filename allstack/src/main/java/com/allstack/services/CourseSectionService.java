package com.allstack.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.dao.CourseSectionDao;
import com.allstack.pojo.CourseSection;

public class CourseSectionService {
	@Autowired
	CourseSectionDao courseSectionDao;
	
	public CourseSection getSectionById(int courseSectionId){
		return courseSectionDao.getCourseSectionById(courseSectionId);
	}
}
