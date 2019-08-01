package com.beiming.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	@Autowired
	private AmqpTemplate template;
	
	public void produce() {
		template.convertAndSend("hello", "Hello   RabbitMQ");
	}

}
