package com.beiming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
	

	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/sayHello")
	public String sayHello() {
		return "I am from port : " + port;
		
	}

}
