package com.example.sms.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 短息消费者
 */
@Component
@RabbitListener(queues = "fanout_sms_queue")
public class FanoutSmsConsumer {

    @RabbitHandler
    public void process(String msg){
        System.out.println("短信消费者获取生产者消息: " + msg);
    }
}
