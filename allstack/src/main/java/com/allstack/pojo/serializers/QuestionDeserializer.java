package com.allstack.pojo.serializers;

import java.io.IOException;

import com.allstack.common.EnumCollection;
import com.allstack.pojo.Question;
import com.allstack.pojo.QuizCollection;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

public class QuestionDeserializer extends JsonDeserializer<Question>{
	@Override
    public Question deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		int questionId = 0;
		Integer questionType = null;
		String extQuestionId = null;
		String quizQuestionHTML = null;				
		String choice1HTML = null;
		String choice2HTML = null;
		String choice3HTML = null;
		String choice4HTML = null;
		Integer isChoice1Correct = null;
		Integer isChoice2Correct = null;
		Integer isChoice3Correct = null;
		Integer isChoice4Correct = null;
		String answerHTML = null;
		String answerHintHTML = null;
		String answerExplanationHTML = null;
		Integer pointsForQuestion = null;
		
		Integer quizCollectionId = null;
		
		if(node.hasNonNull("questionId"))
			questionId = (Integer) ((IntNode) node.get("questionId")).numberValue();
		if(node.hasNonNull("quizQuestionHTML"))
			quizQuestionHTML = node.get("quizQuestionHTML").asText();
		if(node.hasNonNull("pointsForQuestion")){
			pointsForQuestion = node.get("pointsForQuestion").intValue();
		}
		//TODO: Change isNodeValid to hasNonNull
		if(SerializerUtil.isNodeValid(node.get("choice1HTML")))
			choice1HTML = node.get("choice1HTML").asText();
		if(SerializerUtil.isNodeValid(node.get("choice2HTML")))
			choice2HTML = node.get("choice2HTML").asText();
		if(SerializerUtil.isNodeValid(node.get("choice3HTML")))
			choice3HTML = node.get("choice3HTML").asText();
		if(SerializerUtil.isNodeValid(node.get("choice4HTML")))
			choice4HTML = node.get("choice4HTML").asText();
		if(SerializerUtil.isNodeValid(node.get("isChoice1Correct"))){
			String isChoice1CorrectStr = node.get("isChoice1Correct").asText();
			isChoice1Correct = Integer.parseInt(isChoice1CorrectStr);
		}
		if(SerializerUtil.isNodeValid(node.get("isChoice2Correct"))){
			String isChoice2CorrectStr = node.get("isChoice2Correct").asText();
			isChoice2Correct = Integer.parseInt(isChoice2CorrectStr);
		}
		if(SerializerUtil.isNodeValid(node.get("isChoice3Correct"))){
			String isChoice3CorrectStr = node.get("isChoice3Correct").asText();
			isChoice3Correct = Integer.parseInt(isChoice3CorrectStr);
		}
		if(SerializerUtil.isNodeValid(node.get("isChoice4Correct"))){
			String pointsForQuestionStr = node.get("pointsForQuestion").asText();
			pointsForQuestion = Integer.parseInt(pointsForQuestionStr);
		}
		if(SerializerUtil.isNodeValid(node.get("answerHTML")))
			answerHTML = node.get("answerHTML").asText();
		if(SerializerUtil.isNodeValid(node.get("answerHintHTML")))
			answerHintHTML = node.get("answerHintHTML").asText();
		if(SerializerUtil.isNodeValid(node.get("answerExplanationHTML")))
			answerExplanationHTML = node.get("answerExplanationHTML").asText();	
		if(SerializerUtil.isNodeValid(node.get("questionType"))){
			questionType = node.get("questionType").intValue();
		}
		
		if(node.hasNonNull("quizCollection") && node.get("quizCollection").hasNonNull("quizCollectionId"))
			quizCollectionId = node.get("quizCollection").get("quizCollectionId").intValue();
		
		return new Question(questionId, extQuestionId, questionType, new QuizCollection(quizCollectionId), quizQuestionHTML, choice1HTML, choice2HTML, choice3HTML, choice4HTML, isChoice1Correct, isChoice2Correct, isChoice3Correct, isChoice4Correct, answerHTML, answerHintHTML, answerExplanationHTML, pointsForQuestion);
    }	
}
