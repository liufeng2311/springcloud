package com.beiming.rabbit.comsumer.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout-queue-two")
public class ConsumerFanoutTwo {
	
	@RabbitHandler
    public void consume(String msg){
        System.out.println("fanout-queue-two接收到的消息" + msg);
    }

}
