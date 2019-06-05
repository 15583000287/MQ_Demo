package com.example.sms.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 邮件消费者
 */
@Component
@RabbitListener(queues = "fanout_email_queue")
public class FanoutEmailConsumer {

    @RabbitHandler
    public void process(String msg){
        System.out.println("邮件消费者获取生产者消息: " + msg);
    }
}
