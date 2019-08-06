package com.beiming.common.enums;

public enum ErrorCodeEnum {

	SUCCESS(1000);
	
	private final Integer code;

	private ErrorCodeEnum(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
}
