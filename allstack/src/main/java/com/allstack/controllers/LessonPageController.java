package com.allstack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.allstack.common.AppResponse;
import com.allstack.common.EnumCollection.RetCode;
import com.allstack.pojo.LessonPage;
import com.allstack.services.LessonPageService;

@RestController 
@RequestMapping("api/lessonPage")
public class LessonPageController {
	@Autowired
	LessonPageService lessonPageService;
	
	@RequestMapping(value = "/{pageId}")
	public AppResponse<LessonPage> getLessonPageById(@PathVariable int pageId){
		AppResponse<LessonPage> response = null;
		LessonPage lessonPage = lessonPageService.getLessonPageById(pageId);
		if(lessonPage != null){
			response = new AppResponse<LessonPage>(RetCode.success, lessonPage, "");
		}else{
			response = new AppResponse<LessonPage>(RetCode.failure, lessonPage, "");
		}
		return response;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AppResponse<LessonPage> addLessonPage(@RequestBody LessonPage lessonPage){
		AppResponse<LessonPage> response = null;
		lessonPage = lessonPageService.addLessonPage(lessonPage.getCourseSection().getCourseSectionId(), lessonPage.getPageName(), lessonPage.getExtPageId());
		response = new AppResponse<LessonPage>(RetCode.success, lessonPage, "");
		return response;
	}
	
	@RequestMapping(value = "/{pageId}", method = RequestMethod.DELETE)
	public AppResponse<Boolean> deleteLessonPageById(@PathVariable int pageId){
		AppResponse<Boolean> response = null;
		boolean isDeleteSuccess = lessonPageService.deleteLessonPage(pageId);
		if(isDeleteSuccess){
			response = new AppResponse<Boolean>(RetCode.success, true, "");
		}else{
			response = new AppResponse<Boolean>(RetCode.failure, false, "");
		}
		return response;
	}
	
	@RequestMapping(value = "/{pageId}",method = RequestMethod.PUT)
	@ResponseBody
	public AppResponse<LessonPage> updateLessonPageById(@RequestBody LessonPage lessonPage){
		AppResponse<LessonPage> response = null;
		lessonPage = lessonPageService.updateLessonPage(lessonPage);
		if(lessonPage != null){
			response = new AppResponse<LessonPage>(RetCode.success, lessonPage, "");
		}else{
			response = new AppResponse<LessonPage>(RetCode.failure, lessonPage, "");
		}
		return response;
	}
}
