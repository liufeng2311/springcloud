package com.beiming.rabbit.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProducer {
	
	@Autowired
	private AmqpTemplate template;   //此处采用单例模式引入
	
	public void produce() {
		template.convertAndSend("fanout-exchange", "", "Hello, rabbitMQ , this is fanout");  //该模式下第二个参数不起作用
	}

}
