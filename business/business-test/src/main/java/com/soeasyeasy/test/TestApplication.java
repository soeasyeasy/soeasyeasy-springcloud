package com.soeasyeasy.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hc
 */
@SpringBootApplication(scanBasePackages = {"com.soeasyeasy"})
@MapperScan("com.soeasyeasy.test.mapper")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
