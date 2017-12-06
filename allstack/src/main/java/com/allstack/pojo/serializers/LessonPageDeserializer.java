package com.allstack.pojo.serializers;

import java.io.IOException;

import com.allstack.pojo.CourseSection;
import com.allstack.pojo.LessonPage;
import com.allstack.pojo.QuizCollection;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

public class LessonPageDeserializer extends JsonDeserializer<LessonPage>{
	@Override
    public LessonPage deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		int pageId = 0;
		String extPageId = null;
		String pageName = null;
		Integer courseSectionId = null;
		Integer contentType = null;
		String contentHTML = null;
		Integer quizCollectionId = null;
		if(node.hasNonNull("pageId"))
			pageId = (Integer) ((IntNode) node.get("pageId")).numberValue();
		if(node.hasNonNull("extPageId"))
			extPageId = node.get("extPageId").asText();
		if(node.hasNonNull("pageName"))
			pageName = node.get("pageName").asText();
		if(node.hasNonNull("contentType"))
			contentType = (Integer) ((IntNode) node.get("contentType")).numberValue();
		if(node.hasNonNull("contentHTML"))
			contentHTML = node.get("contentHTML").asText();
		if(node.hasNonNull("quizCollection") && node.get("quizCollection").hasNonNull("quizCollectionId"))
			quizCollectionId = node.get("quizCollection").get("quizCollectionId").intValue();
		
		if(node.path("courseSection") != null && node.path("courseSection").get("courseSectionId") != null)
			courseSectionId = (Integer) ((IntNode) node.path("courseSection").get("courseSectionId")).numberValue();
        
		if(quizCollectionId != null)
		    return new LessonPage(pageId, pageName, extPageId, contentHTML, new CourseSection(courseSectionId), new QuizCollection(quizCollectionId));
		else
			return new LessonPage(pageId, pageName, extPageId, contentHTML, new CourseSection(courseSectionId), null);
    }	
}
