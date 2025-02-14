//package com.soeasyeasy.mq.core;
//
//import org.apache.rocketmq.client.consumer.listener.MessageListener;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@RocketMQMessageListener(topic = "${rocketmq.topic}", consumerGroup = "${rocketmq.consumer.group}")
//public class RocketMQConsumer {
//
//    private final MessageListener listener;
//
//    public RocketMQConsumer(MessageListener listener) {
//        this.listener = listener;
//    }
//
//
//    public void onMessage(String message) {
//        // 处理消息逻辑
//        listener.onMessage(message);
//    }
//
//
//    public void close() {
//        // 通常不需要显式关闭消费者
//    }
//}