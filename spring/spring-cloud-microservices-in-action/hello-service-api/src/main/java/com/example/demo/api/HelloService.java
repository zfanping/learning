package com.example.demo.api;

import com.example.demo.dto.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wshcatkin on 2018-07-23.
 */
public interface HelloService {
    @GetMapping(path = "hello4")
    public String hello(@RequestParam("name") String name);


    @GetMapping(path = "hello5")
    public String hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @PostMapping(path = "hello6")
    public String hello(@RequestBody User user);
}
