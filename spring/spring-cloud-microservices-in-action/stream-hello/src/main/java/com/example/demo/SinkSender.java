package com.example.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;

/**
 * Created by wshcatkin on 2018-08-12.
 */
@EnableBinding(value = SinkSender.SinkOutput.class)
public class SinkSender {
//    @Output(Sink.INPUT)
//    MessageChannel output();

    @Bean
    @InboundChannelAdapter(value = SinkOutput.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public Message<Date> timerMessageSource() {
        return new GenericMessage<>(new Date());
    }

    public interface SinkOutput {
        String OUTPUT = "input";

        @Output(SinkOutput.OUTPUT)
        MessageChannel output();
    }
}

