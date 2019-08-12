package com.beiming.java8.interfaces;

public interface Java8Interface2 {

	static final String DEFAULT_MEAASGE = "It is Java8Interface2 default message";
	
	static final String STATIC_MEAASGE = "It is Java8Interface2 static message";
	
	String previous(String str);
	
	default String java8Default() {
		return DEFAULT_MEAASGE;
	}
	
	static String java8Static() {
		return DEFAULT_MEAASGE;
	}
}
