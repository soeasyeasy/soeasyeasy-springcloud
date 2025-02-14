//package com.soeasyeasy.mq.core;
//
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.MessagingException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RocketMQProducer {
//
//    private final RocketMQTemplate rocketMQTemplate;
//
//    @Autowired
//    public RocketMQProducer(RocketMQTemplate rocketMQTemplate) {
//        this.rocketMQTemplate = rocketMQTemplate;
//    }
//
//    public void sendMessage(String topic, String message) throws MessagingException {
//        try {
//            rocketMQTemplate.convertAndSend(topic, message);
//        } catch (Exception e) {
//            throw new MessagingException("Failed to send message via RocketMQ", e);
//        }
//    }
//
//    public void close() {
//        // RocketMQ 的连接管理也通常由 Spring 管理，这里不需要特别处理
//    }
//}