package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * Created by wshcatkin on 2018-07-07.
 */
@RestController
public class HelloController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "hello")
    public String index() throws InterruptedException {
        List<String> serviceIds = discoveryClient.getServices();
        for (String serviceId : serviceIds) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance instance : instances) {
                log.info("/hello instance={}", instance);
            }
        }

        // wait
        int sleepTime = new Random().nextInt(3000);
        log.info("sleepTime={}", sleepTime);
        Thread.sleep(sleepTime);

        return "Hello World";
    }

    @GetMapping(path = "hello1")
    public String hello(@RequestParam String name) {
        return "hello " + name;
    }


    @GetMapping(path = "hello2")
    public String hello(@RequestHeader String name, @RequestHeader Integer age) {
        return "hello " + name;
    }

    @PostMapping(path = "hello3")
    public String hello(@RequestBody User user ) {
        return "hello " + user;
    }
}
