package com.beiming.rabbit.comsumer.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct-queue-two")
public class ConsumerDirectTwo {
	
	@RabbitHandler
    public void consume(String msg){
        System.out.println("direct-queue-two接收到的消息" + msg);
    }

}
