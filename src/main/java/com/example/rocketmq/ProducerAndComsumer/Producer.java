package com.example.rocketmq.ProducerAndComsumer;

import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Author YZ
 * @Date 2019/11/15 11:07
 * @ClassName Producer
 */
@Component
public class Producer {

    @Autowired
    MessageChannel output;

    @Autowired
    MessageChannel output1;

    public void send(Object string){
        //设置tag
        output.send(MessageBuilder.withPayload(string).setHeader(RocketMQHeaders.TAGS, "tag1").build());
        //output1.send(MessageBuilder.withPayload(string).build());

    }
}
