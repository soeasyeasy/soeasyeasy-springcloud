package com.soeasyeasy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 * @author hc
 */
@SpringBootApplication(scanBasePackages = {"com.soeasyeasy"})
@MapperScan("com.soeasyeasy.ai.mapper")
public class AiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiApplication.class, args);
    }

}
