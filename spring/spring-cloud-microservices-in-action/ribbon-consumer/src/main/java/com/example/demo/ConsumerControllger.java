package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wshcatkin on 2018-07-07.
 */
@RestController
public class ConsumerControllger {
    @Autowired
    private HelloService helloService;

    @GetMapping(path = "ribbon-consumer")
    public String helloConsumer() {
        return helloService.helloService();
    }
}

