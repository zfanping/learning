package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wshcatkin on 2018-07-19.
 */
@Service
public class HelloService {
    private static final Logger log = LoggerFactory.getLogger(HelloService.class);
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback", commandKey = "helloKey")
    public String helloService() {
        long start = System.currentTimeMillis();

        String resp = restTemplate.getForObject("http://HELLO-SERVICE/hello", String.class);

        log.info("Spend Time: {}", System.currentTimeMillis() - start);
        return resp;
    }

    public String helloFallback() {
        return "error";
    }
}
