package com.beiming.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.rabbit.producer.DirectProducer;
import com.beiming.rabbit.producer.FanoutProducer;
import com.beiming.rabbit.producer.TopicProducer;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

	@Autowired
	private FanoutProducer fanoutProducer;
	
	@Autowired
	private TopicProducer topicProducer;
	
	@Autowired
	private DirectProducer directProducer;
	
	@RequestMapping("/fanout")
	public void fanoutExchange() {
		fanoutProducer.produce();
	}
	
	@RequestMapping("/topic")
	public void topicExchange() {
		topicProducer.produce();
	}
	
	@RequestMapping("/direct")
	public void directExchange() {
		directProducer.produce();
	}
}
