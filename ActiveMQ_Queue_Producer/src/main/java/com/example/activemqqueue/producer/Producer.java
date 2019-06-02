package com.example.activemqqueue.producer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Random;

@Component
@EnableScheduling
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    //每隔5秒向消息队列发送消息
    @Scheduled(fixedDelay = 5000)
    public void send(){
        //用户名，封装邮件内容时使用
        String userName = "张三"+ new Random().nextInt(20);
        //指定发送到哪个邮箱 (邮件接收者邮箱)
        String email = "593464677@qq.com";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",userName);
        jsonObject.put("email",email);
        //将发送的消息转换为Json字符串
        String msg = jsonObject.toJSONString();
        System.out.println("生产者向消费者发送内容：" +  msg);
        jmsMessagingTemplate.convertAndSend(queue,msg);
    }
}
