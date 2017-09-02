package com.allstack.pojo.serializers;

import java.io.IOException;

import com.allstack.pojo.CourseSection;
import com.allstack.pojo.LessonPage;
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
		if(node.get("pageId") != null)
			pageId = (Integer) ((IntNode) node.get("pageId")).numberValue();
		if(node.get("extPageId") != null)
			extPageId = node.get("extPageId").asText();
		if(node.get("pageName") != null)
			pageName = node.get("pageName").asText();
		
		if(node.path("courseSection") != null && node.path("courseSection").get("courseSectionId") != null)
			courseSectionId = (Integer) ((IntNode) node.path("courseSection").get("courseSectionId")).numberValue();
        
		return new LessonPage(pageId, pageName, extPageId, new CourseSection(courseSectionId));
    }	
}
