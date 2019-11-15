package com.example.rocketmq.Controller;

import com.example.rocketmq.ProducerAndComsumer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author YZ
 * @Date 2019/11/15 11:08
 * @ClassName RocketmqController
 */
@RestController
public class RocketmqController {

    @Autowired
    Producer producer;

    @RequestMapping("send")
    public void send(){
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add("哈哈哈");
        producer.send(objects);
    }
}
