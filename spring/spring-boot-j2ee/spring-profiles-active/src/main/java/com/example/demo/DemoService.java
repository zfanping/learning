package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by wshcatkin on 2018-06-18.
 */
@Service
public class DemoService {
    private static final Log log = LogFactory.getLog(DemoService.class);

    @Value("${demo.name}")
    private String name;

    @PostConstruct
    public void init() {
        log.info("name = " + name);
    }
}
