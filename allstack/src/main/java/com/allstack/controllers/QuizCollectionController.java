package com.allstack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allstack.common.AppResponse;
import com.allstack.common.EnumCollection.RetCode;
import com.allstack.pojo.CourseSection;
import com.allstack.services.CourseSectionService;

@RestController 
@RequestMapping("api/section")
public class QuizCollectionController {
	@Autowired
	CourseSectionService courseSectionService;
	
	@RequestMapping(value = "/{courseSectionId}")
	public AppResponse<CourseSection> getCourseSectionById(@PathVariable int courseSectionId){
		AppResponse<CourseSection> response = null;
		CourseSection courseSection = courseSectionService.getSectionById(courseSectionId);
		if(courseSection != null){
			response = new AppResponse<CourseSection>(RetCode.success, courseSection, "");
		}else{
			response = new AppResponse<CourseSection>(RetCode.failure, courseSection, "");
		}
		return response;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AppResponse<CourseSection> addCourseSection(@RequestBody CourseSection courseSection){
		AppResponse<CourseSection> response = null;
		courseSection = courseSectionService.addCourseSection(courseSection.getCourse().getCourseId(), courseSection.getSectionName(), courseSection.getExtSectionId());
		response = new AppResponse<CourseSection>(RetCode.success, courseSection, "");
		return response;
	}
	
	@RequestMapping(value = "/{courseSectionId}", method = RequestMethod.DELETE)
	public AppResponse<Boolean> deleteCourseSectionById(@PathVariable int courseSectionId){
		AppResponse<Boolean> response = null;
		boolean isDeleteSuccess = courseSectionService.deleteCourseSection(courseSectionId);
		if(isDeleteSuccess){
			response = new AppResponse<Boolean>(RetCode.success, true, "");
		}else{
			response = new AppResponse<Boolean>(RetCode.failure, false, "");
		}
		return response;
	}
	
	@RequestMapping(value = "/{courseSectionId}",method = RequestMethod.PUT)
	public AppResponse<CourseSection> updateCourseSectionById(@RequestBody CourseSection courseSection){
		AppResponse<CourseSection> response = null;
		courseSection = courseSectionService.updateCourseSection(courseSection);
		if(courseSection != null){
			response = new AppResponse<CourseSection>(RetCode.success, courseSection, "");
		}else{
			response = new AppResponse<CourseSection>(RetCode.failure, courseSection, "");
		}
		return response;
	}
}
