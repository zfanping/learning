package com.example.demo;

import org.springframework.web.bind.annotation.*;

/**
 * Created by wshcatkin on 2018-07-23.
 */
@RestController
public class RefalctorHelloController implements com.example.demo.api.HelloService {
    public String hello(@RequestParam String name) {
        return "hello " + name;
    }


    public String hello(@RequestHeader String name, @RequestHeader Integer age) {
        return "hello " + name;
    }

    public String hello(@RequestBody com.example.demo.dto.User user) {
        return "hello " + user;
    }

}
