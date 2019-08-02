//package com.beiming.rabbit.comsumer.fanout;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RabbitListener(queues = "fanout-queue-one")   //指明监听的队列的名字
//public class ConsumerFanoutOne {
//	
//	@RabbitHandler
//    public void consume(String msg){
//        System.out.println("fanout-queue-one接收到的消息" + msg);
//    }
//
//}
