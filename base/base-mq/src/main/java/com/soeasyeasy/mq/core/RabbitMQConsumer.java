//package com.soeasyeasy.mq.core;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitMQConsumer {
//
//    private final MessageListener listener;
//
//    public RabbitMQConsumer(MessageListener listener) {
//        this.listener = listener;
//    }
//
//
//    @RabbitListener(queues = "${rabbitmq.queue}")
//    public void receiveMessage(String message) {
//        Message msg = new Message(message.getBytes());
//        listener.onMessage(msg);
//    }
//
//    public void close() {
//        // 同上，通常不需要显式关闭消费者
//    }
//}