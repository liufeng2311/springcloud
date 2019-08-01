package com.beiming.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {


//	@Bean // 声明交换机
//	public DirectExchange caseExchange() {
//		return new DirectExchange("case-exchange");
//	}

	@Bean
	public Queue queue() {
		return new Queue("hello");
	}

}
