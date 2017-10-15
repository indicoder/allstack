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
import com.allstack.pojo.Question;
import com.allstack.services.QuestionService;

@RestController 
@RequestMapping("api/question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@RequestMapping(value = "/{questionId}")
	public AppResponse<Question> getQuestionById(@PathVariable int questionId){
		AppResponse<Question> response = null;
		Question question = questionService.getQuestionById(questionId);
		if(question != null){
			response = new AppResponse<Question>(RetCode.success, question, "");
		}else{
			response = new AppResponse<Question>(RetCode.failure, question, "");
		}
		return response;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AppResponse<Question> addQuestion(@RequestBody Question question){
		AppResponse<Question> response = null;
		//question = questionService.addQuestion(question.getQuizCollection().getQuizCollectionId(), question.getExtQuestionId(), question.getQuestionType(), question.getQuizQuestionHTML(), question.getChoice1HTML(), question.getChoice2HTML(), question.getChoice3HTML(), question.getChoice4HTML(), question.getIsChoice1Correct(), question.getIsChoice2Correct(), question.getIsChoice3Correct(), question.getIsChoice4Correct(), question.getAnswerHTML(), question.getPointsForQuestion());
		question = questionService.addOrUpdateQuestion(question);
		response = new AppResponse<Question>(RetCode.success, question, "");
		return response;
	}
	
	@RequestMapping(value = "/{questionId}", method = RequestMethod.DELETE)
	public AppResponse<Boolean> deleteQuestionById(@PathVariable int questionId){
		AppResponse<Boolean> response = null;
		boolean isDeleteSuccess = questionService.deleteQuestion(questionId);
		if(isDeleteSuccess){
			response = new AppResponse<Boolean>(RetCode.success, true, "");
		}else{
			response = new AppResponse<Boolean>(RetCode.failure, false, "");
		}
		return response;
	}
	
	@RequestMapping(value = "/{questionId}",method = RequestMethod.PUT)
	@ResponseBody
	public AppResponse<Question> updateQuestionById(@RequestBody Question question){
		AppResponse<Question> response = null;
		question = questionService.addOrUpdateQuestion(question);
		if(question != null){
			response = new AppResponse<Question>(RetCode.success, question, "");
		}else{
			response = new AppResponse<Question>(RetCode.failure, question, "");
		}
		return response;
	}
}
