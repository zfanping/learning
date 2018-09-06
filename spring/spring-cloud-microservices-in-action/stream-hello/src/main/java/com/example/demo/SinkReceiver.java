package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Created by wshcatkin on 2018-08-08.
 */

@EnableBinding(value = {Sink.class})
public class SinkReceiver {
    private static final Logger log = LoggerFactory.getLogger(SinkReceiver.class);

    //    @StreamListener(Sink.INPUT)
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void receive(Object payload) {
        log.info("Received: {}" + payload);
    }
}
