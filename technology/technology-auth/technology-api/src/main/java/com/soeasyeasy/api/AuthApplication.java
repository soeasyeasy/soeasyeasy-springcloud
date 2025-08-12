package com.soeasyeasy.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务
 *
 * @author hc
 */
@SpringBootApplication(scanBasePackages = {"com.soeasyeasy"})
@MapperScan("com.soeasyeasy.api.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
