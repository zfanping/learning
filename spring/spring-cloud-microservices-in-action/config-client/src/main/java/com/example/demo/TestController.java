package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wshcatkin on 2018-08-05.
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${from}")
    private String from;

    @GetMapping("/from")
    public String from() {
        return from;
    }
}
