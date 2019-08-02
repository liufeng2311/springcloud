package com.beiming.rabbit.config.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FanoutExchangeConfig {
	
	//定义FanoutExchange
	@Bean
	public FanoutExchange fanoutExchange() {
		return new  FanoutExchange("fanout-exchange");
	}
	
	
	//定义队列queue-one,queue-two,queue-three
	@Bean
	public Queue queueOne() {
		return new Queue("fanout-queue-one");
	}
	
	@Bean
	public Queue queueTwo() {
		return  new Queue("fanout-queue-two");
	}
	
	@Bean
	public Queue queueThree() {
		return new Queue("fanout-queue-three");
	}

	//定义队列和交换机的绑定关系
	@Bean
    public Binding bindingExchangeWithOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }
	
	@Bean
    public Binding bindingExchangeWithTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }
	
	@Bean
    public Binding bindingExchangeWithThree() {
        return BindingBuilder.bind(queueThree()).to(fanoutExchange());
    }
	
}
