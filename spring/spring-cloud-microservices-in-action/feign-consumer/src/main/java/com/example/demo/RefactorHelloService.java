package com.example.demo;

import com.example.demo.api.*;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by wshcatkin on 2018-07-23.
 */
@FeignClient(value = "HELLO-SERVICE")
public interface RefactorHelloService extends com.example.demo.api.HelloService {
}
