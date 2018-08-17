package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wshcatkin on 2018-07-23.
 */
@RestController
public class ConsumerController {
    @Autowired
    private HelloService helloService;

    @Autowired
    private RefactorHelloService refactorHelloService;

    @RequestMapping(path = "/feign-consumer")
    public String helloConsumer() {
        return helloService.hello();
    }

    @RequestMapping(path = "/feign-consumer2")
    public String helloConsumer2() {
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("DIDI")).append("\n");
        sb.append(helloService.hello("DIDI", 30)).append("\n");
        sb.append(helloService.hello("DIDI", 30)).append("\n");
        sb.append(helloService.hello(new User("DIDI", 30))).append("\n");
        return sb.toString();
    }

    @RequestMapping(path = "/feign-consumer3")
    public String helloConsumer3() {
        StringBuilder sb = new StringBuilder();
        sb.append(refactorHelloService.hello("MIMI")).append("\n");
        sb.append(refactorHelloService.hello("MIMI", 30)).append("\n");
        sb.append(refactorHelloService.hello("MIMI", 30)).append("\n");
        sb.append(refactorHelloService.hello(new com.example.demo.dto.User("DIDI",20))).append("\n");
        return sb.toString();
    }
}
