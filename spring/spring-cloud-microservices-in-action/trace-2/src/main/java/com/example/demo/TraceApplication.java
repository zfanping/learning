package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class TraceApplication {
    private static final Logger log = LoggerFactory.getLogger("TraceApplication");

    @GetMapping("trace-2")
    public String trace(HttpServletRequest request) {
        log.info("call trace-2, traceId={}, spanId={}",
                request.getHeader("X-B3-TraceId"),
                request.getHeader("X-B3-SpanId"));
        return "Trace";
    }

    public static void main(String[] args) {
        SpringApplication.run(TraceApplication.class, args);
    }
}
