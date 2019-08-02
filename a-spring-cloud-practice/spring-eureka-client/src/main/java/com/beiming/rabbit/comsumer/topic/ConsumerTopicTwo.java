package com.beiming.rabbit.comsumer.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic-queue-two")   //指明监听的队列的名字
public class ConsumerTopicTwo {
	
	@RabbitHandler
    public void consume(String msg){
        System.out.println("topic-queue-two接收到的消息" + msg);
    }

}
