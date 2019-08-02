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
	@Bean
    public Binding bindingExchangeWithOne() {
        return BindingBuilder.bind(queueOne()).to(topicExchange()).with("topic-one1");
    }
	
	@Bean
    public Binding bindingExchangeWithTwo() {
		return BindingBuilder.bind(queueTwo()).to(topicExchange()).with("topic-two");
    }
	
	@Bean
    public Binding bindingExchangeWithThree() {
		return BindingBuilder.bind(queueThree()).to(topicExchange()).with("topic-three");
    }
	
}
