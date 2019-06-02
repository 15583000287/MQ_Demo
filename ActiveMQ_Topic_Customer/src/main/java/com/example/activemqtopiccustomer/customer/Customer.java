package com.example.activemqtopiccustomer.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    @Value("${server.port}")
    private String port;

    @JmsListener(destination = "${topic}")     //监听的队列，配置文件中的queue属性
    public void receive(String msg){
        System.out.println("发布/订阅模式消费者"+port+"收到msg:  " + msg);
    }
}
