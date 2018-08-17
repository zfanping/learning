package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by wshcatkin on 2018-06-18.
 */
@Service
@Profile("default")
public class DefaultDemoService {
    private static final Log log = LogFactory.getLog(DefaultDemoService.class);

    @Value("${demo.name}")
    private String name;

    @PostConstruct
    public void init() {
        log.info("name = " + name);
    }
}
