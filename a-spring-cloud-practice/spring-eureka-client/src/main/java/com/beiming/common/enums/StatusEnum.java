package com.beiming.common.enums;

public enum StatusEnum {

	SUCCESS("OK"),
	FAIL("FAIL");

	private final String desc;

	private StatusEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
