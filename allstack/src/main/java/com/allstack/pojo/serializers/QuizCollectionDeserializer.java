package com.allstack.pojo.serializers;

import java.io.IOException;

import com.allstack.pojo.CourseSection;
import com.allstack.pojo.QuizCollection;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

public class QuizCollectionDeserializer extends JsonDeserializer<QuizCollection>{
	@Override
    public QuizCollection deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		int quizCollectionId = 0;
		String extQuizCollectionId = null;
		String quizCollectionName = null;
		Integer courseSectionId = null;
		int isDefault = 0;
		if(node.get("quizCollectionId") != null)
			quizCollectionId = (Integer) ((IntNode) node.get("quizCollectionId")).numberValue();
		if(node.get("extQuizCollectionId") != null)
			extQuizCollectionId = node.get("extQuizCollectionId").asText();
		if(node.get("quizCollectionName") != null)
			quizCollectionName = node.get("quizCollectionName").asText();
		if(node.get("isDefault") != null)
			isDefault = (Integer) ((IntNode) node.get("isDefault")).numberValue();
		
		if(node.path("courseSection") != null && node.path("courseSection").get("courseSectionId") != null)
			courseSectionId = (Integer) ((IntNode) node.path("courseSection").get("courseSectionId")).numberValue();
        
		return new QuizCollection(quizCollectionId,extQuizCollectionId, quizCollectionName, isDefault, new CourseSection(courseSectionId));
    }	
}
