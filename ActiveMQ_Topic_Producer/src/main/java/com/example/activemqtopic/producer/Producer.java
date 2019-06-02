package com.example.activemqtopic.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    //每隔5秒向消息队列发送消息
    @Scheduled(fixedDelay = 5000)
    public void send(){
        jmsMessagingTemplate.convertAndSend(topic,"基于发布/订阅发送消息: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
