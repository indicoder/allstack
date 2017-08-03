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
}