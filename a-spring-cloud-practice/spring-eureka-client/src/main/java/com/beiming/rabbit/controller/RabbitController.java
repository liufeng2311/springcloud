package com.beiming.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.rabbit.producer.TopicProducer;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

//	@Autowired
//	private FanoutProducer fanoutProducer;
	
	@Autowired
	private TopicProducer topicProducer;
	
//	@RequestMapping("/fanout")
//	public void fanoutExchange() {
//		fanoutProducer.produce();
//	}
	
	@RequestMapping("/topic")
	public void topicExchange() {
		topicProducer.produce();
	}
}
