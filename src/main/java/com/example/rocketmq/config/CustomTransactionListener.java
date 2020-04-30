/*
 * Copyright (C) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.rocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * mq事物提交
 *
 * @author Mr.Li yz
 * @date 2019/12/2
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "tx-bonded-group", corePoolSize = 5, maximumPoolSize = 10)
public class CustomTransactionListener implements RocketMQLocalTransactionListener {
    //Commit：提交事务信息，消费者可以消费此消息
    //Rollback：回滚事务消息，broker会删除这条消息，消费者不能消费
    //UNKNOWN：broker需要回查确认消息状态

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg,
                                                                 Object arg) {
        System.out.println(msg + " send success");

        //可以理解为消息发送后,mq已接收到信息后的本地业务及事务,如银行扣款发送消息成功后,本地才会-100
        //return RocketMQLocalTransactionState.COMMIT;

        try {
            //执行本地业务
            int i = 1 / 0;
            return RocketMQLocalTransactionState.COMMIT;
        }catch(NegativeArraySizeException e){
            //不确定最终是否成功
            return RocketMQLocalTransactionState.UNKNOWN;
        }catch(Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println(msg + " send check");

        //可以理解为消息发送后,mq没有返回消息,不知mq是否接收消息成功,此时应该处理的业务,
        //一般是在发送给mq数据后没有收到mq的消息,才会执行此方法,一般时间为1分钟
        //return RocketMQLocalTransactionState.COMMIT;

        //回查事务执行状态
        Integer i=1/0;
        if(i!= null){
            if(i>3){
                return RocketMQLocalTransactionState.COMMIT;
            }else{
                return RocketMQLocalTransactionState.ROLLBACK;
            }
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }

}
