package com.soeasyeasy.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 系统服务
 *
 * @author hc
 */
@SpringBootApplication(scanBasePackages = {"com.soeasyeasy"})
@MapperScan("com.soeasyeasy.system.mapper")
@EnableAsync
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
