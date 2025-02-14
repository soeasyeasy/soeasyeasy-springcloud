//package com.soeasyeasy.mq.core;
//
//import org.apache.rocketmq.client.consumer.listener.MessageListener;
//import org.springframework.context.ApplicationContext;
//
//public class RabbitMQConsumerFactory {
//
//    private final ApplicationContext applicationContext;
//
//    public RabbitMQConsumerFactory(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    public RabbitMQConsumer createConsumer(String queue, MessageListener listener) {
//        return new RabbitMQConsumer(queue, listener);
//    }
//}