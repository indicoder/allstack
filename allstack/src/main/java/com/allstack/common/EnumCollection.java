package com.allstack.common;

public class EnumCollection {
	
	public enum Environment {
		Prod, Dev
	}
	
	public enum BooleanEnum {
		Y, N
	}
	
	public enum RetCode {
		failure(0),success(1),nosession(2);		
		private Integer value;
		
		private RetCode(Integer value) {
			this.value = value;
		}		
		public Integer getValue() {
			return value;
		}
	}
	
	public enum QuestionType {
		SingleChoice(1), MultipleChoice(2), CodeoutPut(3), FillTheBlanks(4);
		int code;
		
		QuestionType(int code){
			this.code = code;
		}
	}
}