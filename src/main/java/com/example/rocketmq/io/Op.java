package com.example.rocketmq.io;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface Op {
    public String OUTPUT="output1";

    @Output("output")
    MessageChannel output();

    @Output("output1")
    MessageChannel output1();



}
