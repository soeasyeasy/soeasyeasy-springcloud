//package com.soeasyeasy.db.core;
//
//import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//// 配置类
//@Configuration
//public class MybatisPlusEnumConfig {
//
//    @Bean
//    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
//        return properties -> {
//            GlobalConfig globalConfig = properties.getGlobalConfig();
//            globalConfig.setEnumTypeHandler(MybatisEnumTypeHandler.class);
//        };
//    }
//}