package com.beiming.rabbit.comsumer.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic-queue-one")   //指明监听的队列的名字
public class ConsumerTopicOne {
	
	@RabbitHandler
    public void consume(String msg){
        System.out.println("topic-queue-one接收到的消息" + msg);
    }

}
