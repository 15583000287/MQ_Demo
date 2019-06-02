package com.example.activemqtopic.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;

@Configuration
public class TopicConfig {
    //获取主题名
    @Value("${topic}")
    private String topic;

    //注册bean
    @Bean
    public Topic logQueue() {
        return new ActiveMQTopic(topic);
    }
}
