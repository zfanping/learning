package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wshcatkin on 2018-07-23.
 */
@FeignClient("hello-service")
public interface HelloService {
    @RequestMapping("/hello")
    String hello();

    @GetMapping(path = "hello1")
    public String hello(@RequestParam("name") String name);


    @GetMapping(path = "hello2")
    public String hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @PostMapping(path = "hello3")
    public String hello(@RequestBody User user);
}
