package com.soeasyeasy.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@RestController
public class TestController {
    /**
     * 测试
     */
    @PostMapping("/test")
    public String test() {
        return "hello world";
    }
}
