package com.beiming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fegin")
public class FeignController {
	
	@Autowired
	private FeginService feginService;
	@PostMapping("/sayHello")
	public String sayHello() {
		
		return feginService.sayHello();
		
	}
}
