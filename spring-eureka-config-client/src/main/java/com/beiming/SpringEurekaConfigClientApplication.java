package com.beiming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringEurekaConfigClientApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringEurekaConfigClientApplication.class, args);
	}
	
	@Value("${name}")
	String foo;
	@RequestMapping(value = "/hi")
	public String hi(){
		return foo;
	}
}
