package com.allstack.pojo.serializers;

import java.io.IOException;

import com.allstack.common.EnumCollection;
import com.allstack.pojo.User;
import com.allstack.pojo.QuizCollection;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

public class UserDeserializer extends JsonDeserializer<User>{
	@Override
    public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		int userId = 0;
		
		String userHandle = null;
		Integer courseId = null;
		String fullName = null;
		String email = null;
		String mobile = null;
		
		if(node.hasNonNull("userId"))
			userId = (Integer) ((IntNode) node.get("userId")).numberValue();
		if(node.hasNonNull("userHandle"))
			userHandle = node.get("userHandle").asText();
		if(node.hasNonNull("fullName"))
			fullName = node.get("fullName").asText();
		if(node.hasNonNull("email"))
			email = node.get("email").asText();
		if(node.hasNonNull("mobile"))
			mobile = node.get("mobile").asText();

		return new User(userId, userHandle, courseId, fullName, email, mobile);
    }	
}
