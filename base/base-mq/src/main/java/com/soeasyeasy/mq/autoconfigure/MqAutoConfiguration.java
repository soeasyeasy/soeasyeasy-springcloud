//package com.soeasyeasy.mq.autoconfigure;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MqAutoConfiguration {
//
//
//    @Configuration
//    @ConditionalOnClass(name = "org.apache.rocketmq.spring.core.RocketMQTemplate")
//    public static class RocketMQConfig {
//        // 注册 RocketMQ 生产者和消费者的 Bean
//    }
//
//    @Configuration
//    @ConditionalOnClass(name = "org.springframework.amqp.rabbit.core.RabbitTemplate")
//    public static class RabbitMQConfig {
//        // 注册 RabbitMQ 生产者和消费者的 Bean
//    }
//}