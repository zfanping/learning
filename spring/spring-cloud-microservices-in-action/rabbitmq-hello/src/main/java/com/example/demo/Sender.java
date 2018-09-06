package com.example.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wshcatkin on 2018-08-08.
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbmitTemplate;

    public void send() {
        String content = "hello " + new Date();
        System.out.println("Sender: " + content);
        this.rabbmitTemplate.convertAndSend("hello", content);
    }
}
