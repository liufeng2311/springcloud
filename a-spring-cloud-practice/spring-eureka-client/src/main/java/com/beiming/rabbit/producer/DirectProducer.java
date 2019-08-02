package com.beiming.rabbit.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectProducer {
	
	@Autowired
	private AmqpTemplate template;   //此处采用单例模式引入
	
	public void produce() {
		template.convertAndSend("direct-exchange", "three", "Hello, rabbitMQ , this is direct");  //该模式下第二个参数为路由键
	}

}
