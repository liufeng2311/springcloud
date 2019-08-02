//package com.beiming.rabbit.comsumer.fanout;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RabbitListener(queues = "fanout-queue-three")
//public class ConsumerFanoutThree {
//	
//	@RabbitHandler
//    public void consume(String msg){
//        System.out.println("fanout-queue-three接收到的消息" + msg);
//    }
//
//}
