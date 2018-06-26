package com.allstack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allstack.common.AppResponse;
import com.allstack.common.EnumCollection.RetCode;
import com.allstack.pojo.Course;
import com.allstack.pojo.Question;
import com.allstack.pojo.User;
import com.allstack.services.CourseService;
import com.allstack.services.UserService;

@CrossOrigin(origins = "http://decahack.com:9080")
@RestController 
@RequestMapping("api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/users/{userId}")
	public AppResponse<User> getUserById(@PathVariable int userId){
		AppResponse<User> response = null;
		User user = userService.getUserById(userId);
		if(user != null){
			response = new AppResponse<User>(RetCode.success, user, "");
		}else{
			response = new AppResponse<User>(RetCode.failure, user, "");
		}
		return response;
	}
	
	//@CrossOrigin(origins = "http://localhost:9080")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AppResponse<User> addUser(@RequestBody User user){
		AppResponse<User> response = null;
		//question = questionService.addQuestion(question.getQuizCollection().getQuizCollectionId(), question.getExtQuestionId(), question.getQuestionType(), question.getQuizQuestionHTML(), question.getChoice1HTML(), question.getChoice2HTML(), question.getChoice3HTML(), question.getChoice4HTML(), question.getIsChoice1Correct(), question.getIsChoice2Correct(), question.getIsChoice3Correct(), question.getIsChoice4Correct(), question.getAnswerHTML(), question.getPointsForQuestion());
		user = userService.addOrUpdateUser(user);
		response = new AppResponse<User>(RetCode.success, user, "");
		return response;
	}
}
