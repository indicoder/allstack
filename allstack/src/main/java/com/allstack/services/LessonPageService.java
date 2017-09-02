package com.allstack.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.dao.LessonPageDao;
import com.allstack.pojo.LessonPage;

public class LessonPageService {
	@Autowired
	LessonPageDao lessonPageDao;
	
	public LessonPage getLessonPageById(int pageId){
		if(pageId != -1)
			return lessonPageDao.getLessonPageById(pageId);
		else
			return new LessonPage();
	}
	
	public LessonPage addLessonPage(int pageId, String pageName, String extPageId){
		return lessonPageDao.addLessonPage(pageId, pageName, extPageId);
	}
	
	public boolean deleteLessonPage(int pageId){
		return lessonPageDao.deleteLessonPage(pageId);
	}
	
	public LessonPage updateLessonPage(LessonPage lessonPage){
		return lessonPageDao.updateLessonPage(lessonPage);
	}
}
