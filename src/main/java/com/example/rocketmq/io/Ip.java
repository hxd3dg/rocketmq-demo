package com.example.rocketmq.io;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface Ip {

    public String INPUT = "input1";

    @Input("input1")
    public MessageChannel input1();

    @Input("input2")
    public MessageChannel input2();
}
