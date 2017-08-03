package com.allstack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allstack.common.AppResponse;
import com.allstack.common.EnumCollection.RetCode;
import com.allstack.pojo.Course;
import com.allstack.services.CourseService;

@RestController 
@RequestMapping("api/course")
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@RequestMapping("/courses")
    public AppResponse<List<Course>> getAllCourses(){
		AppResponse<List<Course>> response = new AppResponse<List<Course>>();
		response.setData(courseService.getAllCourses());
		response.setCode(RetCode.success);
		return response;
    }
	
	@RequestMapping(value = "/courses/{courseId}")
	public AppResponse<Course> getCourseById(@PathVariable int courseId){
		AppResponse<Course> response = null;
		Course course = courseService.getCourseById(courseId);
		if(course != null){
			response = new AppResponse<Course>(RetCode.success, course, "");
		}else{
			response = new AppResponse<Course>(RetCode.failure, course, "");
		}
		return response;
	}
}
