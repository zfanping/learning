package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by wshcatkin on 2018-06-18.
 */
@Service
@Profile("dev")
public class DevDemoService {
    private static final Logger log = LoggerFactory.getLogger(DevDemoService.class);
    @Value("${demo.name}")
    private String name;

    @PostConstruct
    public void init() {
        log.info("name = {}", name);
    }
}
