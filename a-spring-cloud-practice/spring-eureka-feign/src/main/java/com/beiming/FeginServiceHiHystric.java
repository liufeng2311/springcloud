package com.beiming;

import org.springframework.stereotype.Component;

@Component
public class FeginServiceHiHystric implements FeginService{

	@Override
	public String sayHello() {
		return "error";
	}

}
