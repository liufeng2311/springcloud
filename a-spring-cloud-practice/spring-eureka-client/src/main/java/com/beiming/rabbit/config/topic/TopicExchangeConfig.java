package com.beiming.rabbit.config.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TopicExchangeConfig {
	
	//定义TopicExchange
	@Bean
	public TopicExchange topicExchange() {
		return new  TopicExchange("topic-exchange");
	}
	
	
	//定义队列queue-one,queue-two,queue-three
	@Bean
	public Queue queueOne() {
		return new Queue("topic-queue-one");
	}
	
	@Bean
	public Queue queueTwo() {
		return  new Queue("topic-queue-two");
	}
	
	@Bean
	public Queue queueThree() {
		return new Queue("topic-queue-three");
	}

	//定义队列和交换机的绑定关系和路由键
	
	//路由键用.分割，  
	//使用topic-one路由键会接收到topic-two和topic-three的消息
	@Bean
    public Binding bindingExchangeWithOne() {
        return BindingBuilder.bind(queueOne()).to(topicExchange()).with("one");
    }
	
	@Bean
    public Binding bindingExchangeWithTwo() {
		return BindingBuilder.bind(queueTwo()).to(topicExchange()).with("two");
    }
	
	@Bean
    public Binding bindingExchangeWithThree() {
		return BindingBuilder.bind(queueThree()).to(topicExchange()).with("three");
    }
	
}
