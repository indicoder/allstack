package com.allstack.common;

import com.allstack.common.EnumCollection.RetCode;

public class AppResponse<T> {

	private RetCode code;
	private T data;
	private String description;
	
	public AppResponse() {}
	
	public AppResponse(RetCode code, T data, String description) {
		this.code = code;
		this.data = data;
		this.description = description;
	}

	public RetCode getCode() {
		return code;
	}

	public void setCode(RetCode code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
