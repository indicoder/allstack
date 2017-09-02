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
import com.allstack.pojo.QuizCollection;
import com.allstack.services.QuizCollectionService;

@RestController 
@RequestMapping("api/quizCollection")
public class QuizCollectionController {
	@Autowired
	QuizCollectionService quizCollectionService;
	
	@RequestMapping(value = "/{quizCollectionId}")
	public AppResponse<QuizCollection> getQuizCollectionById(@PathVariable int quizCollectionId){
		AppResponse<QuizCollection> response = null;
		QuizCollection quizCollection = quizCollectionService.getQuizCollectionById(quizCollectionId);
		if(quizCollection != null){
			response = new AppResponse<QuizCollection>(RetCode.success, quizCollection, "");
		}else{
			response = new AppResponse<QuizCollection>(RetCode.failure, quizCollection, "");
		}
		return response;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AppResponse<QuizCollection> addQuizCollection(@RequestBody QuizCollection quizCollection){
		AppResponse<QuizCollection> response = null;
		quizCollection = quizCollectionService.addQuizCollection(quizCollection.getCourseSection().getCourseSectionId(), quizCollection.getQuizCollectionName(), quizCollection.getExtQuizCollectionId());
		response = new AppResponse<QuizCollection>(RetCode.success, quizCollection, "");
		return response;
	}
	
	@RequestMapping(value = "/{quizCollectionId}", method = RequestMethod.DELETE)
	public AppResponse<Boolean> deleteQuizCollectionById(@PathVariable int quizCollectionId){
		AppResponse<Boolean> response = null;
		boolean isDeleteSuccess = quizCollectionService.deleteQuizCollection(quizCollectionId);
		if(isDeleteSuccess){
			response = new AppResponse<Boolean>(RetCode.success, true, "");
		}else{
			response = new AppResponse<Boolean>(RetCode.failure, false, "");
		}
		return response;
	}
	
	@RequestMapping(value = "/{quizCollectionId}",method = RequestMethod.PUT)
	@ResponseBody
	public AppResponse<QuizCollection> updateQuizCollectionById(@RequestBody QuizCollection quizCollection){
		AppResponse<QuizCollection> response = null;
		quizCollection = quizCollectionService.updateQuizCollection(quizCollection);
		if(quizCollection != null){
			response = new AppResponse<QuizCollection>(RetCode.success, quizCollection, "");
		}else{
			response = new AppResponse<QuizCollection>(RetCode.failure, quizCollection, "");
		}
		return response;
	}
}
