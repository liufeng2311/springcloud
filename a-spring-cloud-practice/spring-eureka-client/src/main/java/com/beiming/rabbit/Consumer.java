package com.beiming.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Consumer {
	
	@RabbitHandler
    public void consume(String msg){
        System.out.println(msg);
    }

}
