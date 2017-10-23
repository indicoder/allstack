package com.allstack.pojo.serializers;

import com.fasterxml.jackson.databind.JsonNode;

public class SerializerUtil {
    public static boolean isNodeValid(JsonNode node){
    	return node != null && !node.isNull();
    }
}
