package com.beiming.rabbit.comsumer.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct-queue-one")   //指明监听的队列的名字
public class ConsumerDirectOne {
	
	@RabbitHandler
    public void consume(String msg){
        System.out.println("direct-queue-one接收到的消息" + msg);
    }

}
