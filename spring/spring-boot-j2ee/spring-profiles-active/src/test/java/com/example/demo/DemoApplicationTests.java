package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod,dev")
public class DemoApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);
    @Value("${demo.name}")
    private String name;

    @Value("${test.flyway.enabled}")
    private boolean enabled;

    @Test
    public void contextLoads() {
        log.info("name= {}", name);
        log.info("test.flyway.enabled= {}", enabled);
    }
}
