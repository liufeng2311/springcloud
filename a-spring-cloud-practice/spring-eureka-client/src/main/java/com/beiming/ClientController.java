package com.beiming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.rabbit.Producer;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	Producer producer;

	@Value("${server.port}")
	private String port;

	@RequestMapping("/sayHello")
	public String sayHello() {
		return "I am from port : " + port;

	}

	@RequestMapping("/rabbit")
	public void rabbit() {
		producer.produce();
	}

}
