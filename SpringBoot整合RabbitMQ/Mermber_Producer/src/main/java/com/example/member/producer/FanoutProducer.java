package com.example.member.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FanoutProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     * @param queueName 队列名
     */
    public void send(String queueName){
        String msg = "my_fanout_exchange "+new Date();
        System.out.println("生产者发送消息："+msg);
        amqpTemplate.convertAndSend(queueName,msg);
    }
}
