package com.allstack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allstack.common.AppResponse;
import com.allstack.common.EnumCollection.RetCode;
import com.allstack.pojo.CourseSection;
import com.allstack.services.CourseSectionService;

@RestController 
@RequestMapping("api/section")
public class CourseSectionController {
	@Autowired
	CourseSectionService courseSectionService;
	
	@RequestMapping(value = "/{courseSectionId}")
	public AppResponse<CourseSection> getCourseById(@PathVariable int courseSectionId){
		AppResponse<CourseSection> response = null;
		CourseSection courseSection = courseSectionService.getSectionById(courseSectionId);
		if(courseSection != null){
			response = new AppResponse<CourseSection>(RetCode.success, courseSection, "");
		}else{
			response = new AppResponse<CourseSection>(RetCode.failure, courseSection, "");
		}
		return response;
	}
}
