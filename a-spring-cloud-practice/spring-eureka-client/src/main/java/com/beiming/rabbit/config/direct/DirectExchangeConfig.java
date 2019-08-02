package com.beiming.rabbit.config.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectExchangeConfig {
	
//	//定义DirectExchange
//	@Bean
//	public DirectExchange directExchange() {
//		return new DirectExchange("direct-exchange");
//	}
//	
//	
//	//定义队列queue-one,queue-two,queue-three
//	@Bean
//	public Queue queueOne() {
//		return new Queue("direct-queue-one");
//	}
//	
//	@Bean
//	public Queue queueTwo() {
//		return  new Queue("direct-queue-two");
//	}
//	
//	@Bean
//	public Queue queueThree() {
//		return new Queue("direct-queue-three");
//	}
//
//	//定义队列和交换机的绑定关系和路由键
//	
//	//路由键用.分割，  
//	//使用topic-one路由键会接收到topic-two和topic-three的消息
//	@Bean
//    public Binding bindingExchangeWithOne() {
//        return BindingBuilder.bind(queueOne()).to(directExchange()).with("one");
//    }
//	
//	@Bean
//    public Binding bindingExchangeWithTwo() {
//		return BindingBuilder.bind(queueTwo()).to(directExchange()).with("two");
//    }
//	
//	@Bean
//    public Binding bindingExchangeWithThree() {
//		return BindingBuilder.bind(queueThree()).to(directExchange()).with("three");
//    }
//	
}
