package com.example.rocketmq.ProducerAndComsumer;

import com.example.rocketmq.io.Ip;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @Author YZ
 * @Date 2019/11/15 11:13
 * @ClassName Comsumer
 */
@Component
public class Comsumer {
    //消费应该实现幂等性,防止重复消费

    //同时需要另外监听对应的死信队列

    @StreamListener(Ip.INPUT)
    public void sll(Object s){
        System.out.println("input1 :"+s);
    }

    @StreamListener("input2")
    public void sel(Object s){
        System.out.println("input2 :"+s);
    }

}
